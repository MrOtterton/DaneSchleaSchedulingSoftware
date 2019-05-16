/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    
    public static String appointmentValidate(String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end, String errorMessage) throws IOException{
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        int dayOfWeek = startDate.getDayOfWeek().getValue();
        
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();
        
        if(title == null){
           return errorMessage = errorMessage + "Invalid title.";
       }
       else if(description == null){
           return errorMessage = errorMessage + "Invalid description.";
       }
       else if(location == null){
           return errorMessage = errorMessage + "Invalid location.";
       }
       else if(contact == null){
           return errorMessage = errorMessage + "Invalid contact.";
       }
       else if(url == null){
           return errorMessage = errorMessage + "Invalid url.";
       }
       else if(start == end || start == null || end == null){
           return errorMessage = errorMessage + "Invalid times.";
       }
       else if(startDate != endDate){
           return errorMessage = errorMessage + "Appointments cannot span multiple days.";
       }
       else if(dayOfWeek == 0 || dayOfWeek == 6){
           return errorMessage = errorMessage + "Can not schedule weekend appointments.";
       }
       else if(startTime.isAfter(endTime)){
           return errorMessage = errorMessage + "Can not set start before end.";
       }
        return errorMessage = "";
    }
    
}
