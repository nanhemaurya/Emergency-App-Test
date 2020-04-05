package com.example.assignmentone.modals.emergency;


public class Contacts {

    private int emergency_contact_id;
    private String name;
    private String email;
    private String primary_contact_no;
    private String secondary_contact_no;


    public int getEmergency_contact_id() {
        return emergency_contact_id;
    }

    public void setEmergency_contact_id(int emergency_contact_id) {
        this.emergency_contact_id = emergency_contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimary_contact_no() {
        return primary_contact_no;
    }

    public void setPrimary_contact_no(String primary_contact_no) {
        this.primary_contact_no = primary_contact_no;
    }

    public String getSecondary_contact_no() {
        return secondary_contact_no;
    }

    public void setSecondary_contact_no(String secondary_contact_no) {
        this.secondary_contact_no = secondary_contact_no;
    }
}


