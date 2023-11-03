package com.example.visitbzu.features.map;

import com.google.android.gms.maps.model.LatLng;

public class sararom_Marker {
    private LatLng loc;
    private String name;

    public sararom_Marker(LatLng loc, String name) {
        this.loc = loc;
        this.name = name;
    }

    public LatLng getLoc() {
        return loc;
    }

    public void setLoc(LatLng pos) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
