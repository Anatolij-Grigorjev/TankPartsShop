/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.utils.DialogUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 *
 * @author Tiem625
 */
public class SpritesheetMetaDialogController {
    
    @FXML
    private TitledPane spriteMetaPane;
   
    @FXML
    private DecimalInputField width, height, rows, cols, paddingX, paddingY;
    
    private Map<String, Integer> meta;
    
    
    public String getSpriteName() {
        return spriteMetaPane.getText();
    }
    
    public void setSpriteName(String name) {
        spriteMetaPane.setText(name);
    }
    
    public Map<String, Integer> getMeta() {
        return meta;
    }
    
    @FXML
    private void handleCancel() {
        meta = new HashMap<>();
        close();
    }
    
    private void close() {
        ((Stage)spriteMetaPane.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleOK() {
        if (Arrays.asList(width, height, rows, cols)
                .stream().anyMatch(field -> field.getValue().intValue() == 0)) {
                DialogUtils.formHasBlanks();
                return ;
        } 
        
        meta = new HashMap<>();
        meta.put("width", width.getValue().intValue());
        meta.put("height", height.getValue().intValue());
        meta.put("rows", rows.getValue().intValue());
        meta.put("cols", cols.getValue().intValue());
        meta.put("paddingX", paddingX.getValue().intValue());
        meta.put("paddingY", paddingY.getValue().intValue());
        
        close();
    }
    
}
