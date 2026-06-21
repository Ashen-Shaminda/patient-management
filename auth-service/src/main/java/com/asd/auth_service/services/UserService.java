package com.asd.auth_service.services;

import com.asd.auth_service.domain.entities.User;

import java.util.Optional;

public interface UserService  {
   Optional<User> findByEmail(String email);
}
