/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Customer;
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
import javafx.scene.control.Label;
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
public class CustomersController implements Initializable {
    
    //UI items
    @FXML
    private TableView<Customer> custView;
    @FXML
    private TableColumn<Customer, Integer> custIDView;
    @FXML
    private TableColumn<Customer, String> custNameView;
    @FXML
    private TableColumn<Customer, String> custPhoneView;
    @FXML
    private TableColumn<Customer, String> custAddView;
    @FXML
    private TableColumn<Customer, String> custAddTwoView;
    @FXML
    private TableColumn<Customer, String> custCityView;
    @FXML
    private TableColumn<Customer, String> custPostalView;
    @FXML
    private TableColumn<Customer, String> custCountryView;
    @FXML
    private TextField custSearchField;
    @FXML
    private Button custSearch;
    @FXML
    private Button custClose;
    @FXML
    private Label labelCustomers;
    @FXML
    private Button custAdd;
    @FXML
    private Button custUpdate;
    @FXML
    private Button custDelete;
    
    @FXML
    private void handleCustomersCancel(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm cancellation");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            Parent customersCancel = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(customersCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            System.out.println("Operation cancelled.");
        }
    }
    
    @FXML
    private void addCustomerHandler(ActionEvent event) throws IOException {
        Parent addCustomerParent = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
        Scene addCustomerScene = new Scene(addCustomerParent);
        Stage addCustomerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addCustomerStage.setScene(addCustomerScene);
        addCustomerStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
