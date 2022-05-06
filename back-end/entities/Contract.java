package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date start_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date end_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date last_date;
    double total_payments;
    double monthly_price;
    //int package_id;
    double price;
    String name;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "client_id", referencedColumnName = "id",nullable = true)
    private Clients clients;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "channels_in_contract",
            joinColumns = {
                    @JoinColumn(name = "contract_id", referencedColumnName = "id"
                    )},
            inverseJoinColumns = {
                    @JoinColumn(name = "channel_id", referencedColumnName = "id"
                    )})
    private Set<Channel> channelsInContract = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "packages_in_contract",
            joinColumns = {
                    @JoinColumn(name = "contract_id", referencedColumnName = "id"
                    )},
            inverseJoinColumns = {
                    @JoinColumn(name = "packages_id", referencedColumnName = "id"
                    )})
    private Set<Pack> packagesInContract = new HashSet<>();

    public Contract(Date start_date, Date end_date, double price, String name) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.name = name;
    }

    public Date getLast_date() {
        return last_date;
    }

    public void setLast_date(Date last_date) {
        this.last_date = last_date;
    }

    public double getTotal_payments() {
        return total_payments;
    }

    public void setTotal_payments(double total_payments) {
        this.total_payments = total_payments;
    }

    public Set<Channel> getChannelsInContract() {
        return channelsInContract;
    }

    public void setChannelsInContract(Set<Channel> channelsInContract) {
        this.channelsInContract = channelsInContract;
    }

    public Set<Pack> getPackagesInContract() {
        return packagesInContract;
    }

    public void setPackagesInContract(Set<Pack> packagesInContract) {
        this.packagesInContract = packagesInContract;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
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

    public Contract(int id, Date start_date, Date end_date, double monthly_price, int package_id, double price, Set<Channel> channelsInContract, Set<Pack> packagesInContract) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
      //  this.package_id = package_id;
        this.price = price;
        this.channelsInContract = channelsInContract;
          this.packagesInContract = packagesInContract;
    }

    public Contract(int id, Date start_date, Date end_date, double monthly_price, int package_id, double price) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
     //   this.package_id = package_id;
        this.price = price;
    }

    public Contract() {
    }

    public void addChannels(Channel channel){
        channelsInContract.add(channel);
    }
    public void addPackage(Pack packages){
        packagesInContract.add(packages);
    }

    public Contract(int id, Date start_date, Date end_date, double monthly_price, double price, String name, Clients clients, Set<Channel> channelsInContract, Set<Pack> packagesInContract) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
        this.price = price;
        this.name = name;
        this.clients = clients;


    }

    public Contract(int id, Date start_date, Date end_date, double monthly_price, double price, String name, Clients clients) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthly_price = monthly_price;
        this.price = price;
        this.name = name;
        this.clients = clients;
    }

    public Contract(String name) {
        this.name = name;
    }
}
