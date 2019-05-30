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
public class Country {
    private String countryID;
    private String country;
    
    public Country(String countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }
    
    //Getter
    
    public String getCountryID(){
        return countryID;
    }
    
    public String getCountry(){
        return country;
    }
    
    //Setter
    
    public void setCountryID(String countryID){
        this.countryID = countryID;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public static String countryValidate(String country, String errorMessage) throws IOException{
        if(country == null){
            errorMessage = errorMessage + "Invalid country.";
        }
        return errorMessage = "";
    }
}
