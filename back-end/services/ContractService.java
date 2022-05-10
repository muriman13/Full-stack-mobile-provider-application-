package com.example.demo.services;

import com.example.demo.entities.Channel;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Pack;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.repositories.ContractRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {
    private final ContractRepo contractRepo;
    private final ChannelService channelService;
    private final PackService packService;
    public ContractService(ContractRepo contractRepo, ChannelService channelService, PackService packService) {
        this.contractRepo = contractRepo;
        this.channelService = channelService;
        this.packService = packService;
    }


    public int saveContractAndGetId(Contract contract){

        contract.setPrice(calculatePrice(contract.getId()));
        contract.setChannelsInContract(getOne(contract.getId()).getChannelsInContract());
        contract.setPackagesInContract(getOne(contract.getId()).getPackagesInContract());
        saveContact(contract);
        saveContact(contract);
        return contract.getId();
    }

    public Contract addChannelToContract(int contractId, int channelId){
        Channel channel =   channelService.getOne(channelId);
        channel.addContract( getOne(contractId));
        Contract contract  = getOne(contractId);
        contract.addChannels(channel);
        Contract savedContract = saveContact(contract);
        return contract;
    }

    public Contract addPackageToContract(int contractId, int packageId){
        Pack pack = packService.getOne(packageId);
        pack.addCon(getOne(contractId));
        Contract contract = getOne(contractId);
        contract.addPackage(pack);
        Contract savedContract = saveContact(contract);
        return contract;
    }




    public List<Contract> getAll(){
        ArrayList<Contract> contracts = new ArrayList<>();
        contractRepo.findAll().forEach(contracts::add);
        if(contracts.isEmpty()){
            throw new EntityNotFoundException("Empty");
        }
        return contracts;
    }

    public Contract saveContact(Contract contract) {
      return  contractRepo.save(contract);
    }


    public Contract getOne(int contractId) {
       return contractRepo.findById(contractId).orElseThrow(()-> new NoEntityFound("No contract found"));
    }

    public Contract update(Contract contract){
        return contractRepo.save(contract);
    }
    public Contract getContractOfUserId(int id){
        return contractRepo.getContractOfUser(id).orElseThrow(()-> new NoEntityFound("This client doesnt have a contract"));
    }

    public double calculatePrice (int contract_id){
        double contractMonthlyPrice=0;
        contractMonthlyPrice += contractRepo.findById(contract_id).orElseThrow(()-> new NoEntityFound("no contract with this id found")).getChannelsInContract().stream().mapToDouble(Channel::getPrice).sum();
        return contractMonthlyPrice;
    }



    public void updatePrices(){
       List<Contract> test = new ArrayList<>();
       contractRepo.findAll().forEach(test::add);
        for (Contract contract : test) {
            contractRepo.updatePriceContract(calculatePrice(contract.getId()), contract.getId());
        }

    }

}
