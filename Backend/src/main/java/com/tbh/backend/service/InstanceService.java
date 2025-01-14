package com.tbh.backend.service;

import com.tbh.backend.dto.IntanceDTO;
import com.tbh.backend.entity.Instance;
import com.tbh.backend.mappers.InstanceMapper;
import com.tbh.backend.repository.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    
    public List<IntanceDTO> getAllInstances() {
        return instanceRepository.findAll().stream()
                .map(instanceMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<IntanceDTO> getInstanceById(Long id) {
        return instanceRepository.findById(id)
                .map(instanceMapper::mapToDTO);
    }

    
    public IntanceDTO createInstance(IntanceDTO intanceDTO) {
        Instance instance = instanceMapper.mapToEntity(intanceDTO);
        Instance savedInstance = instanceRepository.save(instance);
        return instanceMapper.mapToDTO(savedInstance);
    }

    
    public IntanceDTO updateInstance(Long id, IntanceDTO intanceDTO) {
        if (!instanceRepository.existsById(id)) {
            throw new RuntimeException("Instance not found with id: " + id);
        }
        Instance instance = instanceMapper.mapToEntity(intanceDTO);
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
