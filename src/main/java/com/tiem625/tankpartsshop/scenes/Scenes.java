/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.scenes;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tiem625
 */
public class Scenes {
    
    public static Map<WindowType, ShopScene> topWindows = new HashMap<>();

    private Scenes() {
    }

    public static ShopScene SCENE_MAIN_WINDOW() {
        try {
            return new ShopScene(WindowType.MAIN_WINDOW, "/fxml/Scene.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_NEW_CHASSIS() {
        try {
            return new ShopScene(WindowType.NEW_CHASSIS, "/fxml/NewChassis.fxml", Arrays.asList("/styles/NewChassis.css"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_EDIT_TRANSFORM() {
        try {
            return new ShopScene(WindowType.EDIT_TRANSFORM, "/fxml/EditTransform.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_EDIT_VECTOR3() {
        try {
            return new ShopScene(WindowType.EDIT_VECTOR3, "/fxml/EditVector3.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ShopScene SCENE_EDIT_BOX() {
        try {
            return new ShopScene(WindowType.EDIT_BOX, "/fxml/EditBox.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static ShopScene SCENE_RESULTS_DIALOG() {
        try {
            return new ShopScene(WindowType.RESULTS_DIALOG, "/fxml/ResultJSONDialog.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static ShopScene SCENE_CMD_DIALOG() {
        try {
            return new ShopScene(WindowType.CMD_DIALOG, "/fxml/ResultCmdDialog.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static ShopScene SCENE_SPRITE_META() {
        try {
            return new ShopScene(WindowType.SPRITE_META, "/fxml/SpritesheetMetaDialog.fxml");
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
