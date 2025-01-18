package com.tbh.backend.controller;

import com.tbh.backend.dto.InstanceDTO;
import com.tbh.backend.service.InstanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instances")
public class InstanceController {
    private final InstanceService instanceService;

    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    
    @GetMapping
    public List<InstanceDTO> getAllInstances() {
        return instanceService.getAllInstances();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<InstanceDTO> getInstanceById(@PathVariable Long id) {
        Optional<InstanceDTO> instance = instanceService.getInstanceById(id);
        return instance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<InstanceDTO> createInstance(@RequestBody InstanceDTO instanceDTO) {
        InstanceDTO createdInstance = instanceService.createInstance(instanceDTO);
        return ResponseEntity.ok(createdInstance);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<InstanceDTO> updateInstance(@PathVariable Long id, @RequestBody InstanceDTO instanceDTO) {
        try {
            InstanceDTO updatedInstance = instanceService.updateInstance(id, instanceDTO);
            return ResponseEntity.ok(updatedInstance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstance(@PathVariable Long id) {
        try {
            instanceService.deleteInstance(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
