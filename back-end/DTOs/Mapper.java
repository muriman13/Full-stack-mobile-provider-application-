package com.example.demo.DTOs;

import com.example.demo.entities.Clients;
import com.example.demo.entities.Contract;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public ClientDTO toDto(Clients client) {
        int id = client.getId();
        String fname = client.getFname();
        String lname = client.getLname();
        Contract contract = client.getContract();
        return new ClientDTO(id,fname, lname,contract);
    }

    public Clients toClient(ClientCreationDTO clientDTO) {
        return new Clients(clientDTO.getFname(),clientDTO.getLname(), clientDTO.getContract());
    }
}