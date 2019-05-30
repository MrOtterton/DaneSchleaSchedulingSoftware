/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dane Schlea
 */
public class Customer {
    private String customerID;
    private String customerName;
    private String addressID;
    private String custActive;
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private String phone;
    private String address;
    private String addressTwo;
    private String city;
    private String postal;
    private String country;
    
    //Constructor
    
    public Customer(String customerID, String customerName, String addressID, String custActive){
    this.customerID = customerID;
    this.customerName = customerName;
    this.addressID = addressID;
    this.custActive = custActive;
    }
    
    public Customer(String customerName, String phone, String address, String addressTwo, String city, String postal, String country){
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.addressTwo = addressTwo;
        this.city = city;
        this.postal = postal;
        this.country = country;
    }
    
//Getters

    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public String getCountry() {
        return country;
    }
    
    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddressID() {
        return addressID;
    }

    public String getCusActive() {
        return custActive;
    }

    //Setters

    public static void setCustomerList(ObservableList<Customer> customerList) {
        Customer.customerList = customerList;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public void setCusActive(String cusActive) {
        this.custActive = cusActive;
    }
    
    public static String customerValidate(String customerName, String errorMessage) throws IOException{
        if(customerName == null){
            errorMessage = errorMessage + "Invalid name.";
        }
        return errorMessage = "";
    }
    
}
