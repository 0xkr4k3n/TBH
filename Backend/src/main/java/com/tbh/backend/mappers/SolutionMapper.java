package com.tbh.backend.mappers;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.dto.SolutionDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.entity.Solution;
import org.springframework.stereotype.Component;

@Component
public class SolutionMapper {

    public SolutionDTO convertToDTO(Solution solution) {
        return new SolutionDTO(
                solution.getId(),
                solution.getUser().getId(),
                solution.getUser().getUsername(),
                solution.getChallenge().getId(),
                solution.getChallenge().getName(),
                solution.getSolvedAt()
        );
    }



}
