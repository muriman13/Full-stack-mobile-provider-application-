package com.example.demo.controllers;

import com.example.demo.entities.Channel;
import com.example.demo.services.ChannelService;
import com.example.demo.services.ClientsService;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Pack;
import com.example.demo.services.ContractService;
import com.example.demo.services.PackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/contract/")
public class ContractController {

    private final ContractService contractService;
    private final ChannelService channelService;
   private final PackService packageService;
    private final ClientsService clientService;

    public ContractController(ContractService contractService, ChannelService channelService, PackService packageService, ClientsService clientService) {
        this.contractService = contractService;
        this.channelService = channelService;
        this.packageService = packageService;
        this.clientService = clientService;
    }

    @GetMapping("getall")
    public List<Contract> getAll(){
        return contractService.getAll();
    }

    @PostMapping("save")
    public ResponseEntity<Contract> saveContract( @RequestBody Contract contract){
        Contract savedContract = contractService.saveContact(contract);
        return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
    }
    @PutMapping("saveId")
    public ResponseEntity<?> saveContractAndGetId(@RequestBody Contract contract){
        return new ResponseEntity<>(contractService.saveContractAndGetId(contract), HttpStatus.OK);
    }

    @PostMapping("saveIdEmpty")
    public int saveContractAndGetId(){
        return contractService.saveContact(new Contract()).getId();

    }
    @GetMapping("/ChannelsInContract/{id}")
    public ResponseEntity<List<Channel>> getInContract(@PathVariable int id){
        return new ResponseEntity<>(contractService.getOne(id).getChannelsInContract().stream().toList(), HttpStatus.OK);
    }
    @GetMapping("/packagesInContract/{id}")
    public ResponseEntity<List<Pack>> getPackagesInContract(@PathVariable int id){
        return new ResponseEntity<>(contractService.getOne(id).getPackagesInContract().stream().toList(), HttpStatus.OK);

    }

    @PutMapping("addChannel/{contractId}/{channelId}")
    public ResponseEntity<Contract> addChannel(@PathVariable int contractId, @PathVariable int channelId){
        return new ResponseEntity<>(contractService.addChannelToContract(contractId,channelId), HttpStatus.OK);

    }
    @PutMapping("addPackage/{contractId}/{packageId}")
    public ResponseEntity<Contract> addPackage(@PathVariable int contractId, @PathVariable int packageId){
        return new ResponseEntity<>(contractService.addPackageToContract(contractId,packageId), HttpStatus.OK);

    }

    @GetMapping("byid")
    public ResponseEntity<Contract> getById(int id){
        return new ResponseEntity<>(contractService.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<List<Channel>> getChannels(@PathVariable int id){
       return new ResponseEntity<>(contractService.getOne(id).getChannelsInContract().stream().toList(), HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<Contract> update ( @RequestBody Contract oldContract){
        return new ResponseEntity<>( contractService.update(oldContract),HttpStatus.OK);
    }
    @GetMapping("/getPricceOfContract/{id}")
    public ResponseEntity<Double> getPrice(@PathVariable int id){
       return new ResponseEntity<>(contractService.calculatePrice(id),HttpStatus.OK);
    }

    @GetMapping("updateprice")
    public ResponseEntity<Void> updatePrice(){
       contractService.updatePrices();
       return  new ResponseEntity<>(HttpStatus.OK);
    }

}
