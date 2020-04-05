package com.example.assignmentone.modals.emergency;

import java.util.List;

public class AddressCity {

    private int id;
    private String display_name;
    private String name;
    private int region_id;
    private AddressCityRegion region;

    /*
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
            }*/


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

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public AddressCityRegion getRegion() {
        return region;
    }

    public void setRegion(AddressCityRegion region) {
        this.region = region;
    }
}
