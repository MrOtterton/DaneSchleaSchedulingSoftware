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
public class Address {
    private String addressID;
    private String address;
    private String addressTwo;
    private String cityID;
    private String postalCode;
    private String phone;

    //Constructor
    
    public Address(String addressID, String address, String addressTwo, String cityID, String postalCode, String phone) {
        this.addressID = addressID;
        this.address = address;
        this.addressTwo = addressTwo;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    //Getter

    public String getAddressID() {
        return addressID;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public String getCityID() {
        return cityID;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }
    
    //Setter

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //Address validation
    
    public static String addressValidate(String address, String postalCode, String phone, String errorMessage) throws IOException{
        if(address == null){
            errorMessage = errorMessage + "Invalid address.";
        }
        else if(postalCode == null){
            errorMessage = errorMessage + "Invalid postal code.";
        }
        else if(phone == null){
            errorMessage = errorMessage + "Invalid phone.";
        }
        return errorMessage = "";
    }
}
