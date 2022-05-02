package com.example.demo.clients;

import com.example.demo.channels.channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class clientsService {

    @Autowired
    private com.example.demo.clients.clientsRepo clientsRepo;

    public List<clients> getall() {
        List<clients> test = new ArrayList<>();
        clientsRepo.findAll().forEach(test::add);
        return test;
    }
    public void saveNoR(clients clients){
        clientsRepo.save(clients);
    }

    public void save(clients clients) {
        clientsRepo.save(clients);
    }

    public void getById(int id) {
        clientsRepo.findById(id);
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
