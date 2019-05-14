/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;

/**
 *
 * @author Dane Schlea
 */
public class Customer {
    private int customerID;
    private String customerName;
    private int addressID;
    private int custActive;
    
    //Constructor
    
    public Customer(int customerID, String customerName, int addressID, int custActive){
    this.customerID = customerID;
    this.customerName = customerName;
    this.addressID = addressID;
    this.custActive = custActive;
    }

    //Getters
    
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getAddressID() {
        return addressID;
    }

    public int getCusActive() {
        return custActive;
    }

    //Setters
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setCusActive(int cusActive) {
        this.custActive = cusActive;
    }
    
    public static String customerValidate(String customerName, String errorMessage) throws IOException{
        if(customerName == null){
            errorMessage = errorMessage + "Invalid name.";
        }
        return errorMessage = "";
    }
    
}
