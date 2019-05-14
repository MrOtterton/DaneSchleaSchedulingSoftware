/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDateTime;

/**
 *
 * @author Dane Schlea
 */
public class Appointment {
    private int appointmentID;
    private int customerID;
    private int userID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String url;
    private LocalDateTime start;
    private LocalDateTime end;

    public Appointment(int appointmentID, int customerID, int userID, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end) {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.url = url;
        this.start = start;
        this.end = end;
    }
    
    //Getter

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
    
    //Setter

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    
    
}
