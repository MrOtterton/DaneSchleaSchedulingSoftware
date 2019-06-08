/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author Dane Schlea
 */
public class Address {
    private Integer addressID;
    private String address;
    private String addressTwo;
    private Integer cityID;
    private String postalCode;
    private String phone;

    //Constructor
    
    public Address(Integer addressID, String address, String addressTwo, Integer cityID, String postalCode, String phone) {
        this.addressID = addressID;
        this.address = address;
        this.addressTwo = addressTwo;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    //Getter

    public Integer getAddressID() {
        return addressID;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public Integer getCityID() {
        return cityID;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }
    
    //Setter

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //Address validation
    
    public static Boolean addressValidate(String address, String postalCode, String phone) throws IOException{
        if(address.isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.initModality(Modality.NONE);
           alert.setTitle("Error");
           alert.setHeaderText("Error submitting address");
           alert.setContentText("Address empty.");
           alert.showAndWait();
            return false;
        }
        else if(postalCode.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.initModality(Modality.NONE);
           alert.setTitle("Error");
           alert.setHeaderText("Error submitting address");
           alert.setContentText("Postal code empty.");
           alert.showAndWait();
            return false;
        }
        else if(phone.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.initModality(Modality.NONE);
           alert.setTitle("Error");
           alert.setHeaderText("Error submitting address");
           alert.setContentText("Phone empty.");
           alert.showAndWait();
            return false;
        }
        return true;
    }
}
