/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.scenes.Scenes;
import com.tiem625.tankpartsshop.scenes.ShopScene;
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
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

    private ShopScene spriteMetaScene;
    private Stage spriteMetaStage;
    private Map<String, String> filePostfixes;
    private Map<String, String> filePaths;

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
        ((Stage) textArea.getScene().getWindow()).close();
    }

    @FXML
    private void handleCookCmd() {

        if (spriteMetaStage == null) {
            spriteMetaScene = Scenes.SCENE_SPRITE_META();
            spriteMetaStage = Scenes.initUtilityStage(spriteMetaScene);
        }
        
        SpritesheetMetaDialogController controller = 
                (SpritesheetMetaDialogController) spriteMetaScene.getController();
//        String spritesheetPath = (String) json.get("spritesheet");
//        controller.setSpriteName(
//                spritesheetPath.substring(spritesheetPath.lastIndexOf(ContentWriterUtils.DOUBLE_SLASH))
//        );
        spriteMetaStage.showAndWait();
        Map<String, Integer> spriteSheetMeta = controller.getMeta();
        //cancel was pressed, quit cooking command
        if (spriteSheetMeta.isEmpty()) {
            return;
        }
        
        //call cooker dailog
        if (cmdStage == null) {
            cmdScene = Scenes.SCENE_CMD_DIALOG();
            cmdStage = Scenes.initUtilityStage(cmdScene);
        }
        ((ResultCmdDialogController)cmdScene.getController())
                .setJson(json, spriteSheetMeta, filePostfixes, filePaths);
        cmdStage.showAndWait();

    }

    void setFilePostfixesMap(Map<String, String> postfixes) {
        this.filePostfixes = postfixes;
    }

    void setFilePathsMap(Map<String, String> paths) {
        this.filePaths = paths;
    }

}
