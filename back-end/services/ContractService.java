package com.example.demo.services;

import com.example.demo.entities.Channel;
import com.example.demo.entities.Contract;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.repositories.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepo contractRepo;


    public List<Contract> getAll(){
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


    public Contract getOne(int contractId) {
        if(!(containsKey(contractId))){
            throw new NoEntityFound("not found");
        }
       return contractRepo.findById(contractId).get();
    }

    public Contract update(Contract contract){
        return contractRepo.save(contract);
    }
    public boolean containsKey(int id){
        return contractRepo.existsById(id);
    }
    public Contract getContractOfUserId(int id){
        return contractRepo.getContractOfUser(id).orElseThrow(()-> new NoEntityFound("This client doesnt have a contract"));
    }

    public double calculatePrice (int contract_id){
        double contractMonthlyPrice=0;
        contractMonthlyPrice += contractRepo.findById(contract_id).get().getChannelsInContract().stream().mapToDouble(Channel::getPrice).sum();
        return contractMonthlyPrice;
    }



    public void updatePrices(){
       List<Contract> test = new ArrayList<>();
       contractRepo.findAll().forEach(test::add);
        for(int i=0; i< test.size(); i++){
            contractRepo.updatePriceContract(calculatePrice(test.get(i).getId()),test.get(i).getId());
        }

    }

}
