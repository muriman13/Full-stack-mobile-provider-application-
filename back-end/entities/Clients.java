package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    private String first_name;
    private String last_name;
    private String EGN;

    //channels_id //ne e nujno ne go pishi
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "Clients_contract",
//            joinColumns =
//                    { @JoinColumn(name = "clients_id", referencedColumnName = "id") },
//            inverseJoinColumns =
//                    { @JoinColumn(name = "contract_id", referencedColumnName = "id") })
    @JsonIgnore
    @OneToOne(mappedBy = "clients", optional = true)
    private Contract contract;

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Clients(String first_name, String last_name) {

        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Clients() {
    }

    public Clients(String first_name, String last_name, Contract contract) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.contract = contract;
    }
}
