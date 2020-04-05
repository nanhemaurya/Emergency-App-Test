package com.example.assignmentone.modals;


import com.example.assignmentone.modals.emergency.Contacts;

import java.util.List;

public class MainActivityContentViewList {
    private int id;
    private String title;
    private String fullAddress;
    private String addressLat;
    private String addressLng;
    private String distanceApprox;
    private List<Contacts> contacts;

    public MainActivityContentViewList(int id, String title, String fullAddress, String addressLat, String addressLng, String distanceApprox, List<Contacts> contacts) {
        this.id = id;
        this.title = title;
        this.fullAddress = fullAddress;
        this.addressLat = addressLat;
        this.addressLng = addressLng;
        this.distanceApprox = distanceApprox;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getAddressLat() {
        return addressLat;
    }

    public void setAddressLat(String addressLat) {
        this.addressLat = addressLat;
    }

    public String getAddressLng() {
        return addressLng;
    }

    public void setAddressLng(String addressLng) {
        this.addressLng = addressLng;
    }

    public String getDistanceApprox() {
        return distanceApprox;
    }

    public void setDistanceApprox(String distanceApprox) {
        this.distanceApprox = distanceApprox;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}
