package com.example.demo.providers;

import com.example.demo.channels.channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class providersService {

    @Autowired
    private com.example.demo.providers.providersRepo providersRepo;

    public providers FindById(int id) {
        return providersRepo.findById(id);
    }

    public providers save(providers providers) {
          return   providersRepo.save(providers);
    }

    public List<providers> getall() {
        List<providers> test = new ArrayList<>();
        providersRepo.findAll().forEach(test::add);
        return test;
    }

    public void updateprice(int id, double percent) {
        providersRepo.updatePrice(id,percent);
    }
}
