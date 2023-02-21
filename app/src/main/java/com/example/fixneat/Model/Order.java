package com.example.fixneat.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.UUID;

public class Order implements Parcelable {

    public final static String STATUS_CREATED = "CREATED";
    public final static String STATUS_MEASURED = "MEASURED";
    public final static String STATUS_PREPEND = "PREPEND";
    public final static String STATUS_INSTALLED = "CLOSED";

    private String id;
    private Client client;
    private String currentStatus;
    private String note;
    private DateTime measureVisitD_T;
    private DateTime installVisitD_T;

    private ArrayList<Job> jobs;

    public Order(){
    }

    protected Order(Parcel in) {
        id = in.readString();
        client = in.readParcelable(Client.class.getClassLoader());
        currentStatus = in.readString();
        note = in.readString();
        measureVisitD_T = in.readParcelable(DateTime.class.getClassLoader());
        installVisitD_T = in.readParcelable(DateTime.class.getClassLoader());
        jobs = in.createTypedArrayList(Job.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };


    public DateTime getMeasureVisitD_T() {
        return measureVisitD_T;
    }

    public Order setMeasureVisitD_T(DateTime measureVisitD_T) {
        this.measureVisitD_T = measureVisitD_T;
        return this;
    }

    public DateTime getInstallVisitD_T() {
        return installVisitD_T;
    }

    public Order setInstallVisitD_T(DateTime installVisitD_T) {
        this.installVisitD_T = installVisitD_T;
        return this;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public Order setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Order setClient(Client client) {
        this.client = client;
        return this;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public Order setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Order setNote(String note) {
        this.note = note;
        return this;
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeParcelable(client, i);
        parcel.writeString(currentStatus);
        parcel.writeString(note);
        parcel.writeParcelable(measureVisitD_T, i);
        parcel.writeParcelable(installVisitD_T, i);
        parcel.writeTypedList(jobs);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", currentStatus='" + currentStatus + '\'' +
                ", note='" + note + '\'' +
                ", measureVisitD_T=" + measureVisitD_T +
                ", installVisitD_T=" + installVisitD_T +
                ", jobs=" + jobs +
                '}';
    }
}
