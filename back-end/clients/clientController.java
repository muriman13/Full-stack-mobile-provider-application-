package com.example.demo.clients;

import com.example.demo.contract.contracService;
import com.example.demo.contract.contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/clients/")
public class clientController {
    @Autowired
    private com.example.demo.clients.clientsService clientsService;
    @Autowired
    private contracService contractService;
    @GetMapping("getall")
    public List<clients> getallClients(){
        return clientsService.getall();
    }
    @PostMapping("save")
    public void saveclient(@RequestBody clients clients){
        clientsService.save(clients);
    }
    @GetMapping("/payments/{client_id}")
    public double payments(@PathVariable int client_id){
        Date date = new Date();
        LocalDateTime date1  = clientsService.convertToLocalDateTimeViaInstant(date);
        contract contract = contractService.getContractOfUserId(client_id);
        LocalDateTime date2 = clientsService.convertToLocalDateTimeViaInstant(contract.getStart_date());
        long months = ChronoUnit.MONTHS.between(date2,date1);
         return months * contract.getPrice();
    }

}
