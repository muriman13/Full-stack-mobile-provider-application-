package com.example.demo.clients;

import com.example.demo.contract.contract;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")
public class clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    private String first_name;
    private String last_name;
    //channels_id //ne e nujno ne go pishi
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "Clients_contract",
//            joinColumns =
//                    { @JoinColumn(name = "clients_id", referencedColumnName = "id") },
//            inverseJoinColumns =
//                    { @JoinColumn(name = "contract_id", referencedColumnName = "id") })
    @JsonIgnore
    @OneToOne(mappedBy = "clients", optional = true)
    private com.example.demo.contract.contract contract;

    public com.example.demo.contract.contract getContract() {
        return contract;
    }

    public void setContract(com.example.demo.contract.contract contract) {
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

    public clients( String first_name, String last_name) {

        this.first_name = first_name;
        this.last_name = last_name;
    }

    public clients() {
    }
}
