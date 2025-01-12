package com.tbh.backend.service;

import com.tbh.backend.dto.IntanceDTO;
import com.tbh.backend.entity.Instance;
import com.tbh.backend.repository.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class InstanceService {
    private InstanceRepository instanceRepository;

    public InstanceService(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }


    private IntanceDTO mapToDTO(Instance instance) {
        return new IntanceDTO(
                instance.getId(),
                instance.getIp(),
                instance.getPort()
        );
    }

    
    private Instance mapToEntity(IntanceDTO intanceDTO) {
        return new Instance(
                intanceDTO.getId(),
                intanceDTO.getIp(),
                intanceDTO.getPort()
        );
    }

    
    public List<IntanceDTO> getAllInstances() {
        return instanceRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<IntanceDTO> getInstanceById(Long id) {
        return instanceRepository.findById(id)
                .map(this::mapToDTO);
    }

    
    public IntanceDTO createInstance(IntanceDTO intanceDTO) {
        Instance instance = mapToEntity(intanceDTO);
        Instance savedInstance = instanceRepository.save(instance);
        return mapToDTO(savedInstance);
    }

    
    public IntanceDTO updateInstance(Long id, IntanceDTO intanceDTO) {
        if (!instanceRepository.existsById(id)) {
            throw new RuntimeException("Instance not found with id: " + id);
        }
        Instance instance = mapToEntity(intanceDTO);
        instance.setId(id); 
        Instance updatedInstance = instanceRepository.save(instance);
        return mapToDTO(updatedInstance);
    }

    
    public void deleteInstance(Long id) {
        if (instanceRepository.existsById(id)) {
            instanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Instance not found with id: " + id);
        }
    }
}
