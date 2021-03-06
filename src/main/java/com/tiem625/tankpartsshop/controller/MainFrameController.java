/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.Globals;
import com.tiem625.tankpartsshop.scenes.Scenes;
import com.tiem625.tankpartsshop.scenes.ShopScene;
import com.tiem625.tankpartsshop.utils.DialogUtils;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Tiem625
 */
public class MainFrameController implements Initializable {

    @FXML
    TextField projectDirField;

    DirectoryChooser homeDirChooser;
    
    Stage newChassisModal;
    Stage newTracksModal;
    Stage newEngineModal;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeDirChooser = new DirectoryChooser();
        homeDirChooser.setTitle("Choose project home...");

        projectDirField.setText(null);
    }

    @FXML
    private void handleNewChassisButton(Event source) {

        instantiateShopSceneStage(newChassisModal, Scenes::SCENE_NEW_CHASSIS);
    }
    
    @FXML
    private void handleNewTracksButton(Event source) {
        
        instantiateShopSceneStage(newTracksModal, Scenes::SCENE_NEW_TRACKS);
    }
    
    @FXML
    private void handleNewEngineButton(Event souce) {
        
        instantiateShopSceneStage(newEngineModal, Scenes::SCENE_NEW_ENGINE);
    }
    
    
    private void instantiateShopSceneStage(
            Stage modalStage, 
            Supplier<ShopScene> sceneFunc) {
        if (assertRootDir()) {
            if (modalStage == null) {
                modalStage = Scenes.initUtilityStage(sceneFunc.get());
            }
            modalStage.showAndWait();
        }
    }
    
    private boolean assertRootDir() {
        if (Globals.PROJECT_ROOT_DIR == null) {
            DialogUtils.projectRootNotSet(projectDirField.getScene().getWindow())
                    .showAndWait();
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void handleSetProjectHome() {
        File homeDir = homeDirChooser.showDialog(projectDirField.getScene().getWindow());
        projectDirField.setText(homeDir != null ? homeDir.getAbsolutePath() : null);
        Globals.PROJECT_ROOT_DIR = projectDirField.getText();
    }

}
