package com.example.demo.controllers;

import com.example.demo.services.ChannelService;
import com.example.demo.entities.Pack;
import com.example.demo.services.PackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PackController {

  private final PackService packService;

    public PackController(PackService packService) {
        this.packService = packService;
    }


    @GetMapping("/sho")
    public ResponseEntity<List<Pack>> showAll(){
        return new ResponseEntity<>(packService.getAllpacks(), HttpStatus.OK);
    }
    @GetMapping("/sho/{id}")
        public ResponseEntity<Pack> getPack(@PathVariable int id) {
            return new ResponseEntity<>(packService.getOne(id),HttpStatus.OK);
        }

        @GetMapping ("/delet/{name}")
        @Transactional
    public ResponseEntity<?> deleteId(@PathVariable String name){
        packService.deleteByname(name);
        return new ResponseEntity<>(HttpStatus.OK);
        }
    @RequestMapping("/update/{id}")
    public ResponseEntity<Pack> updatePack( @PathVariable Integer id){
        return new ResponseEntity<>(packService.updatePack("News",id),HttpStatus.OK);
    }
    @PostMapping("/savePostPackage")
    public ResponseEntity<Pack> savePostPackage(@Valid @RequestBody Pack pack){
     return new ResponseEntity<Pack>(packService.savePostPackage(pack), HttpStatus.OK);
    }
    @PutMapping("/addto/{id}/{channel_id}")
    public ResponseEntity<Pack> addChannelToPackage(@PathVariable int id, @PathVariable int channel_id)  {
        return new ResponseEntity<Pack>(packService.savePostPackage(packService.addChannelToPackage(id,channel_id)),HttpStatus.OK);
    }
    @DeleteMapping("/delete/package/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){
        packService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/package")
    public ResponseEntity<Pack> updatePackage(@Valid @RequestBody Pack oldPack){
        return new ResponseEntity<>(packService.update(oldPack),HttpStatus.OK);
    }
    @PostMapping("/add/{category}")
    public ResponseEntity<Pack> createPackageByCategory(@Valid @RequestBody Pack pack, @PathVariable String category){
        return new ResponseEntity<>(packService.categoryPackage(pack,category),HttpStatus.CREATED);
    }

    @PutMapping("/updatePack/{category}")
    public ResponseEntity<Pack> updatePackageByCategory(@Valid @RequestBody Pack pack,@PathVariable String category){
        return new ResponseEntity<>(packService.categoryPackage(pack,category),HttpStatus.CREATED);
    }

}
