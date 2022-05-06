package com.example.demo;

import com.example.demo.DTOs.ClientCreationDTO;
import com.example.demo.DTOs.ClientDTO;
import com.example.demo.entities.Clients;
import com.example.demo.entities.Contract;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public ClientDTO toDto(Clients client) {
        String fname = client.getFirst_name();
        String lname = client.getLast_name();
        Contract contract = client.getContract();
        return new ClientDTO(fname, lname,contract);
    }

    public Clients toClient(ClientCreationDTO clientDTO) {
        return new Clients(clientDTO.getFirst_name(),clientDTO.getLast_name(), clientDTO.getContract());
    }
}