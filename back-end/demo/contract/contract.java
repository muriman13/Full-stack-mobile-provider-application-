package com.example.demo.contract;

import com.example.demo.channels.channel;
import com.example.demo.clients.clients;
import com.example.demo.pack.pack;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contract")
public class contract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date start_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date end_date;
    double monthly_price;
    //int package_id;
    double price;
    String name;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "client_id", referencedColumnName = "id",nullable = true)
    private com.example.demo.clients.clients clients;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "channels_in_contract",
            joinColumns = {
                    @JoinColumn(name = "contract_id", referencedColumnName = "id"
                    )},
            inverseJoinColumns = {
                    @JoinColumn(name = "channel_id", referencedColumnName = "id"
                    )})
    private Set<channel> channelsInContract = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "packages_in_contract",
            joinColumns = {
                    @JoinColumn(name = "contract_id", referencedColumnName = "id"
                    )},
            inverseJoinColumns = {
                    @JoinColumn(name = "packages_id", referencedColumnName = "id"
                    )})
    private Set<pack> packagesInContract = new HashSet<>();

    public contract(Date start_date, Date end_date, double price, String name) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.name = name;
    }
    public Set<channel> getChannelsInContract() {
        return channelsInContract;
    }

    public void setChannelsInContract(Set<channel> channelsInContract) {
        this.channelsInContract = channelsInContract;
    }

    public Set<pack> getPackagesInContract() {
        return packagesInContract;
    }

    public void setPackagesInContract(Set<pack> packagesInContract) {
        this.packagesInContract = packagesInContract;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public com.example.demo.clients.clients getClients() {
        return clients;
    }

    public void setClients(com.example.demo.clients.clients clients) {
        this.clients = clients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public double getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(double monthly_price) {
        this.monthly_price = monthly_price;
    }

//    public int getPackage_id() {
//        return package_id;
//    }
//
//    public void setPackage_id(int package_id) {
//        this.package_id = package_id;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public contract(int id, Date start_date, Date end_date, double monthly_price, int package_id, double price, Set<channel> channelsInContract, Set<pack> packagesInContract) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
      //  this.package_id = package_id;
        this.price = price;
        this.channelsInContract = channelsInContract;
          this.packagesInContract = packagesInContract;
    }

    public contract(int id, Date start_date, Date end_date, double monthly_price, int package_id, double price) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
     //   this.package_id = package_id;
        this.price = price;
    }

    public contract() {
    }

    public void addChannels(channel channel){
        channelsInContract.add(channel);
    }
    public void addPackage(pack packages){
        packagesInContract.add(packages);
    }

    public contract(int id, Date start_date, Date end_date, double monthly_price, double price, String name, com.example.demo.clients.clients clients, Set<channel> channelsInContract, Set<pack> packagesInContract) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
        this.price = price;
        this.name = name;
        this.clients = clients;


    }

    public contract(int id, Date start_date, Date end_date, double monthly_price, double price, String name, com.example.demo.clients.clients clients) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
        this.price = price;
        this.name = name;
        this.clients = clients;
    }

    public contract(String name) {
        this.name = name;
    }
}
