package com.example.demo.controllers;

import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.entities.Providers;
import com.example.demo.services.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers/")
public class ProvidersController {
    @Autowired
    private ProvidersService providersService;
    @Autowired
    private ChannelService channelService;
    @GetMapping("getbyId/{id}")
    public ResponseEntity<Providers> getproviders(@PathVariable int id){
        Providers provider = providersService.FindById(id);
        return new ResponseEntity<Providers>(provider, HttpStatus.OK);
    }
    @PostMapping("addProvider")
    public ResponseEntity<Providers> save (@RequestBody Providers providers){
       Providers savedProvider = providersService.save(providers);
       return new ResponseEntity<Providers>(savedProvider, HttpStatus.CREATED);

    }
    @PutMapping("addto/{id}/{channel_id}")

    public ResponseEntity<Providers> addChannel (@PathVariable int id, @PathVariable int channel_id) throws Exception {
        Providers providers = providersService.FindById(id);
        Channel channel = channelService.getone(channel_id);
        channel.setProviders(providers);
        providers.addChannel(channel);
        Providers savedProvider = providersService.save(providers);
        return new ResponseEntity<Providers>(savedProvider, HttpStatus.OK);
    }
    @GetMapping("getall")
    public ResponseEntity<List<Providers>> getall(){
        List<Providers> providers = providersService.getall();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("update/{id}/{percent}")
    public ResponseEntity<?> updatePrice(@PathVariable int id, @PathVariable double percent){
        providersService.updateprice(id, percent);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
