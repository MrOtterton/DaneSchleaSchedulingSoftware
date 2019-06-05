/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *scheduleNow Users able to change appoStringments
 * 
 * @author Dane Schlea
 */
public class User {
    private Integer userID;
    private String userName;
    private String password;
    private Integer userActive;
    
    //Constructor
    
    public User(Integer userID, String userName, String password, Integer userActive){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userActive = userActive;
    }
    
    public User(){
        
    }
    
    //Getters

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserActive() {
        return userActive;
    }
    
    //Setters

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserActive(Integer userActive) {
        this.userActive = userActive;
    }
    
    
}
