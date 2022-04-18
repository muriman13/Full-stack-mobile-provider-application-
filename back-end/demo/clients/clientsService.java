package com.example.demo.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class clientsService {

    @Autowired
    private com.example.demo.clients.clientsRepo clientsRepo;
}
