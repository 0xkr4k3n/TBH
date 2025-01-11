package com.tbh.backend.controller;

import com.tbh.backend.service.ChallengeService;
import com.tbh.backend.dto.ChallengeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    
    @GetMapping
    public List<ChallengeDTO> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ChallengeDTO> getChallengeById(@PathVariable Long id) {
        Optional<ChallengeDTO> challenge = challengeService.getChallengeById(id);
        return challenge.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<ChallengeDTO> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        ChallengeDTO createdChallenge = challengeService.createChallenge(challengeDTO);
        return ResponseEntity.ok(createdChallenge);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<ChallengeDTO> updateChallenge(@PathVariable Long id, @RequestBody ChallengeDTO challengeDTO) {
        try {
            ChallengeDTO updatedChallenge = challengeService.updateChallenge(id, challengeDTO);
            return ResponseEntity.ok(updatedChallenge);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        try {
            challengeService.deleteChallenge(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}