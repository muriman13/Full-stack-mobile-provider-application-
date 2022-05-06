package com.example.demo.services;

import com.example.demo.entities.Channel;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Pack;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.repsitories.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ContractService {
    @Autowired
    private ContractRepo contractRepo;


    public List<Contract> getall(){
        ArrayList<Contract> contracts = new ArrayList<>();
        contractRepo.findAll().forEach(contracts::add);
        if(contracts.isEmpty()){
            throw new EntityNotFoundException("Empty");
        }
        return contracts;
    }

    public Contract saveContact(Contract contract) {
//        if(contract.getChannelsInContract().isEmpty() || contract.getPackagesInContract().isEmpty()){
//            throw new NoEntityFound("not found");
//        }
      return  contractRepo.save(contract);
    }

    public Contract saveContractWithChannels(Set<Channel> channelsInContract, Set<Pack> packagesInContract, Date start_date, Date end_date, double monthly_price, double price,
                                             String name ) {
//        contract contract = new contract(start_date,end_date,monthly_price,price,name,channelsInContract,packagesInContract);
//        return   contractRepo.save(contract);
        return null;
    }

    public Contract getone(int contractId) {
        if(!(containsKye(contractId))){
            throw new NoEntityFound("not found");
        }
       return contractRepo.findById(contractId).get();
    }

    public Contract update(Contract contract){
        return contractRepo.save(contract);
    }
    public boolean containsKye(int id){
        return contractRepo.existsById(id);
    }
    public Contract getContractOfUserId(int id){
        return contractRepo.getContractOfUser(id).orElseThrow(()-> new NoEntityFound("This client doesnt have a contract"));
    }

    public double calculatePrice (int contract_id){
        Contract contract = contractRepo.findById(contract_id).get();
        double contractMonthlyPrice = 0;
        Set<Channel> channels = contract.getChannelsInContract();
        for(Channel c : channels){
            contractMonthlyPrice += c.getPrice();
        }
       Set<Pack> packages = contract.getPackagesInContract();
        for(Pack p : packages){
            contractMonthlyPrice += p.getPrice();
        }

        return contractMonthlyPrice;

    }



    public void UpdatePrices(){
       List<Contract> test = new ArrayList<>();
       contractRepo.findAll().forEach(test::add);
        for(int i=0; i< test.size(); i++){
            contractRepo.updatePriceContract(calculatePrice(test.get(i).getId()),test.get(i).getId());
        }

    }

}
