/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.Globals;
import java.util.Arrays;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**ˆ
 *
 * @author Anatolij
 */
public class ResultCmdDialogController {
    
    @FXML
    private TextArea textArea;
    private String cookedCmd;
    
    
    private void setText(String text) {
        textArea.setText(text);
    }
    
    public void setJson(Map<String, Object> json) {
        
        StringBuilder cmdBuilder = new StringBuilder();
        //add known usual cmd lines
        Arrays.asList(
                Globals.CMD_EXECUTABLE, 
                "-batchmode", 
                "-quit", 
                "-projectPath " + Globals.PROJECT_ROOT_DIR,
                "-executeMethod " + Globals.CMD_IMPORTER_SCRIPT
        ).stream().forEach(line -> addCmdLine(cmdBuilder.append(line)));
        
        //-simpleassets key has a CSV list that represents simple asset keys
        //loop the json structure - if a key represents a string with 
        //a "special form" then that is a "simple asset" and need to have its
        //key registered here, while the asset itsef gets registered elsewhere
        
        
        
        cookedCmd = cmdBuilder.toString();
        setText(cookedCmd);
    }
    
    private StringBuilder addCmdLine(StringBuilder builder) {
        return builder
                .append(Globals.CMD_LINE_SEPARATOR)
                .append(System.lineSeparator());
    }
    
    
    @FXML 
    private void handleCancel() {
        ((Stage)textArea.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleExecuteCmd() {
        
        
        
    }
    
    
}
