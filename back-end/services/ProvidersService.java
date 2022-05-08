package com.example.demo.services;

import com.example.demo.entities.Providers;
import com.example.demo.repositories.ProvidersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvidersService {

    @Autowired
    private ProvidersRepo providersRepo;

    public Providers FindById(int id) {
        return providersRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Provider by id " + id + " was not found"));
    }

    public Providers save(Providers providers) {
          return   providersRepo.save(providers);
    }

    public List<Providers> getAll() {
        List<Providers> test = new ArrayList<>();
        providersRepo.findAll().forEach(test::add);
        return test;
    }
    public boolean containsKey(int id){
        return providersRepo.existsById(id);
    }
    public void updatePrice(int id, double percent) {
        providersRepo.updatePrice(id,percent);
    }
}
