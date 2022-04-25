package com.example.demo.channels;

import com.example.demo.pack.packRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class channelService {
    @Autowired
   // private com.example.demo.pack.packService packService;
    private ChannelRepository channelrepository;
    public void saveToDatabase(String name, String type, Double price, Integer pack_id){
       channel a= new channel(name, type, price, pack_id);
        channelrepository.save(a);
    }
    public List<channel> getAllChannels(){
        List<channel> test = new ArrayList<>();
        channelrepository.findAll().forEach(test::add);
        return test;
    }
    public List<channel> getchannelByName(String id){
     return  channelrepository.findByname(id);
    }

    public void deleteByname(String name){
        channelrepository.deleteByname(name);
    }
    public void deleteall(){
        channelrepository.deleteAll();
    }
    public void addABunch(){
       channelrepository.save(new channel("name","news",155.12,20));

    }

    public List<channel> manytomany(Integer id){
        return channelrepository.withCertainId(id);
    }


    public channel savePost (channel channel){
        return channelrepository.save(channel);
    }

    public channel getone(int id)  {
        return channelrepository.findById(id).get(0);
    }
    public List<channel> getByCategoy(String cat){
        return channelrepository.withCat(cat);
    }

    public channel update(channel channel ){
        return channelrepository.save(channel);
    }

    public void delete(int id) {
        channelrepository.deleteById(id);
    }

    public channel getchannelById(int id) {
        return channelrepository.findById(id).get(0);
    }

    public List<channel> getInContract(int id) {
        return channelrepository.getInContract(id);
    }
}
