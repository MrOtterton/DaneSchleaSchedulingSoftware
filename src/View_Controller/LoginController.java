/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.User;
import Util.Logger;
import static Util.mainDB.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shakla
 */
public class LoginController implements Initializable {
    
    ResourceBundle rb;
    
    //UI items
    @FXML
    private Label labelLogin;
    @FXML
    private Button loginExit;
    @FXML
    private Button loginSubmit;
    @FXML
    private Label labelUserName;
    @FXML
    private Label labelPassword;
    @FXML
    private PasswordField pField;
    @FXML
    private TextField uField;
    
    //Reference items
    User user = new User();
    static String currentUser;

    @FXML
    void handleLoginExit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Exit");
        alert.setHeaderText("Confirm exit.");
        alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .ifPresent(response -> System.exit(0));
    }    
    
    @FXML
    void handleLoginSubmit(ActionEvent event)throws IOException {
        String thisUser = uField.getText();
        String thisPass = pField.getText();
        try{
            User userValid = loginValidation(thisUser, thisPass);
            if(userValid != null){
                setCurrentUser(thisUser);
                if(checkappt15(thisUser) == true){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("Reminder");
                    alert.setHeaderText("Appointment Reminder");
                    alert.setContentText("You have an appointment in the next 15 minutes. Please check your schedule.");
                    Optional<ButtonType> result = alert.showAndWait();
                }
                showMenu(event);
            }
        }
        finally{}    
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private static void setCurrentUser(String userName){
        currentUser = userName;
    }
    
    /*
     * Check UserName and Password for nulls
     * valides username and password in the DB
     * uses lambdas for incorrect username/password entries to exit system
     * logs successful logins in a text file with the user who logged in
     */ 
    User loginValidation(String userName, String password) throws IOException{
        if(userName == null || password == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid login - exiting program.");
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.exit(0));
                }
        try{
            dbConnect();
            PreparedStatement prepS = getConn().prepareStatement("SELECT * FROM user WHERE userName = ? AND password = ?");
            prepS.setString(1, userName);
            prepS.setString(2, password);
            ResultSet loginRes = prepS.executeQuery();
            if(loginRes.next()){
                user.setUserName(loginRes.getString("userName"));
                user.setPassword(loginRes.getString("password"));
                user.setUserID(loginRes.getInt("userId"));
                Logger.log(userName, true);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid login - exiting program.");
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.exit(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    } 
    
    
    //Load Main Menu
    private void showMenu(ActionEvent event) throws IOException{
        Parent showMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene showMenuScene = new Scene(showMenuParent);
        Stage showMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showMenuStage.setScene(showMenuScene);
        showMenuStage.show();
    }

    //check if any appointments are within current time and 15 minutes of user login
    private Boolean checkappt15(String thisUser) {
        LocalDateTime current = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zone2 = current.atZone(zone);
        LocalDateTime local = zone2.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime local2 = local.plusMinutes(15);
        try{
            dbConnect();
            PreparedStatement prepS = getConn().prepareStatement("SELECT * FROM appointment WHERE createdBy = ? AND start BETWEEN '" + local + "'AND '" + local2 + "'");
            prepS.setString(1, thisUser);
            ResultSet resS = prepS.executeQuery();
            if(resS.next()){
                return true;
            }
        } catch (SQLException ee) {
            System.out.println("SQL error.");
            return false;
        }
        return false;
    }
    
}
