package com.example.demo.controllers;

import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ChannelController {
    private ChannelService channelService;
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Channel>> showAll(){
      return new ResponseEntity<>(channelService.getAllChannels(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")

    public ResponseEntity<Channel> getChannel(@PathVariable int id) {
        return new ResponseEntity<>(channelService.getchannelById(id), HttpStatus.ACCEPTED);
    }

        @GetMapping ("/delete/{name}")
        @Transactional
    public ResponseEntity<Void> deleteid(@PathVariable String name){
        channelService.deleteByname(name);
        return new ResponseEntity<>(HttpStatus.OK);
        }
        @GetMapping("deleteAllNull")
    @Transactional
    public ResponseEntity<Void> deleteall(){
        channelService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
        }

    @PostMapping("/savePost")
    public ResponseEntity<Channel> saveWithPost(@Valid @RequestBody Channel channel){
        channelService.savePost(channel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Channel>> channelWithCattegoru(@PathVariable String category){
        return new ResponseEntity<>(channelService.getByCategoy(category), HttpStatus.OK);
    }

    @GetMapping("/request/{id}")
    public List<Channel> getWith(@PathVariable int id){
        return  channelService.manytomany(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Channel> updateChnannel(@Valid @RequestBody Channel Oldchannel){
        return new ResponseEntity<>(channelService.update(Oldchannel),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id){
        channelService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
