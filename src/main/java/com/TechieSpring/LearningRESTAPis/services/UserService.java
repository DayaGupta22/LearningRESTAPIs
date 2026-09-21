package com.TechieSpring.LearningRESTAPis.services;

import com.TechieSpring.LearningRESTAPis.dto.AuthResponse;
import com.TechieSpring.LearningRESTAPis.dto.SignUpDto;
import com.TechieSpring.LearningRESTAPis.dto.UserDto;
import com.TechieSpring.LearningRESTAPis.entities.User;
import com.TechieSpring.LearningRESTAPis.exceptions.ResourceNotFoundException;
import com.TechieSpring.LearningRESTAPis.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("USER NOT FOUND with email" + username+ "not found"));
    }


    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("USER NOT FOUND with id" + userId+ "not found"));
    }


    public AuthResponse signUp(SignUpDto signUpDto) {
        Optional<User> user =
                userRepository.findByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new BadCredentialsException(
                    "User already present " + signUpDto.getEmail());
        }
        User toCreate = modelMapper.map(signUpDto, User.class);
        toCreate.setPassword(
                passwordEncoder.encode(signUpDto.getPassword())
        );
        User savedUser = userRepository.save(toCreate);
        String token = jwtService.generateToken(savedUser);
        UserDto userDto = modelMapper.map(savedUser, UserDto.class);
        return new AuthResponse(userDto, token);
    }


}
