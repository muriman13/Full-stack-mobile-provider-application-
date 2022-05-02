package com.example.demo.pack;

import com.example.demo.channels.channel;
import com.example.demo.contract.contract;
import com.example.demo.providers.providers;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "packages")
public class pack {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private Double price;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "channels_has_packages",
            joinColumns = {
                    @JoinColumn(name = "packages_id", referencedColumnName = "id"
                            )},
            inverseJoinColumns = {
                    @JoinColumn(name = "channels_id", referencedColumnName = "id"
                            )})
    private Set<channel> channels = new HashSet<>();

//    @ManyToMany(mappedBy = "packagesOfProviders", fetch = FetchType.LAZY)
//    private Set<com.example.demo.providers.providers> providers = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "packagesInContract", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<contract> contracts = new HashSet<>();

//    public Set<com.example.demo.providers.providers> getProviders() {
//        return providers;
//    }
//
//    public void setProviders(Set<com.example.demo.providers.providers> providers) {
//        this.providers = providers;
//    }
@ManyToOne
@JoinColumn(name = "providers_id",nullable = true)
private providers providersInpackage;

    public Set<contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<contract> contracts) {
        this.contracts = contracts;
    }

    public Set<channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<channel> channels) {
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public providers getProvidersInpackage() {
        return providersInpackage;
    }

    public void setProvidersInpackage(providers providersInpackage) {
        this.providersInpackage = providersInpackage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public pack(int id, String name, Double price, Set<channel> channels) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.channels = channels;
    }

    public pack(providers providersInpackage) {
        this.providersInpackage = providersInpackage;
    }

    public pack(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public pack(String name) {

        this.name = name;
    }

    public pack() {
    }


    public void addchannel(channel channel) {
        channels.add(channel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCon(contract contract){
        contracts.add(contract);
    }
}
