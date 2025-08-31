package com.garage_system.Controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garage_system.DTO.UserDto;
import com.garage_system.Model.User;
import com.garage_system.Service.JWTService;
import com.garage_system.Service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {
    
    private final UserService userService;
    private final JWTService jwtService;

    public UserController(UserService userService , JWTService jwtService  ) {
        this.userService = userService;
        this.jwtService  = jwtService;
    }
    
    @PostMapping("/Register")
    public void register(@RequestBody @Valid UserDto user) {
          userService.RegisterUser(user);
          
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid User user) {
       return jwtService.verify(user) ;
    }


    
}
