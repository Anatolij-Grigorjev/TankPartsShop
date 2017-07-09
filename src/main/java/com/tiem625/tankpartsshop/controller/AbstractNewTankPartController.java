/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.components.PickFileControl;
import com.tiem625.tankpartsshop.components.ReadOnlyWindowedControl;
import com.tiem625.tankpartsshop.scenes.Scenes;
import com.tiem625.tankpartsshop.scenes.ShopScene;
import com.tiem625.tankpartsshop.scenes.WindowType;
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
import com.tiem625.tankpartsshop.utils.DialogUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Tiem625
 */
public abstract class AbstractNewTankPartController implements Initializable {
    
    @FXML
    protected AnchorPane rootPane;

    @FXML
    protected TextField idField,
            nameField;

    @FXML
    public PickFileControl pickSpriteSheet,
            pickShopImage,
            pickGarageImage;

    @FXML
    protected DecimalInputField mass,
            activeSprites,
            price;

    @FXML
    protected ReadOnlyWindowedControl rowcTankPosition,
            rowcBoxCollider;
    
    protected ShopScene resultJSONScene;
    protected Stage resultsJSONStage;
    
    protected abstract ContentWriterUtils.TankPartType getPartType();
    protected abstract String makeIdFromName(String name);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextInputDialog nameDialog = DialogUtils.newPartNameDialog(
                Scenes.topWindows.get(WindowType.MAIN_WINDOW).getScene().getWindow(),
                getPartType().getPartTypeName());
        String name = nameDialog.showAndWait().orElse("default");

        idField.setText(makeIdFromName(name));
        nameField.setText(StringUtils.capitalize(name.toLowerCase()));
    }
    
    @FXML
    protected void handleCancel() {
        ((Stage) rootPane.getScene().getWindow()).close();
    }
    
    abstract protected List<String> getExtraBlankGuardFields();

    @FXML
    protected void handleMakeJSONButton() throws IOException {
        
        List<String> defaultNonBlanks = new ArrayList<>(Arrays.asList(
                idField.getText(),
                nameField.getText(),
                price.getText(),
                mass.getText(),
                pickShopImage.getFilePath(),
                pickGarageImage.getFilePath(),
                rowcTankPosition.getFieldValue()
        ));
        defaultNonBlanks.addAll(getExtraBlankGuardFields());
        
        boolean hasBlanks = 
                defaultNonBlanks.stream()
                .anyMatch(StringUtils::isBlank);
        if (hasBlanks) {
            DialogUtils.formHasBlanks(rootPane.getScene().getWindow())
                    .showAndWait();
        } else {

            String singleImageLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                    getPartType(),
                    ContentWriterUtils.TankPartContentType.CONTENT_IMAGE,
                    nameField.getText()
            );
            String spritesheetLeadIn = null;
            if (pickSpriteSheet != null && 
                    StringUtils.isNotBlank(pickSpriteSheet.getFilePath())) {
                spritesheetLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                        getPartType(),
                        ContentWriterUtils.TankPartContentType.CONTENT_SPRITESHEET, 
                        nameField.getText());
            }
            
            String jsonLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                    getPartType(),
                    ContentWriterUtils.TankPartContentType.CONTENT_JSON,
                    nameField.getText());

            Map<String, Object> newPartJson = new HashMap();

            newPartJson.put("id", idField.getText());
            newPartJson.put("name", nameField.getText());
            newPartJson.put("on_tank_position", rowcTankPosition.getFieldValue());
            newPartJson.put("mass", mass.getValue());
            newPartJson.put("shop_item", singleImageLeadIn + pickShopImage.getStrippedFileName());
            newPartJson.put("garage_item", singleImageLeadIn + pickGarageImage.getStrippedFileName());
            newPartJson.put("price", price.getValue());
            newPartJson.put("json", jsonLeadIn + nameField.getText().toLowerCase().replace(" ", "_"));
            if (spritesheetLeadIn != null) {
                newPartJson.put("spritesheet", spritesheetLeadIn + pickSpriteSheet.getStrippedFileName());
                //if this is not specified, the default is 1
                if (activeSprites != null) {
                    newPartJson.put("active_sprites", activeSprites.getValue());
                }
            }
            if (rowcBoxCollider != null && 
                    StringUtils.isNotBlank(rowcBoxCollider.getFieldValue())) {
                newPartJson.put("box_collider", rowcBoxCollider.getFieldValue());
            }
            addExtraJsonMapFields(newPartJson);
            
            //postfixes needed when assembling console command
            Map<String, String> postfixes = new HashMap<>();
            postfixes.put("shop_item", pickShopImage.getFilenamePostfix());
            postfixes.put("garage_item", pickGarageImage.getFilenamePostfix());
            postfixes.put("json", ".json");
            if (spritesheetLeadIn != null) {
                postfixes.put("spritesheet", pickSpriteSheet.getFilenamePostfix());
            }
            addExtraPostfixes(postfixes);
            
            //paths needed when assembling console command
            Map<String, String> paths = new HashMap<>();
            paths.put("shop_item", pickShopImage.getFilePath());
            paths.put("garage_item", pickGarageImage.getFilePath());
            if (spritesheetLeadIn != null) {
                paths.put("spritesheet", pickSpriteSheet.getFilePath());
            }
            addExtraPaths(paths);
            
            if (resultsJSONStage == null) {
                resultJSONScene = Scenes.SCENE_RESULTS_DIALOG();
                resultsJSONStage = Scenes.initUtilityStage(resultJSONScene);
            }
            ResultJSONDialogController controller = 
                    ((ResultJSONDialogController)resultJSONScene.getController());
            controller.setFilePostfixesMap(postfixes);
            controller.setFilePathsMap(paths);
            controller.setJSONMap(newPartJson);
            resultsJSONStage.showAndWait();

        }
    }
    
    protected abstract void addExtraJsonMapFields(Map<String, Object> partialJson);

    protected void addExtraPostfixes(Map<String, String> postfixes) {        
    }

    protected void addExtraPaths(Map<String, String> paths) { 
    }
    
}
