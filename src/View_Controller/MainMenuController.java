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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
