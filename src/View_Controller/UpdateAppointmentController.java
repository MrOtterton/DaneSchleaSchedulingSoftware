/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shakla
 */
public class UpdateAppointmentController implements Initializable {
    
    //UI items
    @FXML
    private Label labelManApp;
    @FXML
    private Button uAppSave;
    @FXML
    private Button uAppCancel;
    @FXML
    private Label labelAppName;
    @FXML
    private Label labelAppTitle;
    @FXML
    private Label labelAppDesc;
    @FXML
    private Label labelAppLoc;
    @FXML
    private Label labelAppCon;
    @FXML
    private Label labelAppUrl;
    @FXML
    private Label labelAppStart;
    @FXML
    private Label labelAppEnd;
    @FXML
    private TextField mAppNameField;
    @FXML
    private TextField mAppTitleField;
    @FXML
    private TextArea mAppDescField;
    @FXML
    private TextField mAppLocField;
    @FXML
    private TextField mAppConField;
    @FXML
    private TextField mAppURLField;
    @FXML
    private DatePicker mAppDateStart;
    @FXML
    private DatePicker mAppDateEnd;
    @FXML
    private ComboBox<String> mAppStart;
    @FXML
    private ComboBox<String> mAppEnd;
    
    @FXML
    private void handleUpdateAppointmentCancel(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm cancellation");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            Parent manageAppointmentCancel = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
            Scene scene = new Scene(manageAppointmentCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            System.out.println("Operation cancelled.");
        }
    }
    
    @FXML
    private void handleUpdateAppointmentSave(ActionEvent event) throws IOException{
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
