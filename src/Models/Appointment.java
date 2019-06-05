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
    private Integer appointmentID;
    private Integer customerID;
    private Integer userID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String url;
    private LocalDateTime start;
    private LocalDateTime end;

    public Appointment(Integer appointmentID, Integer customerID, Integer userID, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end) {
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

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Integer getUserID() {
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

    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public void setUserID(Integer userID) {
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
    
    public static Boolean appointmentValidate(String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end) throws IOException{
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        Integer dayOfWeek = startDate.getDayOfWeek().getValue();
        
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();
        
        if(title.isEmpty()){
           return false;
       }
       else if(description.isEmpty()){
           return false;
       }
       else if(location.isEmpty()){
           return false;
       }
       else if(contact.isEmpty()){
           return false;
       }
       else if(url.isEmpty()){
           return false;
       }
       else if(start == end || start == null || end == null){
           return false;
       }
       else if(startDate != endDate){
           return false;
       }
       else if(dayOfWeek == 0 || dayOfWeek == 6){
           return false;
       }
       else if(startTime.isAfter(endTime)){
           return false;
       }
        return true;
    }
    
}
