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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shakla
 */
public class ManageAppointmentController implements Initializable {
    
    //UI items
    @FXML
    private Label labelManApp;
    @FXML
    private Button mAppSave;
    @FXML
    private Button mAppCancel;
    @FXML
    private Label labelAppName;
    @FXML
    private Label labelAppTitle;
    @FXML
    private Label labelAppDesc;
    @FXML
    private Label labelAppLoc;
    @FXML
    private Label labelAppCon;
    @FXML
    private Label labelAppUrl;
    @FXML
    private Label labelAppStart;
    @FXML
    private Label labelAppEnd;
    @FXML
    private TextField mAppNameField;
    @FXML
    private TextField mAppTitleField;
    @FXML
    private TextArea mAppDescField;
    @FXML
    private TextField mAppLocField;
    @FXML
    private TextField mAppConField;
    @FXML
    private TextField mAppURLField;
    @FXML
    private DatePicker mAppDateStart;
    @FXML
    private DatePicker mAppDateEnd;
    @FXML
    private ComboBox<String> mAppStart;
    @FXML
    private ComboBox<String> mAppEnd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
