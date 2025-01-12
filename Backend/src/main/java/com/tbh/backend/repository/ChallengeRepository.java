package com.tbh.backend.repository;

import com.tbh.backend.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Challenge save(Challenge challenge);

    boolean existsById(Long id);

    void deleteById(Long id);


}
