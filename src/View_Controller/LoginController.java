/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.User;
import static Models.mainDB.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
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
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private PasswordField pField;
    @FXML
    private TextField uField;
    
    //Reference items
    User user = new User();
    private Stage primaryStage;
    private AnchorPane mainMenu;

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
    
    //Check Username and Password for nulls and validates if Username and Password match DB records
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
                user.setUsername(loginRes.getString("userName"));
                user.setPassword(loginRes.getString("password"));
                user.setUserID(loginRes.getInt("userID"));
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
    
    private void showMenu(ActionEvent event) throws IOException{
        Parent showMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene showMenuScene = new Scene(showMenuParent);
        Stage showMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showMenuStage.setScene(showMenuScene);
        showMenuStage.show();
    }
    
}
