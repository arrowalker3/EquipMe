package com.example.equipme;

import java.util.Date;

public class Equipment {
    private String brand;
    private String type;
    private String serialNumber;
    private String assignedTo;
    private Date dateGiven;
    public String notes;


    // Getters
    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Date getDateGiven() {
        return dateGiven;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setDateGiven(Date dateGiven) {
        this.dateGiven = dateGiven;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void display() {

    }

}
