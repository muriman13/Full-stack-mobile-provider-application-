package com.example.demo.controllers;

import com.example.demo.DTOs.ClientDTO;
import com.example.demo.DTOs.Mapper;
import com.example.demo.entities.Clients;
import com.example.demo.services.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/clients/")
public class ClientController {

    private final ClientsService clientsService;
    private final Mapper mapper;

    public ClientController(ClientsService clientsService, Mapper mapper) {
        this.clientsService = clientsService;
        this.mapper = mapper;
    }

    @GetMapping("getall")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        return  new ResponseEntity<>(clientsService.getAll().stream().map(mapper::toDto).collect(toList()), HttpStatus.OK);
    }
    @PostMapping("save")
    public ResponseEntity<ClientDTO> saveclient(@Valid @RequestBody Clients clients){
        clientsService.save(clients);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/payments/{client_id}")
    public ResponseEntity<Double> payments(@PathVariable int client_id){
        return new ResponseEntity<>(clientsService.allMadePayments(client_id),HttpStatus.OK);
    }

}
