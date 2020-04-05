package com.example.assignmentone.modals.emergency;

public class AddressCityRegionCountry {

    private int id;
    private String name;
    private String display_name;

    /*"country": {
                        "id": 1,
                        "name": "India",
                        "display_name": "India"
                    }*/

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

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
