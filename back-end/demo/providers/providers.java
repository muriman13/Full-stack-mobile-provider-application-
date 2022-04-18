package com.example.demo.providers;

import com.example.demo.channels.channel;
import com.example.demo.pack.pack;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "providers")
public class providers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String name;
    private double price;
    //channels_id //ne e nujno ne go pishi
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="providers_has_channels",
    joinColumns ={
        @JoinColumn(name = "providers_id", referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "channels_id",referencedColumnName = "id")
    }
    )
    private Set<channel> channelsofproviders =new HashSet <>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="providers_has_packages",
            joinColumns ={
                    @JoinColumn(name = "providers_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "packages_id",referencedColumnName = "id")
            }
    )
    private Set<pack> packagesOfProviders =new HashSet <>();

    public Set<pack> getPackagesOfProviders() {
        return packagesOfProviders;
    }

    public void setPackagesOfProviders(Set<pack> packagesOfProviders) {
        this.packagesOfProviders = packagesOfProviders;
    }

    public Set<channel> getChannelsofproviders() {
        return channelsofproviders;
    }

    public void setChannelsofproviders(Set<channel> channelsofproviders) {
        this.channelsofproviders = channelsofproviders;
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

    public providers(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public providers() {
    }
}
