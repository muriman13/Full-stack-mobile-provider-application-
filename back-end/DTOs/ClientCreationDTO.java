package com.example.demo.DTOs;

import com.example.demo.entities.Contract;

public class ClientCreationDTO {
    private String first_name;
    private String last_name;
    private String EGN;
    private Contract contract;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public ClientCreationDTO(String first_name, String last_name, String EGN, Contract contract) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.EGN = EGN;
        this.contract = contract;
    }
}
