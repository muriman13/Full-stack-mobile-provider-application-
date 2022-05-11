package com.example.demo.controllers;


import com.example.demo.entities.Providers;
import com.example.demo.services.ProvidersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/providers/")
public class ProvidersController {

    private final ProvidersService providersService;


    public ProvidersController(ProvidersService providersService) {
        this.providersService = providersService;
    }

    @GetMapping("getbyId/{id}")
    public ResponseEntity<Providers> getProviders(@PathVariable int id){
        return new ResponseEntity<>(providersService.FindById(id), HttpStatus.OK);
    }
    @PostMapping("addProvider")
    public ResponseEntity<Providers> save (@Valid @RequestBody Providers providers){
       return new ResponseEntity<>(providersService.save(providers), HttpStatus.CREATED);

    }
    @PutMapping("addto/{id}/{channel_id}")

    public ResponseEntity<Providers> addChannel (@PathVariable int id, @PathVariable int channel_id) {
        return new ResponseEntity<>(providersService.save(providersService.addChannel(id,channel_id)), HttpStatus.OK);
    }
    @GetMapping("getall")
    public ResponseEntity<List<Providers>> getAll(){
        return new ResponseEntity<>( providersService.getAll(), HttpStatus.OK);
    }

    @GetMapping("update/{id}/{percent}")
    public ResponseEntity<Void> updatePrice(@PathVariable int id, @PathVariable double percent){
        providersService.updatePrice(id, percent);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
