package com.tbh.backend.mappers;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.entity.Category;
import com.tbh.backend.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    public ChallengeDTO mapToDTO(Challenge challenge) {
        return new ChallengeDTO(
                challenge.getId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getDifficulty(),
                challenge.getSolves(),
                challenge.getCreatedAt(),
                challenge.getPoints(),
                challenge.getCategory(),
                challenge.getPath()
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
                challengeDTO.getPoints(),
                challengeDTO.getCategory(),
                challengeDTO.getPath()
        );
    }



}
