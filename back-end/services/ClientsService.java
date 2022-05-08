package com.example.demo.services;

import com.example.demo.entities.Clients;
import com.example.demo.entities.Contract;
import com.example.demo.repositories.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepo clientsRepo;
    @Autowired
    private ContractService contractService;

    public List<Clients> getAll() {
        List<Clients> test = new ArrayList<>();
        clientsRepo.findAll().forEach(test::add);
        return test;
    }
    public void saveNoR(Clients clients){
        clientsRepo.save(clients);
    }

    public Clients save(Clients clients) {
      return  clientsRepo.save(clients);
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

    public double AllMadePayments2(int clientId){
        Contract contract = contractService.getContractOfUserId(clientId);
        long months = ChronoUnit.MONTHS.between(convertToLocalDateTimeViaInstant(contract.getStart_date()),convertToLocalDateTimeViaInstant(new Date()));
        return months * contract.getPrice();

    }
    public double allMadePayments(int clientId){
        Contract contract  = contractService.getContractOfUserId(clientId);
        if(contract.getLast_date() == null || contract.getLast_date().equals(0) ){
            contract.setLast_date(contract.getStart_date());
        }
        double payments = contract.getTotal_payments();
        payments+= ChronoUnit.MONTHS.between(convertToLocalDateTimeViaInstant(contract.getLast_date()),convertToLocalDateTimeViaInstant(new Date())) * contract.getPrice();
        contract.setTotal_payments(payments);
        contract.setLast_date(new Date());
        contractService.saveContact(contract);
        return contract.getTotal_payments();

    }

}
