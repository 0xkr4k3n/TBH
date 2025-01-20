package com.tbh.backend.service;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.dto.InstanceDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.entity.Instance;
import com.tbh.backend.entity.User;
import com.tbh.backend.mappers.ChallengeMapper;
import com.tbh.backend.repository.ChallengeRepository;
import com.tbh.backend.repository.InstanceRepository;
import com.tbh.backend.repository.UserRepository;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ChallengeMapper challengeMapper;
    private final UserRepository userRepository;
    private final InstanceRepository instanceRepository;
    private KubernetesService kubernetesService;


    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository,ChallengeMapper challengeMapper, UserRepository userRepository, InstanceRepository instanceRepository, KubernetesService kubernetesService) {
        this.challengeRepository = challengeRepository;
        this.challengeMapper = challengeMapper;
        this.userRepository = userRepository;
        this.instanceRepository = instanceRepository;
        this.kubernetesService = kubernetesService;
    }

    

    
    public List<ChallengeDTO> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(challengeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<ChallengeDTO> getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .map(challengeMapper::mapToDTO);
    }

    
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = challengeMapper.mapToEntity(challengeDTO);
        Challenge savedChallenge = challengeRepository.save(challenge);
        return challengeMapper.mapToDTO(savedChallenge);
    }

    
    public ChallengeDTO updateChallenge(Long id, ChallengeDTO challengeDTO) {
        if (!challengeRepository.existsById(id)) {
            throw new RuntimeException("Challenge not found with id: " + id);
        }
        Challenge challenge = challengeMapper.mapToEntity(challengeDTO);
        challenge.setId(id); 
        Challenge updatedChallenge = challengeRepository.save(challenge);
        return challengeMapper.mapToDTO(updatedChallenge);
    }

    
    public void deleteChallenge(Long id) {
        if (challengeRepository.existsById(id)) {
            challengeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Challenge not found with id: " + id);
        }
    }

    public InstanceDTO runChallenge(Long challengeId, Long userId) {
        // Fetch the challenge and user
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        String ip = null;
        int port = -1;
        try {
            V1Deployment deployment = kubernetesService.createDeploymentFromFile("default", challenge.getPath(), "0");
            System.out.println("Deployment created successfully: " + deployment.getMetadata().getName());

            V1Service service = kubernetesService.createServiceFromFile("default", challenge.getPath(), "1");
            System.out.println("Service created successfully: " + service.getMetadata().getName());

            CoreV1Api coreV1Api = kubernetesService.getCoreV1Api();
            V1Service createdService = coreV1Api.readNamespacedService(service.getMetadata().getName(), "default").execute();

            if (createdService.getSpec().getType().equals("NodePort")) {
                ip = "192.168.49.2"; // Example Minikube IP, replace with your node's IP
                port = createdService.getSpec().getPorts().get(0).getNodePort();
            } else if (createdService.getSpec().getType().equals("ClusterIP")) {
                ip = createdService.getSpec().getClusterIP();
                port = createdService.getSpec().getPorts().get(0).getPort();
            } else if (createdService.getSpec().getType().equals("LoadBalancer")) {
                ip = createdService.getStatus().getLoadBalancer().getIngress().get(0).getIp();
                port = createdService.getSpec().getPorts().get(0).getPort();
            }
        } catch (Exception e) {
            System.err.println("Failed to create resources: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Deployment failed");
        }
        System.out.println("ip: "+ ip +" -- port : "+ port );
        // Create a new instance
        Instance instance = new Instance();
        instance.setIp(ip);
        instance.setPort(port);
        instance.setUser(user);
        instance.setChallenge(challenge);

        // Save the instance to the database
        Instance savedInstance = instanceRepository.save(instance);

        // Return the DTO
        return new InstanceDTO(savedInstance.getId(), savedInstance.getIp(), savedInstance.getPort());
    }
}
