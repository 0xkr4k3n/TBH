package com.tbh.backend.service;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    
    private ChallengeDTO mapToDTO(Challenge challenge) {
        return new ChallengeDTO(
                challenge.getId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getDifficulty(),
                challenge.getSolves(),
                challenge.getCreatedAt()
        );
    }

    
    private Challenge mapToEntity(ChallengeDTO challengeDTO) {
        return new Challenge(
                challengeDTO.getId(),
                challengeDTO.getName(),
                challengeDTO.getDescription(),
                challengeDTO.getDifficulty(),
                challengeDTO.getSolves(),
                challengeDTO.getCreatedAt()
        );
    }

    
    public List<ChallengeDTO> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<ChallengeDTO> getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .map(this::mapToDTO);
    }

    
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = mapToEntity(challengeDTO);
        Challenge savedChallenge = challengeRepository.save(challenge);
        return mapToDTO(savedChallenge);
    }

    
    public ChallengeDTO updateChallenge(Long id, ChallengeDTO challengeDTO) {
        if (!challengeRepository.existsById(id)) {
            throw new RuntimeException("Challenge not found with id: " + id);
        }
        Challenge challenge = mapToEntity(challengeDTO);
        challenge.setId(id); 
        Challenge updatedChallenge = challengeRepository.save(challenge);
        return mapToDTO(updatedChallenge);
    }

    
    public void deleteChallenge(Long id) {
        if (challengeRepository.existsById(id)) {
            challengeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Challenge not found with id: " + id);
        }
    }
}
