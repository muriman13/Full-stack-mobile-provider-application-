package com.example.demo.controllers;

import com.example.demo.DTOs.ClientDTO;
import com.example.demo.DTOs.Mapper;
import com.example.demo.entities.Clients;
import com.example.demo.services.ClientsService;
import com.example.demo.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/clients/")
public class ClientController {
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private ContractService contractService; //do with bean configuration
    @Autowired
    private Mapper mapper;

    @GetMapping("getall")
    public ResponseEntity<List<ClientDTO>> getallClients(){
        return  new ResponseEntity<>(clientsService.getAll().stream().map(mapper::toDto).collect(toList()), HttpStatus.OK);
    }
    @PostMapping("save")
    public ResponseEntity<?> saveclient(@RequestBody Clients clients){
        clientsService.save(clients);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/payments/{client_id}")
    public ResponseEntity<Double> payments(@PathVariable int client_id){
        return new ResponseEntity<>(clientsService.allMadePayments(client_id),HttpStatus.OK);
    }

}
