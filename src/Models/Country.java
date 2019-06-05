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
    private Integer countryID;
    private String country;
    
    public Country(Integer countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }
    
    //Getter
    
    public Integer getCountryID(){
        return countryID;
    }
    
    public String getCountry(){
        return country;
    }
    
    //Setter
    
    public void setCountryID(Integer countryID){
        this.countryID = countryID;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public static Boolean countryValidate(String country) throws IOException{
        if(country.equals("US") || country.equals("UK") || country.equals("Canada") || country.equals("Norway")){
            return true;
        }
        return false;
    }
}
