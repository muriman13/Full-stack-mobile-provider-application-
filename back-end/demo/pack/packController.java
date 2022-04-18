package com.example.demo.pack;

import com.example.demo.channels.channel;
import com.example.demo.channels.channelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class packController {
    @Autowired
  private   packService packService;
   @Autowired
   private  channelService channelService;
    @GetMapping("/tes")
    public String test(){
        return "test";
    }
    @RequestMapping("/sav")
    public void save(){
        packService.saveToDatabase();
    }
    @GetMapping("/sho")
    public List<pack> showAll(){
      return  packService.getAllpacks();
    }
    @GetMapping("/sho/{id}")
        public List<pack> getpack(@PathVariable String id) {
            return packService.getpackByName(id);
        }

        @GetMapping ("/delet/{name}")
        @Transactional
    public void deleteid(@PathVariable String name){
        packService.deleteByname(name);
        }
    @RequestMapping("/update/{id}")
    @Transactional
    public void updatePack( @PathVariable Integer id){
        packService.updatePack("News",id);
    }
    @RequestMapping("/savePostPackage")
    public pack savePostPackage(@RequestBody pack pack){
     return packService.savePostPackage(pack);
    }
    @PutMapping("/addto/{id}/{channel_id}")
    public pack add_channel_to_package (@PathVariable String id, @PathVariable int channel_id) throws Exception {

        pack pack = packService.getpackByName(id).get(0);
        channel channel = channelService.getone(channel_id);
        pack.addchannel(channel);
        return packService.savePostPackage(pack);
    }
}
