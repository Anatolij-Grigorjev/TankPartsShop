/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.scenes;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Tiem625
 */
public class Scenes {
    
    private Scenes() {}
    
    public static Parent SCENE_MAIN_WINDOW;
    public static Parent SCENE_NEW_CHASSIS;
    
    public static void init(Application loader) throws IOException {
        
        SCENE_MAIN_WINDOW = FXMLLoader.load(loader.getClass().getResource("/fxml/Scene.fxml"));
        SCENE_NEW_CHASSIS = FXMLLoader.load(loader.getClass().getResource("/fxml/NewChassis.fxml"));
        
    }
    
}
