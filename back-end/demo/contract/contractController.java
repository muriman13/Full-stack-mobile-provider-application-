package com.example.demo.contract;

import com.example.demo.channels.channel;
import com.example.demo.channels.channelService;
import com.example.demo.pack.pack;
import com.example.demo.pack.packService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("getall")
    public List<contract> getall(){
        return contracService.getall();
    }

    @PostMapping("save")
    public void savecontract(@RequestBody contract contract){
        contracService.saveContact(contract);
    }
    @PutMapping("addChannel/{contractId}/{channelid}")
    public contract addChannel(@PathVariable int contractId,@PathVariable int channelid){
        channel channel = new channel();
        channel =   channelService.getone(channelid);
        channel.setChannelcontract( contracService.getone(contractId));
        contract contract = new contract();
        contract  = contracService.getone(contractId);
        contract.addChannels(channel);
        return contracService.saveContact(contract);

    }
    @PutMapping("addPackage/{contractId}/{packageId}")
    public contract addPackage(@PathVariable int contractId,@PathVariable int packageId){
        pack pack = packageService.getOne(packageId);
        pack.setPackcontract(contracService.getone(contractId));
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
    public void getChannels(@PathVariable int id){
        contracService.getone(id).getChannelsInContract();

    }

}
