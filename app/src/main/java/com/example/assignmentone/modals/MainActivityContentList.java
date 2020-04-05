package com.example.assignmentone.modals;

public class MainActivityContentList {
    private int id;
    private String title;
    private String shortDesc;
    private String desc;

    public MainActivityContentList(int id, String title, String shortDesc, String desc) {
        this.id = id;
        this.title = title;
        this.shortDesc = shortDesc;
        this.desc = desc;
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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
