package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pergola extends Job implements Parcelable {

    private String materialType;
    private String profileType;
    private String color;

    private boolean includeRainCover;
    private boolean includeShading;

    public Pergola() {

    }

    protected Pergola(Parcel in) {
        super(in);
        materialType = in.readString();
        profileType = in.readString();
        color = in.readString();
        includeRainCover = in.readByte() != 0;
        includeShading = in.readByte() != 0;
    }

    public static final Creator<Pergola> CREATOR = new Creator<Pergola>() {
        @Override
        public Pergola createFromParcel(Parcel in) {
            return new Pergola(in);
        }

        @Override
        public Pergola[] newArray(int size) {
            return new Pergola[size];
        }
    };

    public String getMaterialType() {
        return materialType;
    }

    public Pergola setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getProfileType() {
        return profileType;
    }

    public Pergola setProfileType(String profileType) {
        this.profileType = profileType;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Pergola setColor(String color) {
        this.color = color;
        return this;
    }

    public boolean isIncludeRainCover() {
        return includeRainCover;
    }

    public Pergola setIncludeRainCover(boolean includeRainCover) {
        this.includeRainCover = includeRainCover;
        return this;
    }

    public boolean isIncludeShading() {
        return includeShading;
    }

    public Pergola setIncludeShading(boolean includeShading) {
        this.includeShading = includeShading;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(materialType);
        parcel.writeString(profileType);
        parcel.writeString(color);
        parcel.writeByte((byte) (includeRainCover ? 1 : 0));
        parcel.writeByte((byte) (includeShading ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Pergola{" +
                "materialType='" + materialType + '\'' +
                ", profileType='" + profileType + '\'' +
                ", color='" + color + '\'' +
                ", includeRainCover=" + includeRainCover +
                ", includeShading=" + includeShading +
                '}';
    }
}
