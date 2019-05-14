/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Appointment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableColumn<Appointment, String> appTypeView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
