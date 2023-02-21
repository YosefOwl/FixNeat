package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Address implements Parcelable {

    private String city;
    private String street;
    private int homeNum;
    private int aptNum;


    public Address() {

    }

    protected Address(@NonNull Parcel in) {
        city = in.readString();
        street = in.readString();
        homeNum = in.readInt();
        aptNum = in.readInt();
    }

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

    public int getHomeNum() {
        return homeNum;
    }

    public Address setHomeNum(int homeNum) {
        this.homeNum = homeNum;
        return this;
    }

    public int getAptNum() {
        return aptNum;
    }

    public Address setAptNum(int aptNum) {
        this.aptNum = aptNum;
        return this;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(city);
        parcel.writeString(street);
        parcel.writeInt(homeNum);
        parcel.writeInt(aptNum);
    }

    @Override
    public String toString() {
        return  ""   + street + " " + homeNum + "/" + aptNum + ", " + city + ".";
    }
}
