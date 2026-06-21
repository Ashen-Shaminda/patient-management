package com.asd.auth_service.services;

import com.asd.auth_service.domain.dtos.LoginRequestDTO;

import java.util.Optional;

public interface AuthService {
   Optional<String> authenticate(LoginRequestDTO loginRequestDTO);

   boolean validateToken(String token);
}
