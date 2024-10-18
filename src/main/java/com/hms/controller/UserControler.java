package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.repository.AppUserRepository;
import com.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserControler {

    private UserService userService;
    private AppUserRepository appUserRepository;

    public UserControler(UserService userService, AppUserRepository appUserRepository) {
        this.userService = userService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createSignup(
            @RequestBody AppUserDto appUserDto
    ){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(appUserDto.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUserDto.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encodedPassword = BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(4));
        appUserDto.setPassword(encodedPassword);
        AppUserDto appDto = userService.create(appUserDto);
        return new ResponseEntity<>(appDto, HttpStatus.CREATED);
    }
}
