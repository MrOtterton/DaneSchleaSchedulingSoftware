/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Appointment;
import static Models.Appointment.getAppointmentList;
import static Util.mainDB.dbConnect;
import static Util.mainDB.getConn;
import static View_Controller.AppointmentsController.getUpdateApptIndex;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //get index from appointments controller for selected appointment
    int updateApptIndex = getUpdateApptIndex();
    private Appointment appointment;
    //selected appointment ID from appointment to be updated
    private int uApptID;
    //customer ID to be used for verification
    private int custID;
    //previously selected appointment details
    private String oldName;
    private String oldTitle;
    private String oldDesc;
    private String oldLoc;
    
    
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
                //error check for old appointmentID sql error
                if(getApptID(oldName, oldTitle, oldDesc, oldLoc)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("Error");
                    alert.setHeaderText("Appointment error");
                    alert.setContentText("Appointment retrieval error, contact helpdesk");
                    alert.showAndWait();
                    return;
                }
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
                if(checkApptTime(timeStart, timeEnd, uApptID)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("Error");
                    alert.setHeaderText("Time error");
                    alert.setContentText("Time overlaps with another appointment.");
                    alert.showAndWait();
                    return;
                    }
                
                PreparedStatement upAppt = getConn().prepareStatement("UPDATE appointment "
                        + "SET customerId = ?, title = ?, description = ?, location = ?, contact = ?, url = ?, start = ?, end = ?, lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = ? "
                        + "WHERE appointmentId = ?");
                upAppt.setInt(1, custID);
                upAppt.setString(2, apTitle);
                upAppt.setString(3, apDesc);
                upAppt.setString(4, apLoc);
                upAppt.setString(5, apCust);
                upAppt.setString(6, apURL);
                upAppt.setTimestamp(7, timeStart);
                upAppt.setTimestamp(8, timeEnd);
                upAppt.setString(9, currentUser);
                upAppt.setInt(10, uApptID);
                upAppt.executeUpdate();
                
                //Go back to appointments window
                Parent customersCancel = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
                Scene scene = new Scene(customersCancel);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch(SQLException se){
                
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
        //get index
        appointment = getAppointmentList().get(updateApptIndex);
        //set values
        String apptName = appointment.getCustomer();
        String apptTitle = appointment.getTitle();
        String apptDesc = appointment.getDescription();
        String apptLoc = appointment.getLocation();
        //set fields -- not datepicker or time
        setLocation();
        setTimes();
        setTitle();
        
        mAppNameField.setText(apptName);
        mAppTitle.setValue(apptTitle);
        mAppDescField.setText(apptDesc);
        mAppLoc.setValue(apptLoc);
        
        //set old values
        oldApptData(apptName, apptTitle, apptDesc, apptLoc);

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
    
    private boolean getApptID(String custName, String title, String desc, String location){
        try{
            PreparedStatement chkStat = getConn().prepareStatement("SELECT appointmentId "
                    + "FROM appointment WHERE contact = ? AND description = ? AND title = ? AND location = ?");
            chkStat.setString(1, custName);
            chkStat.setString(2, desc);
            chkStat.setString(3, title);
            chkStat.setString(4, location);
            ResultSet chkRS = chkStat.executeQuery();
            while(chkRS.next()){
                uApptID = chkRS.getInt("appointmentId");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQL error for old appointment ID");
        }
        System.out.println("appt sql check");
        return true;
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
        System.out.println("cust sql check");
        return true;
    }
    
    //check appointment for overlap
    private boolean checkApptTime(Timestamp start, Timestamp end, Integer appointmentID){
        try{
            PreparedStatement timeCheck = getConn().prepareStatement("SELECT * FROM appointment "
                    + "WHERE (? BETWEEN start AND end OR ? BETWEEN start AND end OR ? < start AND ? > end) "
                    + "AND (createdBy = ?) AND (appointmentId != ?)");
            timeCheck.setTimestamp(1, start);
            timeCheck.setTimestamp(2, end);
            timeCheck.setTimestamp(3, start);
            timeCheck.setTimestamp(4, end);
            timeCheck.setString(5, currentUser);
            timeCheck.setInt(6, appointmentID);
            ResultSet timeRS = timeCheck.executeQuery();
            if(timeRS.next()){
                return true;
            }
        } 
        catch (SQLException ex) {
            System.out.println("SQL error during time check");
        }
        System.out.println("appt time sql check");
        return false;
    }
    
    //make variables from old info
    public void oldApptData(String name, String title, String description, String location){
        oldName = name;
        oldTitle = title;
        oldDesc = description;
        oldLoc = location;
    }
    
}
