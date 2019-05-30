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
public class City {
    private String cityID;
    private String city;
    private String countryID;

    //Constructors
    public City(String cityID, String city, String countryID) {
        this.cityID = cityID;
        this.city = city;
        this.countryID = countryID;
    }
    
    public City(String cityID, String city){
        this.cityID = cityID;
        this.city = city;
    }
    
    //Getter

    public String getCityID() {
        return cityID;
    }

    public String getCity() {
        return city;
    }

    public String getCountryID() {
        return countryID;
    }
    
    //Setter

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
    
    public static String cityValidate(String city, String errorMessage) throws IOException{
        if(city == null){
            errorMessage = errorMessage + "Invalid city.";
        }
        return errorMessage = "";
    }
    
}
