package com.tbh.backend.mappers;

import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.dto.UserDTO;
import com.tbh.backend.entity.Challenge;
import com.tbh.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getCountry(), user.getPoints(), user.getSolves(), user.getLastSolve(), user.getCreatedAt());
    }
    public User mapToEntity(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getCountry(), userDTO.getPoints(), userDTO.getSolves(), userDTO.getLastSolve(), userDTO.getCreatedAt());
    }


}
