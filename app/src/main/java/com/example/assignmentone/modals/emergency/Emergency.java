package com.example.assignmentone.modals.emergency;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Emergency {
    /*{
        "id": 1,
        "name": "Max Hospital",
        "description": "",
        "contact_type": 2,
        "address_id": 4,
        "contacts": [
            {
                "emergency_contact_id": 1,
                "name": "Rajesh",
                "email": null,
                "primary_contact_no": "123",
                "secondary_contact_no": null,
                "display_order": 1
            }
        ],
        "address": {
            "id": 4,
            "address_line1": "W-3, near Radisson Blu Hotel",
            "address_line2": "Vaishali Sector-1",
            "postal_code": "201012",
            "city_id": 1,
            "latitude": 28.634586,
            "longitude": 77.330902,
            "city": {
                "id": 1,
                "display_name": "Ghaziabad",
                "name": "Ghaziabad",
                "region_id": 1,
                "region": {
                    "id": 1,
                    "display_name": "Uttar Pradesh",
                    "name": "Uttar Pradesh",
                    "country_id": 1,
                    "country": {
                        "id": 1,
                        "name": "India",
                        "display_name": "India"
                    }
                }
            },
            "display_label": "W-3, near Radisson Blu Hotel, Vaishali Sector-1, Ghaziabad, Uttar Pradesh-201012"
        },
        "distance_km": 1.9,
        "display_order": 1
    },*/

    private int id;
    private String name;
    private String description;
    private Integer contact_type;
    private Integer address_id;
    private List<Contacts> contacts;
    private Address address;
    //private String[] contacts;
    private double distance_km;


    @SerializedName("display_order")
    private int order;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getContact_type() {
        return contact_type;
    }

    public void setContact_type(Integer contact_type) {
        this.contact_type = contact_type;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getDistance_km() {
        return distance_km;
    }

    public void setDistance_km(double distance_km) {
        this.distance_km = distance_km;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
