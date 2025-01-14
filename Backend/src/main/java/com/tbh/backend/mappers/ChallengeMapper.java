package com.tbh.backend.mappers;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.dto.IntanceDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.entity.Instance;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {


    public ChallengeDTO mapToDTO(Challenge challenge) {
        return new ChallengeDTO(
                challenge.getId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getDifficulty(),
                challenge.getSolves(),
                challenge.getCreatedAt(),
                challenge.getPoints()
        );
    }


    public Challenge mapToEntity(ChallengeDTO challengeDTO) {
        return new Challenge(
                challengeDTO.getId(),
                challengeDTO.getName(),
                challengeDTO.getDescription(),
                challengeDTO.getDifficulty(),
                challengeDTO.getSolves(),
                challengeDTO.getCreatedAt(),
                challengeDTO.getCategory(),
                challengeDTO.getPoints(),
                challengeDTO.isSolved()
        );
    }



}
