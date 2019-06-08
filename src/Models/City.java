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
public class City {
    private Integer cityID;
    private String city;
    private Integer countryID;

    //Constructors
    public City(Integer cityID, String city, Integer countryID) {
        this.cityID = cityID;
        this.city = city;
        this.countryID = countryID;
    }
    
    public City(Integer cityID, String city){
        this.cityID = cityID;
        this.city = city;
    }
    
    //Getter

    public Integer getCityID() {
        return this.cityID;
    }

    public String getCity() {
        return this.city;
    }

    public Integer getCountryID() {
        return this.countryID;
    }
    
    //Setter

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }
    
    public static Boolean cityValidate(String city) throws IOException{
        if(city.equals("New York") || city.equals("Los Angeles") || city.equals("Houston") || city.equals("Salt Lake City") 
                || city.equals("Lancaster") || city.equals("London") || city.equals("Glasgow") || city.equals("Toronto") 
                || city.equals("Vancouver") || city.equals("Ottawa") || city.equals("Oslo") || city.equals("Bergen") || city.equals("Trondheim")){
            return true;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Error submitting appointment");
        alert.setContentText("City field invalid.");
        alert.showAndWait();
        return false;
    }
    
}
