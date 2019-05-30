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
    private String userID;
    private String userName;
    private String password;
    private String userActive;
    
    //Constructor
    
    public User(String userID, String userName, String password, String userActive){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userActive = userActive;
    }
    
    public User(){
        
    }
    
    //Getters

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserActive() {
        return userActive;
    }
    
    //Setters

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }
    
    
}
