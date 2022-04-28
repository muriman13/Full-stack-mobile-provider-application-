package com.example.demo.contract;

import com.example.demo.channels.channel;
import com.example.demo.channels.channelService;
import com.example.demo.exceptions.NoEntityFound;
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
    @PostMapping("saveId")
    public int savecontractAndGetId(@RequestBody contract contract){
        contracService.saveContact(contract);
        return contract.id;
    }
    @PostMapping("saveIdEmpty")
    public void savecontractAndGetIdEmpty(){
      contract contract =  new contract();
        contracService.saveContact(new contract());

    }
    @GetMapping("/ChannelsInContract/{id}")
    public List<channel> getInContract(@PathVariable int id){
        if(!(contracService.containsKye(id))){
            throw new NoEntityFound("not found");
        }
        return  channelService.getInContract(id);


    }
    @GetMapping("/packagesInContract/{id}")
    public List<pack> getPInContract(@PathVariable int id){
        if(!(contracService.containsKye(id))){
            throw new NoEntityFound("not found");
        }

        return packageService.getInContract(id);

    }

    @PutMapping("addChannel/{contractId}/{channelid}")
    public contract addChannel(@PathVariable int contractId,@PathVariable int channelid){
        if(!(channelService.containsKye(channelid) || contracService.containsKye(contractId))){
            throw new NoEntityFound("not found");
        }
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
        if(!(packageService.containsKye(packageId) || contracService.containsKye(contractId))){
            throw new NoEntityFound("not found");
        }
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

}
