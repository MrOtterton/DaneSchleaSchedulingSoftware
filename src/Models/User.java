/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *scheduleNow Users able to change appointments
 * 
 * @author Dane Schlea
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    private int userActive;
    
    //Constructor
    
    public User(int userID, String userName, String password, int userActive){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userActive = userActive;
    }
    
    public User(){
        
    }
    
    //Getters

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserActive() {
        return userActive;
    }
    
    //Setters

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserActive(int userActive) {
        this.userActive = userActive;
    }
    
    
}
