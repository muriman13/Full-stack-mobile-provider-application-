package com.example.demo.services;


import com.example.demo.entities.Pack;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.repositories.PackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackService {

    private final PackRepository packrepository;
    private final ChannelService channelService;
    public PackService(PackRepository packrepository, ChannelService channelService) {
        this.packrepository = packrepository;
        this.channelService = channelService;
    }


    public List<Pack> getInContract(int id) {
     return packrepository.getInContract(id).orElseThrow(()-> new NoEntityFound("Contract has no packages"));
    }
    public boolean containsKey(int id){
        return packrepository.existsById(id);
    }

    public List<Pack> getAllpacks(){
        List<Pack> packages = new ArrayList<>();
        packrepository.findAll().forEach(packages::add);
        if(packages.isEmpty()){
            throw new NoEntityFound("Empty");
        }
        return packages;
    }
    public Pack getPackByName(String id){
     return packrepository.findByname(id).orElseThrow(() -> new NoEntityFound("No package was found with name:" + id));
    }

    public void deleteByname(String name){
        packrepository.deleteByname(name);
    }
    @Transactional
    public Pack updatePack(String name, Integer id){
       return packrepository.updatePack(name,id);
    }
    public Pack savePostPackage(Pack pack){
       return packrepository.save(pack);
    }
    public Pack getOne (int id) {
        return packrepository.findById(id).orElseThrow(()-> new NoEntityFound("Not pack found"));
    }
    @Transactional
    public void delete(int id) {
        packrepository.deleteById(id);
    }
    public Pack update(Pack pack ){
        return packrepository.save(pack);
    }
    public Pack addChannelToPackage(int id, @PathVariable int channel_id){
        Pack pack = getOne(id);
        pack.addchannel(channelService.getOne(channel_id));
        return pack;
    }


    public Pack categoryPackage(Pack pack,String category){
        pack.setId(packrepository.findByname(category).orElseThrow(()-> new NoEntityFound("no found")).getId());
        pack.setName(category);
        pack.setChannels(channelService.getAllChannels().stream().filter(channel -> channel.getType().equals(category)).collect(Collectors.toSet()));
       return packrepository.save(pack);
    }
}
