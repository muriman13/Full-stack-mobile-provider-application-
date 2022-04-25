package com.example.demo.providers;

import com.example.demo.channels.channel;
import com.example.demo.channels.channelService;
import com.example.demo.pack.pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/providers/")
public class providersController {
    @Autowired
    private com.example.demo.providers.providersService providersService;
    @Autowired
    private channelService channelService;
    @GetMapping("getbyId/{id}")
    public providers getproviders(@PathVariable int id){
        return providersService.FindById(id);
    }
    @PostMapping("addProvider")
    public void save (@RequestBody providers providers){
        providersService.save(providers);

    }
    @PutMapping("addto/{id}/{channel_id}")
    public providers addChannel (@PathVariable int id, @PathVariable int channel_id) throws Exception {
        providers providers = providersService.FindById(id);
        channel channel = channelService.getone(channel_id);
        channel.setProviders(providers);
        providers.addChannel(channel);
        return providersService.save(providers);
    }
    @GetMapping("getall")
    public List<providers> getall(){
        return providersService.getall();
    }

    @GetMapping("update/{id}/{percent}")
    public void updatePrice(@PathVariable int id, @PathVariable double percent){
        providersService.updateprice(id, percent);
    }


}
