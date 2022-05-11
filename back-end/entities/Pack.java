package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "packages")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull(message = "name should not be null")
    private String name;
    @NotNull(message = "There should be a price")
    private Double price;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "channels_has_packages",
            joinColumns = {
                    @JoinColumn(name = "packages_id", referencedColumnName = "id"
                            )},
            inverseJoinColumns = {
                    @JoinColumn(name = "channels_id", referencedColumnName = "id"
                            )})
    private Set<Channel> channels = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "packagesInContract", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Contract> contracts = new HashSet<>();


@ManyToOne
@JoinColumn(name = "providers_id",nullable = true)
private Providers providersInpackage;

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public Providers getProvidersInpackage() {
        return providersInpackage;
    }

    public void setProvidersInpackage(Providers providersInpackage) {
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

    public Pack(int id, String name, Double price, Set<Channel> channels) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.channels = channels;
    }

    public Pack(Providers providersInpackage) {
        this.providersInpackage = providersInpackage;
    }

    public Pack(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Pack(String name) {
        this.name = name;
    }

    public Pack(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pack() {
    }


    public void addchannel(Channel channel) {
        channels.add(channel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCon(Contract contract){
        contracts.add(contract);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return name.equals(pack.name) && providersInpackage.equals(pack.providersInpackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, providersInpackage);
    }
}
