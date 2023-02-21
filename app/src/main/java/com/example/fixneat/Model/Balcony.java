package com.example.fixneat.Model;

import android.os.Parcelable;

public class Balcony extends Job implements Parcelable {

    private String materialType;
    private String profileType;
    private String color;
    private boolean includeRetractableRoof;


    public Balcony() {

    }

    public boolean isIncludeRetractableRoof() {
        return includeRetractableRoof;
    }

    public Balcony setIncludeRetractableRoof(boolean includeRetractableRoof) {
        this.includeRetractableRoof = includeRetractableRoof;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public Balcony setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getProfileType() {
        return profileType;
    }

    public Balcony setProfileType(String profileType) {
        this.profileType = profileType;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Balcony setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {

        String roof = (includeRetractableRoof == true) ?"yes":"no";
        return "Balcony: \n" +
                "\nMaterial: " + materialType +
                "Profile Type: " + profileType + ", Color: " + color +
                "\nInclude Retractable Roof: " + roof+
                "\n Description: " + getDescription()  +
                "\nLength: " + getLength() + " Width: " + getWidth() + " Height: " + getHeight() +
                "\nPrice: " + getPrice() + " Cost: " + getCost() +
                "\nNote: " + getNote();

    }
}