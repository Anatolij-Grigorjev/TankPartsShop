/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.components.PickFileControl;
import com.tiem625.tankpartsshop.components.ReadOnlyWindowedControl;
import com.tiem625.tankpartsshop.utils.DialogUtils;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author Tiem625
 */
public class NewChassisController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TextField idField,
            nameField;

    @FXML
    private PickFileControl pickSpriteSheet,
            pickShopImage,
            pickGarageImage;

    @FXML
    private DecimalInputField mass,
            integrity,
            regeneration,
            regenDelay,
            activeSprites,
            price;
    
    @FXML
    private ReadOnlyWindowedControl rowcTankPosition,
            rowcTurretPivot,
            rowcHealthbarOffset,
            rowcBoxCollider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextInputDialog nameDialog = DialogUtils.newPartNameDialog("Chassis");
        String name = nameDialog.showAndWait().orElse("default");
        
        idField.setText("CH_" + name.toLowerCase() + "01");
        nameField.setText(StringUtils.capitalize(name.toLowerCase()));
        
        
    }
    
    @FXML
    private void handleCancel() {
        ((Stage) idField.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleMakeJSONButton() {
        
        boolean hasBlanks = rootPane.getChildren().stream().map(node -> {
            if (node instanceof TextField) {
                return ((TextField) node).getText();
            } else if (node instanceof ReadOnlyWindowedControl) {
                return ((ReadOnlyWindowedControl) node).getFieldValue();
            } 
            return null;
        }).anyMatch(StringUtils::isBlank);
        if (hasBlanks) {
            DialogUtils.formHasBlanks().showAndWait();
        } else {
            
           
           
            
        }
       
    }
    
    

}
