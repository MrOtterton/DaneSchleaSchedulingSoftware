/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dane Schlea
 */
public class ManageCustomerController implements Initializable {
    
    //UI items
    @FXML
    private Label labelManCust;
    @FXML
    private Label mCustName;
    @FXML
    private Label mCustAddress;
    @FXML
    private Label mCustCity;
    @FXML
    private Label mCustAddressCont;
    @FXML
    private Label mCustPostal;
    @FXML
    private Label mCustCountry;
    @FXML
    private Label mCustPhone;
    @FXML
    private TextField mCustNameField;
    @FXML
    private TextField mCustAddressField;
    @FXML
    private TextField mCustAddressContField;
    @FXML
    private TextField mCustCityField;
    @FXML
    private TextField mCustPostalField;
    @FXML
    private TextField mCustCountryField;
    @FXML
    private TextField mCustPhoneField;
    @FXML
    private Button mCustSave;
    @FXML
    private Button mCustCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
