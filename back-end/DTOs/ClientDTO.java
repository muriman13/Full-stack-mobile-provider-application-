package com.example.demo.DTOs;

import com.example.demo.entities.Contract;

public class ClientDTO {
    private int id;
    private String fname;
    private String lname;
    private Contract contract;

    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public Contract getContract() {
        return contract;
    }

    public ClientDTO(int id, String first_name, String last_name, Contract contract) {
        this.id = id;
        this.fname = first_name;
        this.lname = last_name;
        this.contract = contract;
    }
}
