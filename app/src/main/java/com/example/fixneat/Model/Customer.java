package com.example.fixneat.Model;

public class Customer {

    private String fName;
    private String lName;
    private String phoneNum;
    private String mailAddress;
    private Address address;


    public Customer() {
    }

    public String getfName() {
        return fName;
    }

    public Customer setfName(String fName) {
        this.fName = fName;
        return this;
    }

    public String getlName() {
        return lName;
    }

    public Customer setlName(String lName) {
        this.lName = lName;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Customer setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public Customer setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Customer setAddress(Address address) {
        this.address = address;
        return this;
    }
}
