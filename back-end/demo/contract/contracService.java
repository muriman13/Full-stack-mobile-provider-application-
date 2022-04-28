package com.example.demo.contract;

import com.example.demo.channels.channel;
import com.example.demo.pack.pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class contracService {
    @Autowired
    private contractRepo contractRepo;


    public List<contract> getall(){
        ArrayList<contract> a = new ArrayList<>();
        contractRepo.findAll().forEach(a::add);
        return a;
    }

    public contract saveContact(contract contract) {
      return  contractRepo.save(contract);
    }

    public contract saveContractWithChannels(Set<channel> channelsInContract, Set<pack> packagesInContract, Date start_date,Date end_date, double monthly_price,double price,
    String name ) {
        contract contract = new contract(start_date,end_date,monthly_price,price,name,channelsInContract,packagesInContract);
        return   contractRepo.save(contract);
    }

    public contract getone(int contractId) {
       return contractRepo.findById(contractId).get();
    }

    public contract update(contract contract){
        return contractRepo.save(contract);
    }
    public boolean containsKye(int id){
        return contractRepo.existsById(id);
    }

}
