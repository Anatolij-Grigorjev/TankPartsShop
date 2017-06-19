/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.components;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * @author Tiem625
 */
public class PickFileControl extends CustomVBoxControl {
    
    @FXML
    private Label fieldLabel;
    
    @FXML
    private TextField inputField;
    
    private final FileChooser fileChooser;
    
    
    public PickFileControl() {
        super("/fxml/components/PickFileControl.fxml");
        fileChooser = new FileChooser();
    }
    
    public String getFieldName() {
        return fieldLabel.getText();
    }
    
    public void setFieldName(String value) {
        fieldLabel.setText(value);
    }
    
    @FXML
    private void handleChoosingFile() {
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        if (file != null) {
            inputField.setText(file.getAbsolutePath());
        }
    }
}
