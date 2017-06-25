/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.scenes.Scenes;
import com.tiem625.tankpartsshop.scenes.ShopScene;
import java.io.IOException;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;


/**
 *
 * @author Anatolij
 */
public class ResultJSONDialogController {
    
    @FXML
    private TextArea textArea;
    
    private Map<String, Object> json;
    
    private ShopScene cmdScene;
    private Stage cmdStage;
    
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
        
        if (cmdStage == null) {
            cmdScene = Scenes.SCENE_CMD_DIALOG();
            cmdStage = Scenes.initUtilityStage(cmdScene);
        }
        
        ((ResultCmdDialogController)cmdScene.getController()).setJson(json);
        cmdStage.showAndWait();
        
    }
    
    
}
