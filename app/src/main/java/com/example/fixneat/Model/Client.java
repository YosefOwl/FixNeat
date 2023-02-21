package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Client implements Parcelable {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private Address address;

    private String id;

    public Client() {

    }

    protected Client(@NonNull Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        emailAddress = in.readString();
        phoneNumber = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
        id = in.readString();
    }

    public String getLastName() {
        return lastName;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Client setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Client setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Client setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getClientID() {
        return id;
    }

    public Client setClientID() {
        this.id = UUID.randomUUID().toString();
        return this;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(emailAddress);
        parcel.writeString(phoneNumber);
        parcel.writeParcelable(address, i);
        parcel.writeString(id);
    }

    @Override
    public String toString() {
        return "Name: "  + firstName  + " " + lastName + "\n" +
                "Email: " + emailAddress + "\n" +
                "Phone: " + phoneNumber + "\n" +
                "Address:" + "\n"+ address;
    }
}
