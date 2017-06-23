/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author Anatolij
 */
public class ResultJSONDialogController {
    
    @FXML
    private TextArea textArea;
    
    
    public void setText(String text) {
        textArea.setText(text);
    }
    
    
    @FXML 
    private void handleCancel() {
        ((Stage)textArea.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleCookCmd() {
        
    }
    
    
}
