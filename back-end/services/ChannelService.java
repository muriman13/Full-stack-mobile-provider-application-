package com.example.demo.services;

import com.example.demo.exceptions.ApiRequestExeptions;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.repositories.ChannelRepository;
import com.example.demo.entities.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
   // private com.example.demo.services.packService packService;
    private ChannelRepository channelrepository;
    public void saveToDatabase(String name, String type, Double price, Integer pack_id){
       Channel a= new Channel(name, type, price, pack_id);
        channelrepository.save(a);
    }
    public List<Channel> getAllChannels(){

        List<Channel> channels = new ArrayList<>();
        channelrepository.findAll().forEach(channels::add);

        if(channels.isEmpty()){
            throw  new ApiRequestExeptions("No User could be loaded at this time");
        }
        return channels;
    }
    public boolean containsKey(int id){
        return channelrepository.existsById(id);
    }
    public List<Channel> getchannelByName(String id){
     return  channelrepository.findByname(id).orElseThrow(() -> new NoEntityFound("channels with name: " + id + "were not found"));
    }

    public void deleteByname(String name){
        channelrepository.deleteByname(name);
    }
    public void deleteAll(){
        channelrepository.deleteAll();
    }

    public List<Channel> manytomany(Integer id){
        return channelrepository.withCertainId(id).orElseThrow(()-> new NoEntityFound("No channels with that category"));
    }

    public Channel savePost (Channel channel){
        return channelrepository.save(channel);
    }

    public Channel getOne(int id)  {
        return channelrepository.findById(id).orElseThrow(() -> new NoEntityFound("channel with id: " + id + "was not found"));
    }
    public List<Channel> getByCategoy(String category){
        return channelrepository.withCat(category).orElseThrow(()-> new NoEntityFound("No channels in this category"));
    }

    public Channel update(Channel channel ){
        return channelrepository.save(channel);
    }
    @Transactional
    public void delete(int id) {
        if(!(containsKey(id))){
            throw new NoEntityFound("not found channel you want to delete");
        }
        channelrepository.deleteById(id);
    }

    public Channel getchannelById(int id) {
        if(!(containsKey(id))){
            throw new NoEntityFound("not found");
        }
            return  channelrepository.findById(id).orElseThrow(() -> new NoEntityFound("channel with id: " + id + "was not found"));

    }

    public List<Channel> getInContract(int id) {
        return channelrepository.getInContract(id).orElseThrow(() -> new NoEntityFound("no channels in this contract"));
    }
}
