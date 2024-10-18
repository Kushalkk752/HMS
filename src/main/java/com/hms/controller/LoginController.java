package com.hms.controller;

import com.hms.payload.LoginDto;
import com.hms.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> createLogin(
            @RequestBody LoginDto dto
    ){
        String token = loginService.verifyLogin(dto);
        if(token!=null){
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid Username/Password",HttpStatus.FORBIDDEN);
        }
    }
}
