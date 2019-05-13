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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TabPane mainReportsTab;
    @FXML
    private Tab scheduleTab;
    @FXML
    private TableView<Appointment> reportView;
    @FXML
    private TableColumn<Appointment, String> reportStartView;
    @FXML
    private TableColumn<Appointment, String> reportEndView;
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
    private ChoiceBox<?> typesMonth;
    @FXML
    private ChoiceBox<?> typesType;
    @FXML
    private Button typesSubmit;
    @FXML
    private Label labelTypesNumber;
    @FXML
    private TextField typesNumberField;
    @FXML
    private Tab locationTab;
    @FXML
    private ChoiceBox<?> locationLoc;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
