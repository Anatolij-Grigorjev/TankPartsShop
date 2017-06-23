/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.scenes;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tiem625
 */
public class Scenes {

    private Scenes() {
    }

    public static ShopScene SCENE_MAIN_WINDOW() {
        try {
            return new ShopScene("/fxml/Scene.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_NEW_CHASSIS() {
        try {
            return new ShopScene("/fxml/NewChassis.fxml", Arrays.asList("/styles/NewChassis.css"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_EDIT_TRANSFORM() {
        try {
            return new ShopScene("/fxml/EditTransform.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_EDIT_VECTOR3() {
        try {
            return new ShopScene("/fxml/EditVector3.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_EDIT_BOX() {
        try {
            return new ShopScene("/fxml/EditBox.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static ShopScene SCENE_RESULTS_DIALOG() {
        try {
            return new ShopScene("/fxml/ResultJSONDialog.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Stage initStage(ShopScene scene, Consumer<Stage> decorator) {
        Stage stage = new Stage();
        stage.setScene(scene.getScene());
        decorator.accept(stage);

        return stage;
    }

    public static Stage initUtilityStage(ShopScene scene) {
        return initUtilityStage(scene, null);
    }

    public static Stage initUtilityStage(ShopScene scene, Consumer<Stage> decorator) {
        Stage initStage = initStage(scene, stage -> {
            stage.initStyle(StageStyle.UTILITY);
        });
        if (decorator != null) {
            decorator.accept(initStage);
        }
        return initStage;
    }

}
