/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.scenes.Scenes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tiem625
 */
public class MainFrameController implements Initializable {
    
    @FXML
    Button btnNewChassis;
    Stage newChassisModal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void handleNewChassisButton(Event source) {
        if (newChassisModal == null) {
            newChassisModal = Scenes.initStage(Scenes.SCENE_NEW_CHASSIS, (Stage stage) -> {
                stage.initStyle(StageStyle.UTILITY);
            });
            
        }
        newChassisModal.showAndWait();
    }
    
}
