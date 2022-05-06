package com.example.demo.controllers;

import com.example.demo.ChannelModelAssembler;
import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import org.hibernate.type.CharacterNCharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ChannelController {
    @Autowired
    ChannelService channelService;
    @Autowired
    ChannelModelAssembler channelModelAssembler;
    @RequestMapping("/save/{name}/{type}/{price}/{pack_id}")
    public ResponseEntity<?> save(@PathVariable String name, @PathVariable String type, @PathVariable double price, @PathVariable Integer pack_id){
        channelService.saveToDatabase(name,type,price,pack_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/show")
    public ResponseEntity<List<Channel>> showAll(){
        List<Channel> channels = channelService.getAllChannels();
      return new ResponseEntity<>(channels, HttpStatus.OK);
    }

    @GetMapping("/show/{id}")

    public ResponseEntity<Channel> getChannel(@PathVariable int id) {

        Channel channel  = channelService.getchannelById(id);
        return new ResponseEntity<>(channel, HttpStatus.ACCEPTED);
    }

        @GetMapping ("/delete/{name}")
        @Transactional
    public ResponseEntity<?> deleteid(@PathVariable String name){
        channelService.deleteByname(name);
        return new ResponseEntity<>(HttpStatus.OK);
        }
        @GetMapping("deleteAllNull")
    @Transactional
    public ResponseEntity<?> deleteall(){
        channelService.deleteall();
        return new ResponseEntity<>(HttpStatus.OK);
        }

    @PostMapping("/savePost")
    public ResponseEntity<Channel> saveWithPost(@RequestBody Channel channel){
        Channel savedChannel = channelService.savePost(channel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Channel>> channelWithCattegoru(@PathVariable String category){
        List<Channel> channels = channelService.getByCategoy(category);
        return new ResponseEntity<>(channels, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Channel> updateChnannel(@RequestBody Channel Oldchannel){
        Channel channel = channelService.update(Oldchannel);
        return new ResponseEntity<Channel>(channel,HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){

        channelService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
