package com.example.demo.controllers;

import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import com.example.demo.exceptions.ApiRequestExeptions;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.entities.Pack;
import com.example.demo.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PackController {
    @Autowired
  private PackService packService;
   @Autowired
   private ChannelService channelService;
    @GetMapping("/sho")
    public ResponseEntity<List<Pack>> showAll(){
        List<Pack> packages = packService.getAllpacks();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }
    @GetMapping("/sho/{id}")
        public ResponseEntity<Pack> getpack(@PathVariable int id) {

            Pack pack = packService.getOne(id);
            return new ResponseEntity<Pack>(pack,HttpStatus.OK);
        }

        @GetMapping ("/delet/{name}")
        @Transactional
    public ResponseEntity<?> deleteid(@PathVariable String name){
        packService.deleteByname(name);
        return new ResponseEntity<>(HttpStatus.OK);
        }
    @RequestMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Pack> updatePack( @PathVariable Integer id){
        Pack pack = packService.updatePack("News",id);
        return new ResponseEntity<>(pack,HttpStatus.OK);
    }
    @PostMapping("/savePostPackage")
    public ResponseEntity<Pack> savePostPackage(@RequestBody Pack pack){
     Pack savedPack = packService.savePostPackage(pack);
     return new ResponseEntity<Pack>(savedPack, HttpStatus.OK);
    }
    @PutMapping("/addto/{id}/{channel_id}")
    public ResponseEntity<Pack> add_channel_to_package (@PathVariable int id, @PathVariable int channel_id) throws Exception {
        Pack pack = packService.getOne(id);
        Channel channel = channelService.getone(channel_id);
        pack.addchannel(channel);
        Pack updatedPack = packService.savePostPackage(pack);
        return new ResponseEntity<Pack>(updatedPack,HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/delete/package/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){
        packService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/package")
    public ResponseEntity<Pack> updatePackage(@RequestBody Pack Oldpack){
        Pack pack = packService.update(Oldpack);
        return new ResponseEntity<Pack>(pack,HttpStatus.OK);
    }
}
