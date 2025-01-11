package com.tbh.backend.controller;

import com.tbh.backend.dto.IntanceDTO;
import com.tbh.backend.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instances")
public class InstanceController {
    private final InstanceService instanceService;

    @Autowired
    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    
    @GetMapping
    public List<IntanceDTO> getAllInstances() {
        return instanceService.getAllInstances();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<IntanceDTO> getInstanceById(@PathVariable Long id) {
        Optional<IntanceDTO> instance = instanceService.getInstanceById(id);
        return instance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<IntanceDTO> createInstance(@RequestBody IntanceDTO intanceDTO) {
        IntanceDTO createdInstance = instanceService.createInstance(intanceDTO);
        return ResponseEntity.ok(createdInstance);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<IntanceDTO> updateInstance(@PathVariable Long id, @RequestBody IntanceDTO intanceDTO) {
        try {
            IntanceDTO updatedInstance = instanceService.updateInstance(id, intanceDTO);
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
