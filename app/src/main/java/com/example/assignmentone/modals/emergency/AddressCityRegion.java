package com.example.assignmentone.modals.emergency;

import java.util.List;

public class AddressCityRegion {

    private int id;
    private String display_name;
    private String name;
    private int country_id;
    private AddressCityRegionCountry country;

    /*
    * "region": {
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
    *
    * */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public AddressCityRegionCountry getCountry() {
        return country;
    }

    public void setCountry(AddressCityRegionCountry country) {
        this.country = country;
    }
}
