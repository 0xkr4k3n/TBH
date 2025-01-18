package com.tbh.backend.service;

import com.tbh.backend.dto.InstanceDTO;
import com.tbh.backend.entity.Instance;
import com.tbh.backend.mappers.InstanceMapper;
import com.tbh.backend.repository.InstanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstanceService {
    private final InstanceRepository instanceRepository;
    private final InstanceMapper instanceMapper;

    public InstanceService(InstanceRepository instanceRepository, InstanceMapper instanceMapper) {
        this.instanceRepository = instanceRepository;
        this.instanceMapper = instanceMapper;
    }

    
    public List<InstanceDTO> getAllInstances() {
        return instanceRepository.findAll().stream()
                .map(instanceMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<InstanceDTO> getInstanceById(Long id) {
        return instanceRepository.findById(id)
                .map(instanceMapper::mapToDTO);
    }

    
    public InstanceDTO createInstance(InstanceDTO instanceDTO) {
        Instance instance = instanceMapper.mapToEntity(instanceDTO);
        Instance savedInstance = instanceRepository.save(instance);
        return instanceMapper.mapToDTO(savedInstance);
    }

    
    public InstanceDTO updateInstance(Long id, InstanceDTO instanceDTO) {
        if (!instanceRepository.existsById(id)) {
            throw new RuntimeException("Instance not found with id: " + id);
        }
        Instance instance = instanceMapper.mapToEntity(instanceDTO);
        instance.setId(id); 
        Instance updatedInstance = instanceRepository.save(instance);
        return instanceMapper.mapToDTO(updatedInstance);
    }

    
    public void deleteInstance(Long id) {
        if (instanceRepository.existsById(id)) {
            instanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Instance not found with id: " + id);
        }
    }
}
