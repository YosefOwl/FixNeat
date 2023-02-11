package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private String city;
    private String street;
    private int houseNum;


    public Address() {
    }

    protected Address(Parcel in) {
        city = in.readString();
        street = in.readString();
        houseNum = in.readInt();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public Address setHouseNum(int houseNum) {
        this.houseNum = houseNum;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(city);
        parcel.writeString(street);
        parcel.writeInt(houseNum);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                '}';
    }

}
