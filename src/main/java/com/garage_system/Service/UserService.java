package com.garage_system.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.garage_system.DTO.UserDto;
import com.garage_system.Model.User;
import com.garage_system.Repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
      public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
         this.userRepository  = userRepository;
         this.passwordEncoder = passwordEncoder;
    }

     public void RegisterUser(UserDto userDto) {
          
          userRepository.findByEmail(userDto.getEmail()).ifPresent(exception -> {
               throw new IllegalStateException("Email is already existed");
          });
          
          
          User newUser = new User();

          newUser.setName(userDto.getName());
          newUser.setEmail(userDto.getEmail());
          newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
          newUser.setCreatedAt(java.time.LocalDate.now());
         
        
      
     }

}
