package com.asd.auth_service.services.impl;

import com.asd.auth_service.domain.dtos.LoginRequestDTO;
import com.asd.auth_service.services.AuthService;
import com.asd.auth_service.services.UserService;
import com.asd.auth_service.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
   private final UserService userService;
   private final PasswordEncoder passwordEncoder;
   private final JwtUtil jwtUtil;

   public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
      this.userService = userService;
      this.passwordEncoder = passwordEncoder;
      this.jwtUtil = jwtUtil;
   }

   public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
      Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
              .filter(user -> passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword()))
              .map(user -> jwtUtil.generateToken(user.getEmail(), user.getRole()));

      return token;
   }

   public boolean validateToken(String token) {
      try {
         jwtUtil.validateToken(token);

         return true;
      } catch (JwtException e) {
         return false;
      }
   }
}
