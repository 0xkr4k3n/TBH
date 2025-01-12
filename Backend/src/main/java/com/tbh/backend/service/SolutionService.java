package com.tbh.backend.service;

import com.tbh.backend.dto.SolutionDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.entity.Solution;
import com.tbh.backend.entity.User;
import com.tbh.backend.repository.ChallengeRepository;
import com.tbh.backend.repository.SolutionRepository;
import com.tbh.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    public List<SolutionDTO> getAllSolutions() {
        List<Solution> solutions = solutionRepository.findAll();
        return solutions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public SolutionDTO getSolutionById(Long id) {
        Solution solution = solutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solution not found with id: " + id));
        return convertToDTO(solution);
    }
    public SolutionDTO createSolution(Long userId, Long challengeId, Date solvedAt) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + challengeId));

        Solution solution = new Solution();
        solution.setUser(user);
        solution.setChallenge(challenge);
        solution.setSolvedAt(solvedAt);

        Solution savedSolution = solutionRepository.save(solution);
        return convertToDTO(savedSolution);
    }


    public SolutionDTO updateSolution(Long id, Date newSolvedAt) {
        Solution solution = solutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solution not found with id: " + id));

        solution.setSolvedAt(newSolvedAt);

        Solution updatedSolution = solutionRepository.save(solution);
        return convertToDTO(updatedSolution);
    }


    public void deleteSolution(Long id) {
        if (!solutionRepository.existsById(id)) {
            throw new RuntimeException("Solution not found with id: " + id);
        }
        solutionRepository.deleteById(id);
    }


    private SolutionDTO convertToDTO(Solution solution) {
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
