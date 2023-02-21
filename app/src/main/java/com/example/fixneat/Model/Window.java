package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Window extends Job implements Parcelable {

    private String windowType;
    private String glassType;
    private String profileType;
    private String color;

    private boolean includeMosquitoNet;

    public Window () {

    }

    protected Window(Parcel in) {
        super(in);
        windowType = in.readString();
        glassType = in.readString();
        profileType = in.readString();
        color = in.readString();
        includeMosquitoNet = in.readByte() != 0;
    }

    public static final Creator<Window> CREATOR = new Creator<Window>() {
        @Override
        public Window createFromParcel(Parcel in) {
            return new Window(in);
        }

        @Override
        public Window[] newArray(int size) {
            return new Window[size];
        }
    };

    public String getWindowType() {
        return windowType;
    }

    public Window setWindowType(String windowType) {
        this.windowType = windowType;
        return this;
    }

    public String getGlassType() {
        return glassType;
    }

    public Window setGlassType(String glassType) {
        this.glassType = glassType;
        return this;
    }

    public String getProfileType() {
        return profileType;
    }

    public Window setProfileType(String profileType) {
        this.profileType = profileType;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Window setColor(String color) {
        this.color = color;
        return this;
    }

    public boolean isIncludeMosquitoNet() {
        return includeMosquitoNet;
    }

    public Window setIncludeMosquitoNet(boolean includeMosquitoNet) {
        this.includeMosquitoNet = includeMosquitoNet;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(windowType);
        parcel.writeString(glassType);
        parcel.writeString(profileType);
        parcel.writeString(color);
        parcel.writeByte((byte) (includeMosquitoNet ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Window{" +
                "windowType='" + windowType + '\'' +
                ", glassType='" + glassType + '\'' +
                ", profileType='" + profileType + '\'' +
                ", color='" + color + '\'' +
                ", includeMosquitoNet=" + includeMosquitoNet +
                '}';
    }
}
