package com.example.demo.controllers;

import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import com.example.demo.services.ClientsService;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Pack;
import com.example.demo.services.ContractService;
import com.example.demo.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return contracService.getAll();
    }

    @PostMapping("save")
    public ResponseEntity<Contract> savecontract(@RequestBody Contract contract){
        Contract savedCotract = contracService.saveContact(contract);
        return new ResponseEntity<Contract>(savedCotract, HttpStatus.CREATED);
    }
    @PutMapping("saveId")
    public ResponseEntity<?> savecontractAndGetId(@RequestBody Contract contract){
        contract.setPrice(contracService.calculatePrice(contract.getId()));
        contract.setChannelsInContract(contracService.getOne(contract.getId()).getChannelsInContract());
        contract.setPackagesInContract(contracService.getOne(contract.getId()).getPackagesInContract());
        contracService.saveContact(contract);
        int id = contract.getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("saveIdEmpty")
    public int savecontractAndGetId(){
//        Contract contract = new Contract();
//        contracService.saveContact(contract);
//        return contract.getId();
        return contracService.saveContact(new Contract()).getId();

    }
    @GetMapping("/ChannelsInContract/{id}")
    public ResponseEntity<List<Channel>> getInContract(@PathVariable int id){
        return new ResponseEntity<>(contracService.getOne(id).getChannelsInContract().stream().toList(), HttpStatus.OK);
    }
    @GetMapping("/packagesInContract/{id}")
    public ResponseEntity<List<Pack>> getPInContract(@PathVariable int id){
        return new ResponseEntity<>(contracService.getOne(id).getPackagesInContract().stream().toList(), HttpStatus.OK);

    }

    @PutMapping("addChannel/{contractId}/{channelid}")
    public ResponseEntity<Contract> addChannel(@PathVariable int contractId, @PathVariable int channelid){

        Channel channel =   channelService.getOne(channelid);
        channel.addContract( contracService.getOne(contractId));
        Contract contract  = contracService.getOne(contractId);
        contract.addChannels(channel);
        Contract savedContract = contracService.saveContact(contract);
        return new ResponseEntity<Contract>(savedContract, HttpStatus.OK);

    }
    @PutMapping("addPackage/{contractId}/{packageId}")
    public ResponseEntity<Contract> addPackage(@PathVariable int contractId, @PathVariable int packageId){

        Pack pack = packageService.getOne(packageId);
        pack.addCon(contracService.getOne(contractId));
        Contract contract = contracService.getOne(contractId);
        contract.addPackage(pack);
        Contract savedContract = contracService.saveContact(contract);
        return new ResponseEntity<Contract>(savedContract, HttpStatus.OK);

    }

    @GetMapping("byid")
    public ResponseEntity<Contract> getById(int id){
        return new ResponseEntity<Contract>(contracService.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<List<Channel>> getChannels(@PathVariable int id){
       return new ResponseEntity<>(contracService.getOne(id).getChannelsInContract().stream().toList(), HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<Contract> update (@RequestBody Contract Oldcontract){
        return new ResponseEntity<Contract>( contracService.update(Oldcontract),HttpStatus.OK);
    }
    @GetMapping("/getPricceOfContract/{id}")
    public ResponseEntity<?> getprice(@PathVariable int id){
       return new ResponseEntity<>(contracService.calculatePrice(id),HttpStatus.OK);
    }

    @GetMapping("updateprice")
    public ResponseEntity<?> updateprice(){
       contracService.updatePrices();
       return  new ResponseEntity<>(HttpStatus.OK);
    }

}
