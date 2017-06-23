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
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
import com.tiem625.tankpartsshop.utils.DialogUtils;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * FXML Controller class
 *
 * @author Tiem625
 */
public class NewChassisController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField idField,
            nameField;

    @FXML
    private PickFileControl pickSpriteSheet,
            pickShopImage,
            pickGarageImage;

    @FXML
    private DecimalInputField mass,
            integrity,
            regeneration,
            regenDelay,
            activeSprites,
            price;

    @FXML
    private ReadOnlyWindowedControl rowcTankPosition,
            rowcTurretPivot,
            rowcHealthbarOffset,
            rowcBoxCollider;
    
    private ShopScene scene;
    private Stage resultsJSONStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextInputDialog nameDialog = DialogUtils.newPartNameDialog("Chassis");
        String name = nameDialog.showAndWait().orElse("default");

        idField.setText("CH_" + name.toLowerCase() + "01");
        nameField.setText(StringUtils.capitalize(name.toLowerCase()));

    }

    @FXML
    private void handleCancel() {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void handleMakeJSONButton() throws IOException {

        boolean hasBlanks = rootPane.getChildren().stream()
                .filter(node -> {
                    return (node instanceof TextField) || 
                            (node instanceof ReadOnlyWindowedControl);
                })
                .map(node -> {
            if (node instanceof TextField) {
                return ((TextField) node).getText();
            } else if (node instanceof ReadOnlyWindowedControl) {
                return ((ReadOnlyWindowedControl) node).getFieldValue();
            }
            return null;
        }).anyMatch(StringUtils::isBlank);
        if (hasBlanks) {
            DialogUtils.formHasBlanks().showAndWait();
        } else {

            String singleImageLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                    ContentWriterUtils.TankPartType.PART_CHASSIS,
                    ContentWriterUtils.TankPartContentType.CONTENT_IMAGE,
                    nameField.getText()
            );
            
            String spritesheetLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                    ContentWriterUtils.TankPartType.PART_CHASSIS,
                    ContentWriterUtils.TankPartContentType.CONTENT_SPRITESHEET, 
                    nameField.getText());

            Map<String, Object> newChassis = new HashMap();

            newChassis.put("id", idField.getText());
            newChassis.put("name", nameField.getText());
            newChassis.put("on_tank_position", rowcTankPosition.getFieldValue());
            newChassis.put("mass", mass.getValue());
            newChassis.put("shop_item", singleImageLeadIn + pickShopImage.getStrippedFileName());
            newChassis.put("garage_item", singleImageLeadIn + pickGarageImage.getStrippedFileName());
            newChassis.put("integrity", integrity.getValue());
            newChassis.put("regeneration", regeneration.getValue());
            newChassis.put("regen_delay", regenDelay.getValue());
            newChassis.put("spritesheet", spritesheetLeadIn + pickSpriteSheet.getStrippedFileName());
            newChassis.put("active_sprites", activeSprites.getValue());
            newChassis.put("price", price.getValue());
            newChassis.put("box_collider", rowcBoxCollider.getFieldValue());
            newChassis.put("turret_pivot", rowcTurretPivot.getFieldValue());
            newChassis.put("healthbar_offset", rowcHealthbarOffset.getFieldValue());
            
            
            String chassisJson = new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(newChassis);
            
            
            if (resultsJSONStage == null) {
                scene = Scenes.SCENE_RESULTS_DIALOG();
                resultsJSONStage = Scenes.initUtilityStage(scene);
            }
            
            ((ResultJSONDialogController)scene.getController()).setText(chassisJson);
            resultsJSONStage.showAndWait();

        }

    }

}
