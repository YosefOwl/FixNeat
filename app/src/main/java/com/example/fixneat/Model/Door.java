package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Door extends Job implements Parcelable {

    private String doorType;
    private String glassType;
    private String profileType;
    private String color;

    private boolean includeMosquitoNet;

    public Door () {

    }

    protected Door(Parcel in) {
        super(in);
        doorType = in.readString();
        glassType = in.readString();
        profileType = in.readString();
        color = in.readString();
        includeMosquitoNet = in.readByte() != 0;
    }

    public static final Creator<Door> CREATOR = new Creator<Door>() {
        @Override
        public Door createFromParcel(Parcel in) {
            return new Door(in);
        }

        @Override
        public Door[] newArray(int size) {
            return new Door[size];
        }
    };

    public String getDoorType() {
        return doorType;
    }

    public Door setDoorType(String doorType) {
        this.doorType = doorType;
        return this;
    }

    public String getGlassType() {
        return glassType;
    }

    public Door setGlassType(String glassType) {
        this.glassType = glassType;
        return this;
    }

    public String getProfileType() {
        return profileType;
    }

    public Door setProfileType(String profileType) {
        this.profileType = profileType;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Door setColor(String color) {
        this.color = color;
        return this;
    }

    public boolean isIncludeMosquitoNet() {
        return includeMosquitoNet;
    }

    public Door setIncludeMosquitoNet(boolean includeMosquitoNet) {
        this.includeMosquitoNet = includeMosquitoNet;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(doorType);
        parcel.writeString(glassType);
        parcel.writeString(profileType);
        parcel.writeString(color);
        parcel.writeByte((byte) (includeMosquitoNet ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Door{" +
                "doorType='" + doorType + '\'' +
                ", glassType='" + glassType + '\'' +
                ", profileType='" + profileType + '\'' +
                ", color='" + color + '\'' +
                ", includeMosquitoNet=" + includeMosquitoNet +
                '}';
    }
}
