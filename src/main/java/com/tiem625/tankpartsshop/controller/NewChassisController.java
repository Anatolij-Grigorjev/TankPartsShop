/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.components.ReadOnlyWindowedControl;
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Tiem625
 */
public class NewChassisController extends AbstractNewTankPartController {

    @FXML
    private DecimalInputField integrity,
            regeneration,
            regenDelay;

    @FXML
    private ReadOnlyWindowedControl
            rowcTurretPivot,
            rowcHealthbarOffset;

    @Override
    protected ContentWriterUtils.TankPartType getPartType() {
        return ContentWriterUtils.TankPartType.PART_CHASSIS;
    }

    @Override
    protected String makeIdFromName(String name) {
        return "CH_" + name + "01";
    }

    @Override
    protected List<String> getExtraBlankGuardFields() {
        return Arrays.asList(
                integrity.getText(),
                regeneration.getText(),
                regenDelay.getText(),
                rowcHealthbarOffset.getFieldValue(),
                rowcTurretPivot.getFieldValue(),
                pickSpriteSheet.getFilePath(),
                activeSprites.getText(),
                rowcBoxCollider.getFieldValue()
        );
    }

    @Override
    protected void addExtraJsonMapFields(Map<String, Object> partialJson) {
        partialJson.put("box_collider", rowcBoxCollider.getFieldValue());
        partialJson.put("turret_pivot", rowcTurretPivot.getFieldValue());
        partialJson.put("healthbar_offset", rowcHealthbarOffset.getFieldValue());
        partialJson.put("integrity", integrity.getValue());
        partialJson.put("regeneration", regeneration.getValue());
        partialJson.put("regen_delay", regenDelay.getValue());
    }

}
