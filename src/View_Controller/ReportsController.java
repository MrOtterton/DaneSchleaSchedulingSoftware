/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Appointment;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
