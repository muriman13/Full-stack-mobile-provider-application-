package com.example.demo.services;

import com.example.demo.entities.Channel;
import com.example.demo.entities.Providers;
import com.example.demo.repositories.ProvidersRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvidersService {

    private final ProvidersRepo providersRepo;
    private final ChannelService channelService;
    public ProvidersService(ProvidersRepo providersRepo, ChannelService channelService) {
        this.providersRepo = providersRepo;
        this.channelService = channelService;
    }

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
        providersRepo.updatePricePackages(id,percent);
    }
    public Providers addChannel(int id, int channel_id){
        Providers providers = FindById(id);
        Channel channel = channelService.getOne(channel_id);
        channel.setProviders(providers);
        providers.addChannel(channel);
        return providers;
    }


}
