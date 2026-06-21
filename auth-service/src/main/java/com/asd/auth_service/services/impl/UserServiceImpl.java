package com.asd.auth_service.services.impl;

import com.asd.auth_service.domain.entities.User;
import com.asd.auth_service.repositories.UserRepository;
import com.asd.auth_service.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public Optional<User> findByEmail(String email) {
      return userRepository.findByEmail(email);
   }
}
