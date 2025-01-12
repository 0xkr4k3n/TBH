package com.tbh.backend.repository;
import com.tbh.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    boolean existsById(Long id);

    void deleteById(Long id);

}
