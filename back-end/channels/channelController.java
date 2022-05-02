package com.example.demo.channels;

import com.example.demo.exceptions.ApiRequestExeptions;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.pack.pack;
import com.example.demo.pack.packService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@RestController
public class channelController {
    @Autowired
     channelService channelService;
    @Autowired
     packService packService;
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @RequestMapping("/save/{name}/{type}/{price}/{pack_id}")
    public void save(@PathVariable String name, @PathVariable String type, @PathVariable double price, @PathVariable Integer pack_id){
        channelService.saveToDatabase(name,type,price,pack_id);
    }
    @GetMapping("/show")
    public List<channel> showAll(){
        if(channelService.getAllChannels().isEmpty()){
            throw  new ApiRequestExeptions("No User could be loaded at this time");
        }
      return  channelService.getAllChannels();
    }
//    @GetMapping("/show/name/{id}")
//        public List<channel> getChannelso(@PathVariable String id) {
//            return channelService.getchannelByName(id);
//        }
    @GetMapping("/show/{id}")

    public channel getChannel(@PathVariable int id) {
    if(!(channelService.containsKye(id))){
        throw new NoEntityFound("not found");
    }
        return channelService.getchannelById(id);
    }
        @GetMapping ("/delete/{name}")
        @Transactional
    public void deleteid(@PathVariable String name){
        channelService.deleteByname(name);
        }
        @GetMapping("deleteAllNull")
    @Transactional
    public void deleteall(){
        channelService.deleteall();
        }
        @RequestMapping("killme")
    public void savealot(){
        channelService.addABunch();
        }
//        @PostMapping("/update")
//    public void update(String name){
//        channelService.updateByName(name);
//        }


    @RequestMapping("/savePost")
    public channel saveWithPost(@RequestBody channel channel){
        return channelService.savePost(channel);
    }



    @GetMapping("/request/{id}")
    public List<channel> getWith(@PathVariable int id){

        return  channelService.manytomany(id);
    }



    @GetMapping("/category/{cat}")
    public List<channel> channelWithCattegoru(@PathVariable String cat){
        return channelService.getByCategoy(cat);
    }

    @PutMapping("/update")
    public channel updateChnannel(@RequestBody channel Oldchannel){
        channel channel = channelService.update(Oldchannel);
        return channel;
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void delete (@PathVariable int id){
        if(!(channelService.containsKye(id))){
            throw new NoEntityFound("not found");
        }

        channelService.delete(id);
    }

}
