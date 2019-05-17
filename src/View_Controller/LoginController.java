/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

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
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
