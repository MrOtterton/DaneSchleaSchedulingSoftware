/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Address;
import Models.City;
import Models.Country;
import Models.Customer;
import static Util.mainDB.dbConnect;
import static Util.mainDB.getConn;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dane Schlea
 */
public class UpdateCustomerController implements Initializable {
    
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
    
    @FXML
    private void handleUpdateCustomerCancel(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm cancellation");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            Parent updateCustomerCancel = FXMLLoader.load(getClass().getResource("Customers.fxml"));
            Scene scene = new Scene(updateCustomerCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            System.out.println("Operation cancelled.");
        }
    }
    
    //save customer to the database
    @FXML
    private void handleUpdateCustomerSave(ActionEvent event) throws IOException{
        String name = mCustNameField.getText();
        String addr = mCustAddressField.getText();
        String addr2 = mCustAddressContField.getText();
        String cityName = mCustCityField.getText();
        String postal = mCustPostalField.getText();
        String country = mCustCountryField.getText();
        String phone = mCustPhoneField.getText();       
        if((Customer.customerValidate(name) || Address.addressValidate(addr, postal, phone) || City.cityValidate(cityName) || Country.countryValidate(country)) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error submitting customer");
            alert.setContentText("One or more invalid fields.");
            alert.showAndWait();
        }
        else{
            try{
                //get cityId and apply it to rc
                String rc = "";
                dbConnect();
                PreparedStatement prepCity = getConn().prepareStatement("SELECT cityId AS '" + rc + "' FROM city WHERE city = ?");
                prepCity.setString(1, cityName);
                ResultSet resCity = prepCity.executeQuery();
                if(resCity.next()){
                    rc = rc;
                }
                System.out.println("Pass 1");
                //insert data into address first to get new address key
                PreparedStatement prepS = getConn().prepareStatement("UPDATE address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)"
                                + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)", Statement.RETURN_GENERATED_KEYS);
                prepS.setString(1, addr);
                prepS.setString(2, addr2);
                prepS.setString(3, rc);
                prepS.setString(4, postal);
                prepS.setString(5, phone);
                prepS.setString(6, LoginController.currentUser);
                prepS.setString(7, LoginController.currentUser);
                System.out.println("Pass 2");
                //get address key
                boolean addRes = prepS.execute();
                System.out.println("Pass 2.1");
                int addID = -1;
                ResultSet rsAdd = prepS.getGeneratedKeys();
                if(rsAdd.next()){
                    addID = rsAdd.getInt(1);
                }
                System.out.println("Pass 3");
                //insert data into customer
                PreparedStatement pState = getConn().prepareStatement("INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy "
                        + "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
                pState.setString(1, name);
                pState.setString(2, rc);
                pState.setInt(3, 1);
                pState.setString(4, LoginController.currentUser);
                pState.setString(5, LoginController.currentUser);
                int result = pState.executeUpdate();
                System.out.println("Pass 4");
            } catch (SQLException e) {
                System.out.println("SQL error");
            }
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
