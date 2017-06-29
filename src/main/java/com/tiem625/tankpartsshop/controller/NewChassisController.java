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
import java.util.Arrays;
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
    public PickFileControl pickSpriteSheet,
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

        boolean hasBlanks = Arrays.asList(
                idField.getText(),
                nameField.getText(),
                pickSpriteSheet.getFilePath(),
                pickShopImage.getFilePath(),
                pickGarageImage.getFilePath(),
                mass.getText(),
                integrity.getText(),
                regeneration.getText(),
                regenDelay.getText(),
                activeSprites.getText(),
                price.getText(),
                rowcBoxCollider.getFieldValue(),
                rowcHealthbarOffset.getFieldValue(),
                rowcTankPosition.getFieldValue(),
                rowcTurretPivot.getFieldValue()
        ).stream().anyMatch(StringUtils::isBlank);
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
            
            String jsonLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                    ContentWriterUtils.TankPartType.PART_CHASSIS,
                    ContentWriterUtils.TankPartContentType.CONTENT_JSON,
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
            newChassis.put("json", jsonLeadIn + nameField.getText().toLowerCase().replace(" ", "_"));
            
            //postfixes needed when assembling console command
            Map<String, String> postfixes = new HashMap<>();
            postfixes.put("shop_item", pickShopImage.getFilenamePostfix());
            postfixes.put("garage_item", pickGarageImage.getFilenamePostfix());
            postfixes.put("spritesheet", pickSpriteSheet.getFilenamePostfix());
            postfixes.put("json", ".json");
            
            if (resultsJSONStage == null) {
                scene = Scenes.SCENE_RESULTS_DIALOG();
                resultsJSONStage = Scenes.initUtilityStage(scene);
            }
            ResultJSONDialogController controller = ((ResultJSONDialogController)scene.getController());
            controller.setFilePostfixesMap(postfixes);
            controller.setJSONMap(newChassis);
            resultsJSONStage.showAndWait();

        }

    }

}
