package com.example.demo.controllers;

import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import com.example.demo.services.ClientsService;
import com.example.demo.entities.Contract;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.entities.Pack;
import com.example.demo.services.ContractService;
import com.example.demo.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/contract/")
public class ContractController {

    @Autowired
    private ContractService contracService;
    @Autowired
    private ChannelService channelService;
    @Autowired
   private PackService packageService;
    @Autowired
    private ClientsService clientService;
    @GetMapping("getall")
    public List<Contract> getall(){
        return contracService.getall();
    }

    @PostMapping("save")
    public ResponseEntity<Contract> savecontract(@RequestBody Contract contract){
        Contract savedCotract = contracService.saveContact(contract);
        return new ResponseEntity<Contract>(savedCotract, HttpStatus.CREATED);
    }
    @PutMapping("saveId")
    public ResponseEntity<?> savecontractAndGetId(@RequestBody Contract contract){
        contract.setPrice(contracService.calculatePrice(contract.getId()));
        contract.setChannelsInContract(contracService.getone(contract.getId()).getChannelsInContract());
        contract.setPackagesInContract(contracService.getone(contract.getId()).getPackagesInContract());
        contracService.saveContact(contract);
        int id = contract.getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @GetMapping("/ChannelsInContract/{id}")
    public ResponseEntity<List<Channel>> getInContract(@PathVariable int id){
        List<Channel>channels = contracService.getone(id).getChannelsInContract().stream().toList();
        return new ResponseEntity<>(channels, HttpStatus.OK);
    }
    @GetMapping("/packagesInContract/{id}")
    public ResponseEntity<List<Pack>> getPInContract(@PathVariable int id){

        List<Pack> packages =  contracService.getone(id).getPackagesInContract().stream().toList();
        return new ResponseEntity<>(packages, HttpStatus.OK);

    }

    @PutMapping("addChannel/{contractId}/{channelid}")
    public ResponseEntity<Contract> addChannel(@PathVariable int contractId, @PathVariable int channelid){

        Channel channel = new Channel();
        channel =   channelService.getone(channelid);
        channel.addContract( contracService.getone(contractId));
        Contract contract = new Contract();
        contract  = contracService.getone(contractId);
        contract.addChannels(channel);
        Contract savedContract = contracService.saveContact(contract);
        return new ResponseEntity<Contract>(savedContract, HttpStatus.OK);

    }
    @PutMapping("addPackage/{contractId}/{packageId}")
    public ResponseEntity<Contract> addPackage(@PathVariable int contractId, @PathVariable int packageId){

        Pack pack = packageService.getOne(packageId);
        pack.addCon(contracService.getone(contractId));
        Contract contract = contracService.getone(contractId);
        contract.addPackage(pack);
        Contract savedContract = contracService.saveContact(contract);
        return new ResponseEntity<Contract>(savedContract, HttpStatus.OK);

    }

    @GetMapping("byid")
    public ResponseEntity<Contract> getById(int id){
        Contract contract = contracService.getone(id);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }
    @PutMapping("WithRquestParam")
    public ResponseEntity<Contract> contractWithChannelsAndPackages(@RequestParam Set<Channel> channelsInContract,
                                                    @RequestParam Set<Pack> packagesInContract,
                                                    @RequestParam Date start_date,
                                                    @RequestParam Date end_date,
                                                    @RequestParam double monthly_price,
                                                    @RequestParam double price,
                                                    @RequestParam String name ){

      Contract savedContract=  contracService.saveContractWithChannels(channelsInContract,packagesInContract,start_date,end_date,monthly_price,price,name);
        return new ResponseEntity<Contract>(savedContract,HttpStatus.CREATED);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<List<Channel>> getChannels(@PathVariable int id){
       List<Channel> channels =  contracService.getone(id).getChannelsInContract().stream().toList();
       return new ResponseEntity<>(channels, HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<Contract> update (@RequestBody Contract Oldcontract){
        Contract contract = contracService.update(Oldcontract);
        return new ResponseEntity<Contract>(contract,HttpStatus.OK);
    }
    @GetMapping("/getPricceOfContract/{id}")
    public ResponseEntity<?> getprice(@PathVariable int id){
       double price = contracService.calculatePrice(id);
       return new ResponseEntity<>(price,HttpStatus.OK);
    }

    @GetMapping("updateprice")
    public ResponseEntity<?> updateprice(){
       contracService.UpdatePrices();
       return  new ResponseEntity<>(HttpStatus.OK);
    }

}
