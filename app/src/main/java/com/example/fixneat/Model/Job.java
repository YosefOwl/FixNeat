package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Job implements Parcelable {

    protected String description;
    protected double length;
    protected double width;
    protected double height;
    protected String imgUrl;
    protected double price;
    protected double cost;
    protected String note;

    public Job () {

    }

    protected Job(@NonNull Parcel in) {
        description = in.readString();
        length = in.readDouble();
        width = in.readDouble();
        height = in.readDouble();
        imgUrl = in.readString();
        price = in.readInt();
        cost = in.readInt();
        note = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeDouble(length);
        dest.writeDouble(width);
        dest.writeDouble(height);
        dest.writeString(imgUrl);
        dest.writeDouble(price);
        dest.writeDouble(cost);
        dest.writeString(note);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getLength() {
        return length;
    }

    public Job setLength(double length) {
        this.length = length;
        return this;
    }

    public double getWidth() {
        return width;
    }

    public Job setWidth(double width) {
        this.width = width;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public Job setHeight(double height) {
        this.height = height;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Job setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Job setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Job setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Job setNote(String note) {
        this.note = note;
        return this;
    }

    @Override
    public String toString() {
        return  "Description: " + getDescription()  +
                "\nLength: " + getLength() + " Width: " + getWidth() + " Height: " + getHeight() +
                "\nPrice: " + getPrice() + " Cost: " + getCost() +
                "\nNote: " + getNote();
    }
}
