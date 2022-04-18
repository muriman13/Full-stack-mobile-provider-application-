package com.example.demo.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class clientController {
    @Autowired
    private com.example.demo.clients.clientsService clientsService;

}
