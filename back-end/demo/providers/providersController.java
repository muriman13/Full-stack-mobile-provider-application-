package com.example.demo.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class providersController {
    @Autowired
    private com.example.demo.providers.providersService providersService;

}
