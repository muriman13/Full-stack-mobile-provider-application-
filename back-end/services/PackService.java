package com.example.demo.services;

import com.example.demo.entities.Pack;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.repsitories.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackService {
    @Autowired
    private PackRepository packrepository;

    public List<Pack> getInContract(int id) {
     return packrepository.getInContract(id).orElseThrow(()-> new NoEntityFound("Contract has no packages"));
    }
    public boolean containsKye(int id){
        return packrepository.existsById(id);
    }

    public void saveToDatabase(){
       Pack a= new Pack("AllChannelsPackage",4.99);
        packrepository.save(a);
    }
    public List<Pack> getAllpacks(){
        List<Pack> packages = new ArrayList<>();
        packrepository.findAll().forEach(packages::add);
        if(packages.isEmpty()){
            throw new NoEntityFound("Empty");
        }
        return packages;
    }
    public List<Pack> getpackByName(String id){
     return packrepository.findByname(id).orElseThrow(() -> new NoEntityFound("No package was found with name:" + id));
    }

    public void deleteByname(String name){
        packrepository.deleteByname(name);
    }
    public Pack updatePack(String name, Integer id){
       return packrepository.updatePack(name,20);
    }
    public Pack savePostPackage(Pack pack){
       return packrepository.save(pack);
    }
    public Pack getOne (int id) {
        return packrepository.findById(id).get();
    }

    public void delete(int id) {
        packrepository.deleteById(id);
    }
    public Pack update(Pack pack ){
        return packrepository.save(pack);
    }

    public Pack getPackById(int id) {
        return null;
    }
}
