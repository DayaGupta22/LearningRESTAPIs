package com.TechieSpring.LearningRESTAPis.controllers;

import com.TechieSpring.LearningRESTAPis.dto.AuthResponse;
import com.TechieSpring.LearningRESTAPis.dto.LoginDto;
import com.TechieSpring.LearningRESTAPis.dto.SignUpDto;

import com.TechieSpring.LearningRESTAPis.services.AuthService;
import com.TechieSpring.LearningRESTAPis.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private  final AuthService authService;

   @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpDto signUpDto) {
       AuthResponse response = userService.signUp(signUpDto);
       System.out.println(response);
       return ResponseEntity.ok(response);
   }

   @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto, HttpServletRequest request,HttpServletResponse response) {
       String token = authService.login(loginDto);
       Cookie cookie=new Cookie("token",token);
       cookie.setHttpOnly(true);
       response.addCookie(cookie);
     return  token;
   }
}
