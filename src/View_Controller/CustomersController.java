/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Models.Customer;
import static Models.Customer.getCustomerList;
import static Models.Customer.setCustomerList;
import static Util.mainDB.dbConnect;
import static Util.mainDB.getConn;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button custClose;
    @FXML
    private Label labelCustomers;
    @FXML
    private Button custAdd;
    @FXML
    private Button custUpdate;
    @FXML
    private Button custDelete;
    
    //index of customer
    private static int updateCustomerIndex;
    
    //Customer index getter
    public static int getUpdateCustomerIndex(){
        return updateCustomerIndex;
    }
    
    //cancel from window to main menu
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
    //add customer
    @FXML
    private void addCustomerHandler(ActionEvent event) throws IOException {
        Parent addCustomerParent = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene addCustomerScene = new Scene(addCustomerParent);
        Stage addCustomerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addCustomerStage.setScene(addCustomerScene);
        addCustomerStage.show();
    }
    
    //update customer
    @FXML
    private void updateCustomerHandler(ActionEvent event) throws IOException{
        Customer updateCustomer = custView.getSelectionModel().getSelectedItem();
        if(updateCustomer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection required");
            alert.setContentText("You must select a customer to update");
            alert.showAndWait();
            return; 
        }
        updateCustomerIndex = getCustomerList().indexOf(updateCustomer);
        
        try{
            Parent updateCustParent = FXMLLoader.load(getClass().getResource("UpdateCustomer.fxml"));
            Scene updateCustScene = new Scene(updateCustParent);
            Stage updateCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            updateCustStage.setScene(updateCustScene);
            updateCustStage.show();
        }
        catch (IOException x){
            x.printStackTrace();
        }
    }
    
    //delete customer
    @FXML
    private void deleteCustomerHandler(ActionEvent event) throws IOException {
        Customer customerSelected = custView.getSelectionModel().getSelectedItem();
        if(customerSelected != null){    
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Customer delete");
            alert.setHeaderText("Confirm delete?");
            alert.setContentText("Are you sure you want to delete this customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                deleteCustomer(customerSelected);
                custView.getItems().setAll(populateCustomers());
            }
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * Used lambdas to simplify setting of cell values
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        custNameView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        custPhoneView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        custAddView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        custAddTwoView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddressTwo()));
        custCityView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        custPostalView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostal()));
        custCountryView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCountry()));
        
        custView.getItems().setAll(populateCustomers());
    }    
    
    //Populate the TableView with customers information
    protected List<Customer> populateCustomers() {
        String pCustName;
        String pPhone;
        String pAddress;
        String pAddressTwo;
        String pCity;
        String pPostal;
        String pCountry;
        //build the customer list
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        //connect to the db and pull customers info where it matches
        try{
            dbConnect();
            PreparedStatement prepState = getConn().prepareStatement(
                    "SELECT customer.customerName, address.phone, address.address, address.address2, city.cityId, city.city, address.postalCode, country.country " +
                    "FROM customer, address, city, country " +
                    "WHERE customer.addressId = address.addressId AND address.cityId = city.cityId AND city.countryId = country.countryId " +
                    "ORDER BY customer.customerName");
            
            ResultSet resSet = prepState.executeQuery();
            while(resSet.next()){
                pCustName = resSet.getString("customer.customerName");
                pPhone = resSet.getString("address.phone");
                pAddress = resSet.getString("address.address");
                pAddressTwo = resSet.getString("address.address2");
                pCity = resSet.getString("city.city");
                pPostal = resSet.getString("address.postalCode");
                pCountry = resSet.getString("country.country");
                
                customerList.add(new Customer(pCustName, pPhone, pAddress, pAddressTwo, pCity, pPostal, pCountry));
                setCustomerList(customerList);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error");
        } catch (Exception e){
            System.out.println("Non-sql error");
        }
        return customerList;
    }

    private void deleteCustomer(Customer customerSelected) {
        try{
            dbConnect();
            PreparedStatement cust = getConn().prepareStatement("SELECT customerId FROM customer WHERE customerName = ?");
                cust.setString(1, customerSelected.getCustomerName());
                ResultSet resCust = cust.executeQuery();
                while(resCust.next()){
                    int rCust = (int) resCust.getObject("customerId");
            
                PreparedStatement prepDel = getConn().prepareStatement("DELETE customer.*, address.* FROM customer, address WHERE customer.customerId = ? AND customer.addressId = address.addressId");
                prepDel.setInt(1, rCust);
                prepDel.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("SQL issue with deletion");
        }
    }
    
}
