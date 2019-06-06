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
    private String start;
    private String end;
    private String customer;
    private String phone;

    public Appointment(Integer appointmentID, Integer customerID, Integer userID, String title, String description, String location, String contact, String url, String start, String end) {
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
    
    public Appointment(String start, String end, String location, String customer, String phone){
        this.start = start;
        this.end = end;
        this.location = location;
        this.customer = customer;
        this.phone = phone;
    }
    
    //Getter

    public String getCustomer(){
        return this.customer;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
    public Integer getAppointmentID() {
        return this.appointmentID;
    }

    public Integer getCustomerID() {
        return this.customerID;
    }

    public Integer getUserID() {
        return this.userID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public String getContact() {
        return this.contact;
    }

    public String getUrl() {
        return this.url;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
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

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
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
