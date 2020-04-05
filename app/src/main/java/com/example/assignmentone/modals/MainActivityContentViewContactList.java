package com.example.assignmentone.modals;


public class MainActivityContentViewContactList {
    private int id;
    private String title;
    private String primaryNumber;
    private String secondaryNumber;

    public MainActivityContentViewContactList(int id, String title, String primaryNumber, String secondaryNumber) {
        this.id = id;
        this.title = title;
        this.primaryNumber = primaryNumber;
        this.secondaryNumber = secondaryNumber;
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

    public String getPrimaryNumber() {
        return primaryNumber;
    }

    public void setPrimaryNumber(String primaryNumber) {
        this.primaryNumber = primaryNumber;
    }

    public String getSecondaryNumber() {
        return secondaryNumber;
    }

    public void setSecondaryNumber(String secondaryNumber) {
        this.secondaryNumber = secondaryNumber;
    }
}
