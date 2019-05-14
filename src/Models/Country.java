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
public class Country {
    private int countryID;
    private String country;
    
    public Country(int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }
    
    //Getter
    
    public int getCountryID(){
        return countryID;
    }
    
    public String getCountry(){
        return country;
    }
    
    //Setter
    
    public void setCountryID(int countryID){
        this.countryID = countryID;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
}
