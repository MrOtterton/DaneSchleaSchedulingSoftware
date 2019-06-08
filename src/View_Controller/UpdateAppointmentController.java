/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
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
    private Label labelAppUrl;
    @FXML
    private Label labelAppStart;
    @FXML
    private Label labelAppEnd;
    @FXML
    private TextField mAppNameField;
    @FXML
    private ChoiceBox<String> mAppTitle;
    @FXML
    private TextArea mAppDescField;
    @FXML
    private ChoiceBox<String> mAppLoc;
    @FXML
    private TextField mAppURLField;
    @FXML
    private DatePicker mAppDateStart;
    @FXML
    private DatePicker mAppDateEnd;
    @FXML
    private ChoiceBox<String> mAppStart;
    @FXML
    private ChoiceBox<String> mAppEnd;
    
    //Set times for start and end
    private ObservableList<String> timeSetter;
    //time formatter
    private final DateTimeFormatter timeF = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
    private final ZoneId zID = ZoneId.systemDefault();
    
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLocation();
        setTimes();
        setTitle();
    }    
    
    //set location choicebox
    private void setLocation(){
        //location choicebox choices
        mAppLoc.getItems().add("New York");
        mAppLoc.getItems().add("Los Angeles");
        mAppLoc.getItems().add("Houston");
        mAppLoc.getItems().add("Salt Lake City");
        mAppLoc.getItems().add("Lancaster");
        mAppLoc.getItems().add("London");
        mAppLoc.getItems().add("Glasgow");
        mAppLoc.getItems().add("Toronto");
        mAppLoc.getItems().add("Vancouver");
        mAppLoc.getItems().add("Ottawa");
        mAppLoc.getItems().add("Oslo");
        mAppLoc.getItems().add("Bergen");
        mAppLoc.getItems().add("Trondheim");
    }
    
    //set title choicebox
    private void setTitle(){
        mAppTitle.getItems().add("Initial Consultation");
        mAppTitle.getItems().add("Consultation");
        mAppTitle.getItems().add("Final Consultation");
    }
    
    private void setTimes(){
        timeSetter = FXCollections.observableArrayList();
        LocalTime timeStart = LocalTime.MIDNIGHT.plusHours(8);
        for(int t = 0; t < 20; t++){
            timeSetter.add(timeStart.format(timeF));
            timeStart = timeStart.plusMinutes(30);
        }
        mAppStart.getItems().addAll(timeSetter);
        mAppEnd.getItems().addAll(timeSetter);
    }
    
}
