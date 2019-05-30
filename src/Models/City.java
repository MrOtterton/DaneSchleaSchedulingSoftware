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
    private int cityID;
    private String city;
    private int countryID;

    //Constructors
    public City(int cityID, String city, int countryID) {
        this.cityID = cityID;
        this.city = city;
        this.countryID = countryID;
    }
    
    public City(int cityID, String city){
        this.cityID = cityID;
        this.city = city;
    }
    
    //Getter

    public int getCityID() {
        return cityID;
    }

    public String getCity() {
        return city;
    }

    public int getCountryID() {
        return countryID;
    }
    
    //Setter

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    
    public static String cityValidate(String city, String errorMessage) throws IOException{
        if(city == null){
            errorMessage = errorMessage + "Invalid city.";
        }
        return errorMessage = "";
    }
    
}
