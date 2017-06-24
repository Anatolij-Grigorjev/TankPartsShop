/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import java.io.IOException;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.apache.commons.lang3.SystemUtils;
import org.codehaus.jackson.map.ObjectMapper;


/**
 *
 * @author Anatolij
 */
public class ResultJSONDialogController {
    
    @FXML
    private TextArea textArea;
    
    private final String CMD_PROGRAM = SystemUtils.IS_OS_MAC_OSX? 
            "/Applications/Unity.app/Contents/MacOS/Unity " :
            "\"C:\\Program Files\\Unity\\Unity.exe\"";
    
    private Map<String, Object> json;
    
    
    private void setText(String text) {
        textArea.setText(text);
    }
    
    public void setJSONMap(Map<String, Object> json) throws IOException {
        this.json = json;
        setText(new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(json));
    }
    
    
    @FXML 
    private void handleCancel() {
        ((Stage)textArea.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleCookCmd() {
        
        
        
    }
    
    
}
