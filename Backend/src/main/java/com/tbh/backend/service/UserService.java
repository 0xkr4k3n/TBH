package com.tbh.backend.service;


import com.tbh.backend.dto.UserDTO;
import com.tbh.backend.entity.User;
import com.tbh.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getCreatedAt());
    }
    private User mapToEntity(UserDTO userDTO) {
        return new User( userDTO.getId(), userDTO.getUsername(), userDTO.getCreatedAt());
    }
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDTO);
    }

    
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = mapToEntity(userDTO);
        user.setId(id); 
        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
