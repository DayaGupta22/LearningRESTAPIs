package com.TechieSpring.LearningRESTAPis.dto;

import com.TechieSpring.LearningRESTAPis.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserDto user;
    private String token;
}
