package com.example.demo.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class providersService {

    @Autowired
    private com.example.demo.providers.providersRepo providersRepo;
}
