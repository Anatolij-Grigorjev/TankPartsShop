/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.scenes;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Tiem625
 */
public class Scenes {
    
    private Scenes() {}
    
    public static ShopScene SCENE_MAIN_WINDOW;
    public static ShopScene SCENE_NEW_CHASSIS;
    
    public static void init(Application loader) throws IOException {
        
        SCENE_MAIN_WINDOW = new ShopScene("/fxml/Scene.fxml");
        SCENE_NEW_CHASSIS = new ShopScene("/fxml/NewChassis.fxml", Arrays.asList("/styles/NewChassis.css"));
        
    }
    
    public static Stage initStage(ShopScene scene, Consumer<Stage> decorator) {
        Stage stage = new Stage();
        stage.setScene(scene.getScene());
        decorator.accept(stage);
        
        return stage;
    }
    
}
