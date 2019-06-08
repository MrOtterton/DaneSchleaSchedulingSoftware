/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author Dane Schlea
 */
public class Customer {
    private Integer customerID;
    private String customerName;
    private Integer addressID;
    private Integer custActive;
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private String phone;
    private String address;
    private String addressTwo;
    private String city;
    private String postal;
    private String country;
    
    //Constructor
    
    public Customer(Integer customerID, String customerName, Integer addressID, Integer custActive){
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
    
    public Integer getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public Integer getCusActive() {
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
    
    
    
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public void setCusActive(Integer cusActive) {
        this.custActive = cusActive;
    }
    
    public static Boolean customerValidate(String customerName) throws IOException{
        if(customerName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.initModality(Modality.NONE);
           alert.setTitle("Error");
           alert.setHeaderText("Error submitting customer");
           alert.setContentText("Name empty.");
           alert.showAndWait();
            return false;
        }
        return true;
    }
    
}
