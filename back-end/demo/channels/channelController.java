package com.example.demo.channels;

import com.example.demo.pack.pack;
import com.example.demo.pack.packService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
      return  channelService.getAllChannels();
    }
    @GetMapping("/show/{id}")
        public List<channel> getChannel(@PathVariable String id) {
            return channelService.getchannelByName(id);
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
    public List<channel> getWith(@PathVariable Integer id){
       return  channelService.manytomany(id);
    }

    @GetMapping("/category/{cat}")
    public List<channel> channelWithCattegoru(@PathVariable String cat){
        return channelService.getByCategoy(cat);
    }

}
