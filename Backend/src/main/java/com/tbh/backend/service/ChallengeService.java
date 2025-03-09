package com.tbh.backend.service;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.dto.InstanceDTO;
import com.tbh.backend.dto.UserDTO;
import com.tbh.backend.entity.Category;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.entity.Instance;
import com.tbh.backend.entity.User;
import com.tbh.backend.mappers.ChallengeMapper;
import com.tbh.backend.repository.CategoryRepository;
import com.tbh.backend.repository.ChallengeRepository;
import com.tbh.backend.repository.InstanceRepository;
import com.tbh.backend.repository.UserRepository;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Ingress;
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
    private final CategoryRepository categoryRepository;
    private final DnsService dnsService;
    private final UserService userService;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository,ChallengeMapper challengeMapper, UserRepository userRepository, InstanceRepository instanceRepository, KubernetesService kubernetesService,CategoryRepository categoryRepository, DnsService dnsService, UserService userService) {
        this.challengeRepository = challengeRepository;
        this.challengeMapper = challengeMapper;
        this.userRepository = userRepository;
        this.instanceRepository = instanceRepository;
        this.kubernetesService = kubernetesService;
        this.categoryRepository = categoryRepository;
        this.dnsService = dnsService;
        this.userService = userService;
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

        if (challengeDTO.getCategory() != null) {
            Category category = categoryRepository.findById(challengeDTO.getCategory().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            challenge.setCategory(category);
        }

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

    public String runChallenge(Long challengeId, Long userId) {
        String domainName = new String();
        // Fetch the challenge and user
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        String ip = null;
        int port = -1;
        try {
            V1Deployment deployment = kubernetesService.createDeploymentFromFile("default", challenge.getPath() + "/deployment.yaml", "0");
            System.out.println("Deployment created successfully: " + deployment.getMetadata().getName());

            V1Service service = kubernetesService.createServiceFromFile("default", challenge.getPath() + "/deployment.yaml" , "1");
            System.out.println("Service created successfully: " + service.getMetadata().getName());

            CoreV1Api coreV1Api = kubernetesService.getCoreV1Api();
            V1Service createdService = coreV1Api.readNamespacedService(service.getMetadata().getName(), "default").execute();

            V1Ingress ingress = kubernetesService.createIngressFromFile("default", challenge.getPath() + "/ingress.yaml", "0");
            System.out.println("Ingress created successfully: " + ingress.getMetadata().getName());

            domainName = deployment.getMetadata().getName();
            dnsService.addChallengeDomain(domainName);
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
        return domainName;
    }

    public boolean deleteChallenge(Long challengeId, Long userId) {
        // Fetch the instance and verify the user

        Instance instance = instanceRepository.findByChallengeIdAndUserId(challengeId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Instance not found"));


        Challenge challenge = instance.getChallenge();
        String deploymentName = challenge.getName();

        try {
            // Delete the Kubernetes resources in reverse order
            kubernetesService.deleteIngress("default", deploymentName);
            System.out.println("Ingress deleted successfully: " + deploymentName);

            kubernetesService.deleteService("default", deploymentName);
            System.out.println("Service deleted successfully: " + deploymentName);

            kubernetesService.deleteDeployment("default", deploymentName);
            System.out.println("Deployment deleted successfully: " + deploymentName);

            // Remove domain if needed
            dnsService.removeDomain(deploymentName + ".htb.com");

            // Delete the instance from the database
            instanceRepository.delete(instance);

            return true;
        } catch (Exception e) {
            System.err.println("Failed to delete resources: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Deletion failed: " + e.getMessage());
        }
    }
}
