package com.example.demo.contract;

import com.example.demo.channels.channel;
import com.example.demo.channels.channelService;
import com.example.demo.clients.clients;
import com.example.demo.clients.clientsRepo;
import com.example.demo.clients.clientsService;
import com.example.demo.exceptions.NoEntityFound;
import com.example.demo.pack.pack;
import com.example.demo.pack.packService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/contract/")
public class contractController {

    @Autowired
    private contracService contracService;
    @Autowired
    private channelService channelService;
    @Autowired
   private packService packageService;
    @Autowired
    private clientsService clientService;
    @GetMapping("getall")
    public List<contract> getall(){
        return contracService.getall();
    }

    @PostMapping("save")
    public void savecontract(@RequestBody contract contract){
        contracService.saveContact(contract);
    }
    @PutMapping("saveId")
    public int savecontractAndGetId(@RequestBody contract contract){
        contract.setPrice(contracService.calculatePrice(contract.id));
        contract.setChannelsInContract(contracService.getone(contract.id).getChannelsInContract());
        contract.setPackagesInContract(contracService.getone(contract.id).getPackagesInContract());
        contracService.saveContact(contract);
        return contract.id;
    }
    @PostMapping("saveIdEmpty")
    public int savecontractAndGetIdEmpty(@RequestBody contract contract){

        contracService.saveContact(contract);
        return contract.id;

    }
    @GetMapping("/ChannelsInContract/{id}")
    public Set<channel> getInContract(@PathVariable int id){
        if(!(contracService.containsKye(id))){
            throw new NoEntityFound("not found");
        }
        return contracService.getone(id).getChannelsInContract();
//        return  channelService.getInContract(id);


    }
    @GetMapping("/packagesInContract/{id}")
    public Set<pack> getPInContract(@PathVariable int id){
        if(!(contracService.containsKye(id))){
            throw new NoEntityFound("not found");
        }


        return contracService.getone(id).getPackagesInContract();

    }

    @PutMapping("addChannel/{contractId}/{channelid}")
    public contract addChannel(@PathVariable int contractId,@PathVariable int channelid){
        if(!(channelService.containsKye(channelid) || contracService.containsKye(contractId))){
            throw new NoEntityFound("not found");
        }
        channel channel = new channel();
        channel =   channelService.getone(channelid);
        channel.addContract( contracService.getone(contractId));
        contract contract = new contract();
        contract  = contracService.getone(contractId);
        contract.addChannels(channel);
        return contracService.saveContact(contract);

    }
    @PutMapping("addPackage/{contractId}/{packageId}")
    public contract addPackage(@PathVariable int contractId,@PathVariable int packageId){
        if(!(packageService.containsKye(packageId) || contracService.containsKye(contractId))){
            throw new NoEntityFound("not found");
        }
        pack pack = packageService.getOne(packageId);
        pack.addCon(contracService.getone(contractId));
        contract contract = contracService.getone(contractId);
        contract.addPackage(pack);
        return contracService.saveContact(contract);

    }
    @GetMapping("aaa")
    public List<contract> aaa(){
        return contracService.getall();
    }
    @GetMapping("byid")
    public contract getById(int id){
        return contracService.getone(id);
    }
    @PutMapping("GodSmiteMeDown")
    public contract contractWithChannelsAndPackages(@RequestParam Set<channel> channelsInContract,
                                                    @RequestParam Set<pack> packagesInContract,
                                                    @RequestParam Date start_date,
                                                    @RequestParam Date end_date,
                                                    @RequestParam double monthly_price,
                                                    @RequestParam double price,
                                                    @RequestParam String name ){

      return  contracService.saveContractWithChannels(channelsInContract,packagesInContract,start_date,end_date,monthly_price,price,name);

    }
    @GetMapping("/show/{id}")
    public Set<channel> getChannels(@PathVariable int id){
        if(!(contracService.containsKye(id))){
            throw new NoEntityFound("not found");
        }

       return contracService.getone(id).getChannelsInContract();

    }
    @PutMapping("/update")
    public contract update ( @RequestBody contract Oldcontract){
        contract contract = contracService.update(Oldcontract);
        return contract;
    }
    @GetMapping("/getPricceOfContract/{id}")
    public double getprice(@PathVariable int id){
       return contracService.calculatePrice(id);
    }

    @GetMapping("updateprice")
    public void updateprice(){
        contracService.UpdatePrices();
    }

}
