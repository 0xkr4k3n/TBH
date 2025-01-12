package com.tbh.backend.controller;

import com.tbh.backend.dto.SolutionDTO;
import com.tbh.backend.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @GetMapping
    public ResponseEntity<List<SolutionDTO>> getAllSolutions() {
        List<SolutionDTO> solutions = solutionService.getAllSolutions();
        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SolutionDTO> getSolutionById(@PathVariable Long id) {
        SolutionDTO solution = solutionService.getSolutionById(id);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SolutionDTO> createSolution(
            @RequestParam Long userId,
            @RequestParam Long challengeId,
            @RequestParam Date solvedAt) {
        SolutionDTO newSolution = solutionService.createSolution(userId, challengeId, solvedAt);
        return new ResponseEntity<>(newSolution, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SolutionDTO> updateSolution(
            @PathVariable Long id,
            @RequestParam Date solvedAt) {
        SolutionDTO updatedSolution = solutionService.updateSolution(id, solvedAt);
        return new ResponseEntity<>(updatedSolution, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolution(@PathVariable Long id) {
        solutionService.deleteSolution(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
