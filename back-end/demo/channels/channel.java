package com.example.demo.channels;


import com.example.demo.contract.contract;
import com.example.demo.pack.pack;
import com.example.demo.providers.providers;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
public class channel  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private int id;

    private String name;
    private String type;
    private Double price;
    private Integer pack_id;
    @Column(insertable = false, updatable = false)
    private Integer contract_id;
   @JsonIgnore
    @ManyToMany(mappedBy = "channels", fetch = FetchType.LAZY)
    private Set<pack> packs = new HashSet<>();
   // @JsonIgnore
    @ManyToMany(mappedBy = "channelsofproviders", fetch = FetchType.LAZY)
    private Set<com.example.demo.providers.providers> providers;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "contract_id",nullable = true)
    private contract channelcontract;

    public contract getChannelcontract() {
        return channelcontract;
    }

    public void setChannelcontract(contract channelcontract) {
        this.channelcontract = channelcontract;
    }

    public Set<com.example.demo.providers.providers> getProviders() {
        return providers;
    }

    public void setProviders(Set<com.example.demo.providers.providers> providers) {
        this.providers = providers;
    }

    public Set<pack> getPacks() {
        return packs;
    }

    public void setPacks(Set<pack> packs) {
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

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public channel(int id, String name, String type, Double price, Integer pack_id, Set<pack> packs, Set<com.example.demo.providers.providers> providers, contract channelcontract) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.pack_id = pack_id;
        this.packs = packs;
        this.providers = providers;
       this.channelcontract = channelcontract;
    }

    public channel(String name, String type, Double price, Integer pack_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.pack_id = pack_id;
    }

    public channel() {

    }

    public void addPacks(pack pack) {
        this.packs.add(pack);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
