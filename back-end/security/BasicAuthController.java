package com.example.demo.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthController {

    @GetMapping(path = "")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
}