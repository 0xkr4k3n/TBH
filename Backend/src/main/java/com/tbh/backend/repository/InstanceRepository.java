package com.tbh.backend.repository;

import com.tbh.backend.entity.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface InstanceRepository extends JpaRepository<Instance, Long> {
    Instance save(Instance instance);

    boolean existsById(Long id);

    void deleteById(Long id);

}
