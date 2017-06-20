/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.scenes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 *
 * @author Tiem625
 */
public class ShopScene {
    
    protected Scene scene;
    protected Object controller;
    protected List<String> styles;
    
    public ShopScene(String path) throws IOException {
        this(path, Collections.EMPTY_LIST);
    }
    
    public ShopScene(String path, List<String> extraStyles) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        scene = new Scene(loader.load());
        controller = loader.getController();
        styles = new ArrayList<>();
        styles.add("/styles/Common.css");
        styles.addAll(extraStyles);
        scene.getStylesheets().addAll(styles);
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public List<String> getStyles() {
        return styles;
    }
    
    public Object getController() {
        return controller;
    }
    
}
