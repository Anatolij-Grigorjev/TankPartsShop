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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tiem625
 */
public class MainFrameController implements Initializable {

    @FXML
    Button btnNewChassis;

    @FXML
    TextField projectDirField;

    DirectoryChooser homeDirChooser;
    Stage newChassisModal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeDirChooser = new DirectoryChooser();
        homeDirChooser.setTitle("Choose project home...");

        projectDirField.setText(null);
    }

    @FXML
    private void handleNewChassisButton(Event source) {

        if (Globals.PROJECT_ROOT_DIR == null) {
            DialogUtils.projectRootNotSet().showAndWait();
        } else {

            if (newChassisModal == null) {
                newChassisModal = Scenes.initUtilityStage(Scenes.SCENE_NEW_CHASSIS());
            }
            newChassisModal.showAndWait();
        }
    }

    @FXML
    private void handleSetProjectHome() {
        File homeDir = homeDirChooser.showDialog(projectDirField.getScene().getWindow());
        projectDirField.setText(homeDir != null ? homeDir.getAbsolutePath() : null);
        Globals.PROJECT_ROOT_DIR = projectDirField.getText();
    }

}
