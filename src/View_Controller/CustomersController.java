/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
