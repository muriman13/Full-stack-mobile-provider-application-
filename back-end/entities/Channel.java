package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;


@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private int id;

    private String name;
    private String type;
    private Double price;
    private Integer pack_id;
    @JsonIgnore
    @ManyToMany(mappedBy = "channelsInContract", fetch = FetchType.LAZY)
    private Set<Contract> contracts = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "channels", fetch = FetchType.LAZY)
    private Set<Pack> packs = new HashSet<>();
   // @JsonIgnore
//    @ManyToMany(mappedBy = "channelsofproviders", fetch = FetchType.LAZY)
//    private Set<com.example.demo.providers> providers;
    @JsonIgnore
//    @ManyToOne(optional = true)
//    @JoinColumn(name = "contract_id",nullable = true)
//    private contract channelcontract;



    @ManyToOne
    @JoinColumn(name = "providers_id",nullable = true)
    private Providers providers;

//    public contract getChannelcontract() {
//        return channelcontract;
//    }
//
//    public void setChannelcontract(contract channelcontract) {
//        this.channelcontract = channelcontract;
//    }

//    public Set<com.example.demo.providers> getProviders() {
//        return providers;
//    }

//    public void setProviders(Set<com.example.demo.providers> providers) {
//        this.providers = providers;
//    }


    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }

    public Set<Pack> getPacks() {
        return packs;
    }

    public void setPacks(Set<Pack> packs) {
        this.packs = packs;
    }

    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPack_id() {
        return pack_id;
    }

    public void setPack_id(Integer pack_id) {
        this.pack_id = pack_id;
    }



    public Channel(String name, String type, Double price, Integer pack_id, Set<Contract> contracts, Set<Pack> packs, Providers providers) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.pack_id = pack_id;
        this.contracts = contracts;
        this.packs = packs;
        this.providers = providers;
    }

    public Channel(String name, String type, Double price, Integer pack_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.pack_id = pack_id;
    }

    public Channel() {

    }

    public void addPacks(Pack pack) {
        this.packs.add(pack);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void addContract(Contract contract){
        contracts.add(contract);
    }


}
