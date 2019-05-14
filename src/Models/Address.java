/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Dane Schlea
 */
public class Address {
    private int addressID;
    private String address;
    private String addressTwo;
    private int cityID;
    private String postalCode;
    private String phone;

    //Constructor
    
    public Address(int addressID, String address, String addressTwo, int cityID, String postalCode, String phone) {
        this.addressID = addressID;
        this.address = address;
        this.addressTwo = addressTwo;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    //Getter

    public int getAddressID() {
        return addressID;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public int getCityID() {
        return cityID;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }
    
    //Setter

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
