/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Anatolij
 */
public class ReadOnlyWindowedControl extends CustomVBoxControl {
    
    @FXML
    private Label topLabel;
    @FXML
    private Label fieldLabel;
    @FXML
    private Label valueLabel;

    public ReadOnlyWindowedControl() {
        super("/fxml/components/ReadOnlyWindowedControl.fxml");
    }
    
    
    public void setTopText(String topText) {
        topLabel.setText(topText);
    }
    
    public void setFieldText(String fieldText) {
        fieldLabel.setText(fieldText);
    }
    
    public void setFieldValue(String fieldValue) {
        valueLabel.setText(fieldValue);
    }
    
    @FXML
    private void handleNeedEditWindow() {
        
    }
    
}
