package com.garage_system.Service;

import org.springframework.stereotype.Service;

import com.garage_system.DTO.UserDto;
import com.garage_system.Model.User;
import com.garage_system.Repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
     
     public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     public void RegisterUser(UserDto user) {
          User newUser = new User();

          newUser.setName(user.getName());
          newUser.setEmail(user.getEmail());
          newUser.setPassword(user.getPassword());
          newUser.setCreatedAt(java.time.LocalDate.now());
          
          userRepository.save(newUser);
     }

}
