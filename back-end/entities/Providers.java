package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Providers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull(message = "name should not be null")
    private String name;
    @NotNull(message = "price should not be null")
    private double price;
    @JsonIgnore
    @OneToMany(mappedBy = "providers" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Channel> channelsInProvider = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "providersInpackage" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Pack> packsForprovider = new HashSet<>();


    public Set<Channel> getChannelsInProvider() {
        return channelsInProvider;
    }
    public void setChannelsInProvider(Set<Channel> channelsInProvider) {
        this.channelsInProvider = channelsInProvider;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Providers(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public Providers(String name, double price, Providers providers) {
        this.name = name;
        this.price = price;

    }

    public Providers() {
    }
    public void addChannel(Channel channel){
        channelsInProvider.add(channel);
    }
}


