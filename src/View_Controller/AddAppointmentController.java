/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Appointment;
import static Util.mainDB.dbConnect;
import static Util.mainDB.getConn;
import static View_Controller.LoginController.currentUser;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
public class AddAppointmentController implements Initializable {
    
    //UI items
    @FXML
    private Label labelManApp;
    @FXML
    private Button mAppSave;
    @FXML
    private Button mAppCancel;
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
    
    int custID;
    
    @FXML
    private void handleManageAppointmentCancel(ActionEvent event) throws IOException{
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
    
    //save current appointment and check for valid fields
    @FXML
    private void handleManageAppointmentAdd(ActionEvent event) throws IOException{
        String apCust = mAppNameField.getText();
        String apTitle = mAppTitle.getValue();
        String apDesc = mAppDescField.getText();
        String apLoc = mAppLoc.getValue();
        String apURL = mAppURLField.getText();
        String apST = mAppStart.getValue();
        String apET = mAppEnd.getValue();
        LocalDate apSD = mAppDateStart.getValue();
        LocalDate apED = mAppDateEnd.getValue();
        
        //combine dates and times and format for database insertion
        LocalDateTime apptStart = LocalDateTime.of(apSD, LocalTime.parse(apST, timeF));
        LocalDateTime apptEnd = LocalDateTime.of(apED, LocalTime.parse(apET, timeF));
        ZonedDateTime zoneStart = apptStart.atZone(zID).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime zoneEnd = apptEnd.atZone(zID).withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp timeStart = Timestamp.valueOf(zoneStart.toLocalDateTime());
        Timestamp timeEnd = Timestamp.valueOf(zoneEnd.toLocalDateTime());
        
        //validate appointment
        if(Appointment.appointmentValidate(apTitle, apDesc, apLoc, apCust, apURL, apptStart, apptEnd) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error submitting appointment");
            alert.setContentText("One or more invalid fields.");
            alert.showAndWait();
        }
        else{
            try{
            dbConnect();
            //check customer exists and get ID
            if(checkCustomer(apCust)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("Error");
                    alert.setHeaderText("Customer error");
                    alert.setContentText("Customer doesn't exist");
                    alert.showAndWait();
                    return;
            }
        
            //check overlap
            if(checkApptTime(timeStart, timeEnd)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("Error");
                    alert.setHeaderText("Time error");
                    alert.setContentText("Time overlaps with another appointment.");
                    alert.showAndWait();
                    return;
            }
            
            PreparedStatement appStat = getConn().prepareStatement("INSERT INTO appointment "
                    + "(customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
            appStat.setInt(1, custID);
            appStat.setString(2, apTitle);
            appStat.setString(3, apDesc);
            appStat.setString(4, apLoc);
            appStat.setString(5, apCust);
            appStat.setString(6, apURL);
            appStat.setTimestamp(7, timeStart);
            appStat.setTimestamp(8, timeEnd);
            appStat.setString(9, currentUser);
            appStat.setString(10, currentUser);
            appStat.executeUpdate();
            
            //go back to appt window
            Parent apptWindow = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
            Scene scene = new Scene(apptWindow);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            
            } 
            catch(SQLException se){
            System.out.println("SQL error");
            }
        }
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
    
    /**set time boxes to be between 8am and 6 pm (local time) - business hours
     * t is set to max 20 to allow for boxes to reach 5:30 pm - 18 stop at 4:30
     */
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
        mAppLoc.setValue("New York");
    }
    
    //set title choicebox
    private void setTitle(){
        mAppTitle.getItems().add("Initial Consultation");
        mAppTitle.getItems().add("Consultation");
        mAppTitle.getItems().add("Final Consultation");
        mAppTitle.setValue("Initial Consultation");
    }
    
    //check that customer exists and return ID
    private boolean checkCustomer(String custName){

        try{
            PreparedStatement chkStat = getConn().prepareStatement("SELECT customerId, customerName "
                    + "FROM customer WHERE customerName = ?");
            chkStat.setString(1, custName);
            ResultSet chkRS = chkStat.executeQuery();
            
            while(chkRS.next()){
                String checkThis = chkRS.getString("customerName");
                if(checkThis.contentEquals(custName)){
                    custID = (int) chkRS.getInt("customerId");
                    return false;
                }
            } 
        } catch (SQLException ex) {
            System.out.println("SQL error during customer check");
        }
        return true;
    }
    
    //check appointment for overlap
    private boolean checkApptTime(Timestamp start, Timestamp end){
        try{
            PreparedStatement timeCheck = getConn().prepareStatement("SELECT * FROM appointment "
                    + "WHERE (? BETWEEN start AND end OR ? BETWEEN start AND end OR ? < start AND ? > end) "
                    + "AND (createdBy = ?)");
            timeCheck.setTimestamp(1, start);
            timeCheck.setTimestamp(2, end);
            timeCheck.setTimestamp(3, start);
            timeCheck.setTimestamp(4, end);
            timeCheck.setString(5, currentUser);
            ResultSet timeRS = timeCheck.executeQuery();
            if(timeRS.next()){
                return true;
            }
        } 
        catch (SQLException ex) {
            System.out.println("SQL error during time check");
        }
        return false;
    }
    
}
