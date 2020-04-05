package com.example.assignmentone.modals.emergency;

import java.util.List;

public class Address {

    private int id;
    private String address_line1;
    private String address_line2;
    private String postal_code;
    private int city_id;
    private String latitude;
    private String longitude;
    private AddressCity city;
    private String display_label;




    /*
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
        }
    */


    public int getId() {
        return id;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public AddressCity getCity() {
        return city;
    }

    public String getDisplay_label() {
        return display_label;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCity(AddressCity city) {
        this.city = city;
    }

    public void setDisplay_label(String display_label) {
        this.display_label = display_label;
    }

}
