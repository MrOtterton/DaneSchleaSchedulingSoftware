/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Appointment;
import static Models.Appointment.getAppointmentList;
import static Models.Appointment.setAppointmentList;
import static Util.mainDB.dbConnect;
import static Util.mainDB.getConn;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dane Schlea
 */
public class AppointmentsController implements Initializable {
    
    //UI items
    @FXML
    private TableView<Appointment> appView;
    @FXML
    private TableColumn<Appointment, String> appStartView;
    @FXML
    private TableColumn<Appointment, String> appEndView;
    @FXML
    private TableColumn<Appointment, String> appTitleView;
    @FXML
    private TableColumn<Appointment, String> appDescView;
    @FXML
    private TableColumn<Appointment, String> appLocView;
    @FXML
    private TableColumn<Appointment, String> appCustView;
    @FXML
    private TableColumn<Appointment, String> appConsView;
    @FXML
    private RadioButton appMonthly;
    @FXML
    private RadioButton appWeekly;
    @FXML
    private Button appClose;
    @FXML
    private Button appAdd;
    @FXML
    private Button appUpdate;
    @FXML
    private Button appDelete;
    @FXML
    private Label labelAppointments;
    
    //index of selected items on appt window
    private static int updateApptIndex;
    //datetime formatters
    private final ZoneId zID = ZoneId.systemDefault();
    private final DateTimeFormatter dateTF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    
    //getter for selected appt index
    public static int getUpdateApptIndex(){
        return updateApptIndex;
    }
    private ObservableList<Appointment> appointmentList;
    
    //cancel out of window
    @FXML
    private void handleAppointmentsCancel(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm cancellation");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            Parent appointmentsCancel = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(appointmentsCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            System.out.println("Operation cancelled.");
        }
    }
    
    //open the add appointment window
    @FXML
    private void addAppointmentHandler(ActionEvent event) throws IOException {
        Parent addAppointmentParent = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        Scene addAppointmentScene = new Scene(addAppointmentParent);
        Stage addAppointmentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addAppointmentStage.setScene(addAppointmentScene);
        addAppointmentStage.show();
    }
    
    //open the update appointment window with the selected data
    @FXML
    private void updateAppointmentHandler(ActionEvent event) throws IOException{
        Appointment updateAppt = appView.getSelectionModel().getSelectedItem();
        if(updateAppt == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection required");
            alert.setContentText("You must select an appointment to update");
            alert.showAndWait();
            return; 
        }
        updateApptIndex = getAppointmentList().indexOf(updateAppt);
        
        try{
            Parent updateCustParent = FXMLLoader.load(getClass().getResource("UpdateAppointment.fxml"));
            Scene updateCustScene = new Scene(updateCustParent);
            Stage updateCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            updateCustStage.setScene(updateCustScene);
            updateCustStage.show();
        }
        catch (IOException x){
            x.printStackTrace();
        }
    }
    
    //delete appointment button handler
    @FXML
    private void deleteAppointmentHandler(ActionEvent event) throws IOException {
        Appointment apptSelected = appView.getSelectionModel().getSelectedItem();
        if(apptSelected != null){    
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Appointment delete");
            alert.setHeaderText("Confirm delete?");
            alert.setContentText("Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                deleteAppointment(apptSelected);
                appView.getItems().setAll(populateAppointments());
            }
        }
    }
    
    //weekly radio button handler - only display appts within a week upcoming
    //lambda used for setting of table to filtered week value
    @FXML
    void weeklyRadio(ActionEvent event){
        appMonthly.setSelected(false);
        
        LocalDate today = LocalDate.now();
        LocalDate aWeek = today.plusDays(7);
        FilteredList<Appointment> apptFilt = new FilteredList<>(appointmentList);
        apptFilt.setPredicate(row ->{
          LocalDate thisRow = LocalDate.parse(row.getStart(), dateTF);
          return thisRow.isAfter(today.minusDays(1)) && thisRow.isBefore(aWeek);
        });
        appView.setItems(apptFilt);
    }
    
    //montly radio button handler - only display appts within a month upcoming
    //lambda used for setting of table to filtered month value
    @FXML
    void monthlyRadio(ActionEvent event){
        appWeekly.setSelected(false);
        
        LocalDate today = LocalDate.now();
        LocalDate aMonth = today.plusMonths(1);
        FilteredList<Appointment> apptFilt = new FilteredList<>(appointmentList);
        apptFilt.setPredicate(row ->{
            LocalDate thisRow = LocalDate.parse(row.getStart(), dateTF);
            return thisRow.isAfter(today.minusDays(1)) && thisRow.isBefore(aMonth);
        });
        appView.setItems(apptFilt);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * 
     * Used lambdas to simplify setting of cell values in the table view for function and readability
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appStartView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart()));
        appEndView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnd()));
        appTitleView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        appDescView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        appLocView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        appCustView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer()));
        appConsView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser()));
        
        //populate tableview
        appView.getItems().setAll(populateAppointments());
    }    

    //method to delete an appointment matching customerId to customerName
    private void deleteAppointment(Appointment apptSelected) {
        try{
            dbConnect();
            PreparedStatement appt = getConn().prepareStatement("SELECT appointment.appointmentId, appointment.customerId, appointment.description, customer.customerId, customer.customerName "
                    + "FROM appointment, customer WHERE appointment.customerId = customer.customerId AND customer.customerName = ? AND appointment.description = ?");
                appt.setString(1, apptSelected.getCustomer());
                appt.setString(2, apptSelected.getDescription());
                ResultSet resAppt = appt.executeQuery();
                while(resAppt.next()){
                    int rApp = (int) resAppt.getObject("appointmentId");
            
                PreparedStatement prepDel = getConn().prepareStatement("DELETE appointment.* FROM appointment WHERE appointment.appointmentId = ?");
                prepDel.setInt(1, rApp);
                prepDel.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("SQL issue with deletion");
        }
    }

    //populate appView tableview with appointments
    protected List<Appointment> populateAppointments() {
        
        //build the appt list
        appointmentList = FXCollections.observableArrayList();
        //connect to db and pull appt info
        try{
            dbConnect();
            PreparedStatement appStat = getConn().prepareStatement("SELECT customer.customerId, appointment.start, appointment.end, appointment.location, appointment.contact, "
                    + "appointment.title, appointment.description, appointment.createdBy, appointment.customerId FROM appointment, customer "
                    + "WHERE appointment.customerId = customer.customerId "
                    + "ORDER BY appointment.start");
            
            ResultSet appRS = appStat.executeQuery();
            while(appRS.next()){
                //set timestamps to local time
                Timestamp apptStart = appRS.getTimestamp("appointment.start");
                ZonedDateTime zIDStart = apptStart.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime apptLocalStart = zIDStart.withZoneSameInstant(zID);

                Timestamp apptEnd = appRS.getTimestamp("appointment.end");
                ZonedDateTime zIDEnd = apptEnd.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime apptLocalEnd = zIDEnd.withZoneSameInstant(zID);
                
                String aLocation = appRS.getString("appointment.location");
                String aCustomer = appRS.getString("appointment.contact");
                String aTitle = appRS.getString("appointment.title");
                String aDescription = appRS.getString("appointment.description");
                String aUser = appRS.getString("appointment.createdBy");
                
                appointmentList.add(new Appointment(apptLocalStart.format(dateTF), apptLocalEnd.format(dateTF), aTitle, aDescription, aLocation, aCustomer, aUser));
                setAppointmentList(appointmentList);
            }
            
        } catch (SQLException se) {
            System.out.println("SQL error");
        }
        return appointmentList;
    }
    
}
