/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dane Schlea
 */
public class MainMenuController implements Initializable {
    
    //UI items
    @FXML
    private Button menuExit;
    @FXML
    private Button menuAppointments;
    @FXML
    private Button menuReports;
    @FXML
    private Button menuCustomers;
    @FXML
    private Label labelMainMenu;
    
    //Button handlers
    @FXML
    void handleMenuExit(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Exit");
        alert.setHeaderText("Confirm exit.");
        alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .ifPresent(response -> System.exit(0));
    }
    
    @FXML
    void handleAppointmentsButton(ActionEvent event) throws IOException{
        Parent appointmentsParent = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
        Scene appointmentsScene = new Scene(appointmentsParent);
        Stage appointmentsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsStage.setScene(appointmentsScene);
        appointmentsStage.show();
    }
    
    @FXML
    void handleCustomersButton(ActionEvent event) throws IOException{
        Parent customersParent = FXMLLoader.load(getClass().getResource("Customers.fxml"));
        Scene customersScene = new Scene(customersParent);
        Stage customersStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customersStage.setScene(customersScene);
        customersStage.show();
    }
    
    @FXML
    void handleReportsButton(ActionEvent event) throws IOException{
        Parent reportsParent = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        Scene reportsScene = new Scene(reportsParent);
        Stage reportsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        reportsStage.setScene(reportsScene);
        reportsStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
