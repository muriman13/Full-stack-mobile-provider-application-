package com.example.demo.DTOs;

import com.example.demo.entities.Contract;

public class ClientCreationDTO {
    private int id;
    private String fname;
    private String lname;
    private String egn;
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
    public String getEgn() {
        return egn;
    }



    public ClientCreationDTO(int id, String first_name, String last_name, String EGN, Contract contract) {
        this.id = id;
        this.fname = first_name;
        this.lname = last_name;
        this.egn = EGN;
        this.contract = contract;
    }
}
