package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    private String fname;
    private String lname;
    private String egn;

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

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
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



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Clients(String first_name, String last_name) {

        this.fname = first_name;
        this.lname = last_name;
    }

    public Clients() {
    }

    public Clients(int id, String first_name, String last_name, String egn) {
        this.id = id;
        this.fname = first_name;
        this.lname = last_name;
        this.egn = egn;
    }

    public Clients(int id, String first_name, String last_name) {
        this.id = id;
        this.fname = first_name;
        this.lname = last_name;
    }

    public Clients(String first_name, String last_name, Contract contract) {
        this.fname = first_name;
        this.lname = last_name;
        this.contract = contract;
    }

    public Clients(String fname, String lname, String egn) {
        this.fname = fname;
        this.lname = lname;
        this.egn = egn;
    }
}
