package com.sktl.sm.data.entity;

/**
 * Created by USER-PC on 20.09.2017.
 */

public class Pointer {
    private String idPointer;
    private String name;
    private double latitude;
    private double longitude;
    private String image;
    private String description;

    public String getIdPointer() {
        return idPointer;
    }

    public void setIdPointer(String idPointer) {
        this.idPointer = idPointer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
