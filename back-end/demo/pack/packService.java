package com.example.demo.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class packService {
    @Autowired
    private packRepository packrepository;

    public void saveToDatabase(){
       pack a= new pack("AllChannelsPackage",4.99);
        packrepository.save(a);
    }
    public List<pack> getAllpacks(){
        List<pack> test = new ArrayList<>();
        packrepository.findAll().forEach(test::add);
        return test;
    }
    public List<pack> getpackByName(String id){
     return packrepository.findByname(id);
    }

    public void deleteByname(String name){
        packrepository.deleteByname(name);
    }
    public void updatePack(String name, Integer id){
        packrepository.updatePack(name,20);
    }
    public pack savePostPackage(pack pack){
       return packrepository.save(pack);
    }
    public pack getOne (int id) {
        return packrepository.findById(id).get();
    }
}
