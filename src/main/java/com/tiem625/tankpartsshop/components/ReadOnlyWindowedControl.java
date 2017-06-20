/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.components;

import com.tiem625.tankpartsshop.controller.edit.EditTransformController;
import com.tiem625.tankpartsshop.scenes.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
   
    Stage editValueStage;

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
    
    public String getTopText() {
        return topLabel.getText();
    }
    
    public String getFieldText() {
        return fieldLabel.getText();
    }
    
    public String getFieldValue() {
        return valueLabel.getText();
    }
    
    @FXML
    private void handleNeedEditWindow() {
        if (editValueStage == null) {
            editValueStage = Scenes.initUtilityStage(Scenes.SCENE_EDIT_TRANSFORM);
        }
        
        editValueStage.showAndWait();
        EditTransformController controller = 
                (EditTransformController) Scenes.SCENE_EDIT_TRANSFORM.getController();
        setFieldValue(controller.getContentWriteable().getContentString());
    }
    
}
