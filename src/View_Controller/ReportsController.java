/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Appointment;
import static Util.mainDB.dbConnect;
import static Util.mainDB.getConn;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dane Schlea
 */
public class ReportsController implements Initializable {
    
    //UI items
    @FXML
    private Button reportsClose;
    @FXML
    private Button scheduleBoxButton;
    @FXML
    private TabPane mainReportsTab;
    @FXML
    private Tab scheduleTab;
    @FXML
    private ChoiceBox<String> scheduleBox;
    @FXML
    private TableView<Appointment> reportView;
    @FXML
    private TableColumn<Appointment, ZonedDateTime> reportStartView;
    @FXML
    private TableColumn<Appointment, ZonedDateTime> reportEndView;
    @FXML
    private TableColumn<Appointment, String> reportLocView;
    @FXML
    private TableColumn<Appointment, String> reportCustView;
    @FXML
    private TableColumn<Appointment, String> reportPhoneView;
    @FXML
    private Tab typesTab;
    @FXML
    private Label labelTypesMonth;
    @FXML
    private Label labelTypesType;
    @FXML
    private ChoiceBox<String> typesMonth;
    @FXML
    private ChoiceBox<String> typesType;
    @FXML
    private Button typesSubmit;
    @FXML
    private Label labelTypesNumber;
    @FXML
    private TextField typesNumberField;
    @FXML
    private Tab locationTab;
    @FXML
    private ChoiceBox<String> locationLoc;
    @FXML
    private Label labelLocationLoc;
    @FXML
    private Button locationSubmit;
    @FXML
    private Label labelLocationNumb;
    @FXML
    private TextField locationNumbField;
    @FXML
    private Label labelReports;
    
    private ObservableList<Appointment> userSchedule;
    private final ZoneId zID = ZoneId.systemDefault();
    private final DateTimeFormatter dateTF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    String thisUser;
    Integer numbMonth;
    String numbType;
    String numbLoc;
    
    @FXML
    private void handleReportsCancel(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm cancellation");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            Parent reportsCancel = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(reportsCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            System.out.println("Operation cancelled.");
        }
    }
    
    //number of appointment types by month button handler
    public void typeHandler() throws IOException {
        numbMonth = Integer.parseInt(typesMonth.getValue());
        numbType = typesType.getValue();
        
        monthTypeResult();
    }
    
    public void locationHandler() throws IOException{
        numbLoc = locationLoc.getValue();
        
        locationResult();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //scheduleBox items - choicebox 1st tab
        scheduleBox.getItems().add("test");
        scheduleBox.getItems().add("test2");
        scheduleBox.setValue("test");
        
        //typesMonth items - choicebox 2nd tab
        typesMonth.getItems().add("01");
        typesMonth.getItems().add("02");
        typesMonth.getItems().add("03");
        typesMonth.getItems().add("04");
        typesMonth.getItems().add("05");
        typesMonth.getItems().add("06");
        typesMonth.getItems().add("07");
        typesMonth.getItems().add("08");
        typesMonth.getItems().add("09");
        typesMonth.getItems().add("10");
        typesMonth.getItems().add("11");
        typesMonth.getItems().add("12");
        typesMonth.setValue("01");
        
        //typesType items - choicebox 2nd tab
        typesType.getItems().add("Initial Consultation");
        typesType.getItems().add("Consultation");
        typesType.getItems().add("Final Consultation");
        typesType.setValue("Initial Consultation");
        
        //locationLoc items - choicebox 3rd tab
        locationLoc.getItems().add("New York");
        locationLoc.getItems().add("Los Angeles");
        locationLoc.getItems().add("Houston");
        locationLoc.getItems().add("Salt Lake City");
        locationLoc.getItems().add("Lancaster");
        locationLoc.getItems().add("London");
        locationLoc.getItems().add("Glasgow");
        locationLoc.getItems().add("Toronto");
        locationLoc.getItems().add("Vancouver");
        locationLoc.getItems().add("Ottawa");
        locationLoc.getItems().add("Oslo");
        locationLoc.getItems().add("Bergen");
        locationLoc.getItems().add("Trondheim");
        locationLoc.setValue("New York");
    }    
    
    //retrieve the specified user schedule
    @FXML
    public void scheduleBoxSelect() throws IOException{
        thisUser = scheduleBox.getValue();

        populateUserSchedule();
        reportStartView.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportEndView.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportLocView.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportCustView.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reportPhoneView.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
    }
    
    //method to populate the appointments for the selected user
    private void populateUserSchedule() {
        userSchedule = FXCollections.observableArrayList();
        
        try{
        dbConnect();
        PreparedStatement userST = getConn().prepareStatement("SELECT appointment.appointmentId, appointment.customerId, appointment.start, appointment.end, appointment.createdBy, "
                + "appointment.location, customer.customerId, customer.customerName, customer.addressId, address.phone, address.addressId "
                + "FROM appointment, customer, address "
                + "WHERE appointment.customerId = customer.customerId AND customer.addressId = address.addressId AND appointment.start >= CURRENT_DATE AND appointment.createdBy = ?");
            userST.setString(1, thisUser);
            ResultSet us = userST.executeQuery();
           
            
            while (us.next()) {
                //set timestamps to local time
                Timestamp userStart = us.getTimestamp("appointment.start");
                ZonedDateTime zIDStart = userStart.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime userLocalStart = zIDStart.withZoneSameInstant(zID);

                Timestamp userEnd = us.getTimestamp("appointment.end");
                ZonedDateTime zIDEnd = userEnd.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime userLocalEnd = zIDEnd.withZoneSameInstant(zID);

                String userLoc = us.getString("appointment.location");
                String custName = us.getString("customer.customerName");
                String custPhone = us.getString("address.phone");
                      
                userSchedule.add(new Appointment(userLocalStart.format(dateTF), userLocalEnd.format(dateTF), userLoc, custName, custPhone));
                

            }
            
        } catch (SQLException se) {
            System.out.println("SQL error");
        } catch (Exception e) {
            System.out.println("Non-SQL error");
        }
        reportView.getItems().setAll(userSchedule);
    }

    //calculate amount of appts per month/type combo
    private void monthTypeResult() {
        dbConnect();
        try{
            PreparedStatement mStat = getConn().prepareStatement("SELECT * "
                + "FROM appointment WHERE appointment.title = ? AND MONTH(appointment.start) = ?");
            mStat.setString(1, numbType);
            mStat.setInt(2, numbMonth);
            ResultSet mType = mStat.executeQuery();
            
            int typeCount = 0;
            while(mType.next()){
                typeCount++;
            }
            typesNumberField.setText(Integer.toString(typeCount));
            
        } catch (SQLException se) {
            System.out.println("SQL error");
        }
    }

    //calculate total amount of appts at a single location
    private void locationResult() {
       dbConnect();
       try{
           PreparedStatement locStat = getConn().prepareStatement("SELECT * "
               + "FROM appointment WHERE appointment.location = ?");
           locStat.setString(1, numbLoc);
           ResultSet locSet = locStat.executeQuery();
           
           int locCount = 0;
           while(locSet.next()){
               locCount++;
           }
           locationNumbField.setText(Integer.toString(locCount));
           
       } catch (SQLException se) {
           System.out.println("SQL error");
        }
    }
}
