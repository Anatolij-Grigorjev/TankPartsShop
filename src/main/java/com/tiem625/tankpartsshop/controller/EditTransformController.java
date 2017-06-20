/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.model.ContentProvider;
import com.tiem625.tankpartsshop.model.ContentWriteable;
import com.tiem625.tankpartsshop.model.TransformState;
import java.util.Arrays;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Anatolij
 */
public class EditTransformController implements ContentProvider {
    
    
    private TransformState value;
    
    @FXML 
    private DecimalInputField posX, posY, posZ,
                              rotX, rotY, rotZ,
                              scaleX, scaleY, scaleZ;
    
    @FXML
    private AnchorPane rootPane;

    @Override
    public ContentWriteable getContentWriteable() {
        return value;
    }
    
    @FXML
    private void handleCancel() {
        value = TransformState.ZERO;
        closeWindow();
    }
    
    @FXML
    private void handleOK() {
        
        double[] floats = Stream.of(posX, posY, posZ, rotX, rotY, rotZ, scaleX, scaleY, scaleZ)
                .mapToDouble(dif -> dif.getValue().doubleValue()).toArray();
        
        value = new TransformState(floats);
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
