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
    
    private Scenes() {}
    
    public static ShopScene SCENE_MAIN_WINDOW;
    public static ShopScene SCENE_NEW_CHASSIS;
    public static ShopScene SCENE_EDIT_TRANSFORM;
    public static ShopScene SCENE_EDIT_VECTOR3;
    public static ShopScene SCENE_EDIT_BOX;
    
    public static void init() throws IOException {
        
        SCENE_MAIN_WINDOW = new ShopScene("/fxml/Scene.fxml");
        SCENE_NEW_CHASSIS = new ShopScene("/fxml/NewChassis.fxml", Arrays.asList("/styles/NewChassis.css"));
        SCENE_EDIT_TRANSFORM = new ShopScene("/fxml/EditTransform.fxml");
        SCENE_EDIT_VECTOR3 = new ShopScene("/fxml/EditVector3.fxml");
        SCENE_EDIT_BOX = new ShopScene("/fxml/EditBox.fxml");
        
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
