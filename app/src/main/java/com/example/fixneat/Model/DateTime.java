package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DateTime implements Parcelable {

    String date;
    String time;


    public DateTime() {

    }

    protected DateTime(Parcel in) {
        date = in.readString();
        time = in.readString();
    }

    public static final Creator<DateTime> CREATOR = new Creator<DateTime>() {
        @Override
        public DateTime createFromParcel(Parcel in) {
            return new DateTime(in);
        }

        @Override
        public DateTime[] newArray(int size) {
            return new DateTime[size];
        }
    };

    public String getDate() {
        return date;
    }

    public DateTime setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTime() {
        return time;
    }

    public DateTime setTime(String time) {
        this.time = time;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(time);
    }

    @Override
    public String toString() {
        return "" + date + ", " + time + "";
    }
}
