package com.example.fixneat.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Order {

    enum STATUS{ VISIT, OPEN, PREPEND, CLOSED}

    private String id;
    private Customer customer;
    private ArrayList<Job> jobs;
    private STATUS status;

    public Order(){

    }


    public String getId() {
        return id;
    }

    public Order setId() {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public Order setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public STATUS getStatus() {
        return status;
    }

    public Order setStatus(STATUS STATUS) {
        this.status = STATUS;
        return this;
    }

}
