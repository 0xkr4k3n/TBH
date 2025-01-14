package com.tbh.backend.service;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.mappers.ChallengeMapper;
import com.tbh.backend.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ChallengeMapper challengeMapper;
    public ChallengeService(ChallengeRepository challengeRepository, ChallengeMapper challengeMapper) {
        this.challengeRepository = challengeRepository;
        this.challengeMapper = challengeMapper;
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
}
