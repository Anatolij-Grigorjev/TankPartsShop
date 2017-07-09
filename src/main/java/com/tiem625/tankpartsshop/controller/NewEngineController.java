/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.components.PickFileControl;
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;

/**
 *
 * @author Tiem625
 */
public class NewEngineController extends AbstractNewTankPartController {
    
    @FXML
    private DecimalInputField
            torque,
            max_acceleration,
            acceleration_rate,
            deacceleration_rate;
    
    @FXML
    private PickFileControl pickIdleSound, pickRevvingSound;

    @Override
    protected ContentWriterUtils.TankPartType getPartType() {
        return ContentWriterUtils.TankPartType.PART_ENGINE;
    }

    @Override
    protected String makeIdFromName(String name) {
        return "ENG_" + name + "01";
    }

    @Override
    protected List<String> getExtraBlankGuardFields() {
        return Arrays.asList(
                torque.getText(),
                max_acceleration.getText(),
                acceleration_rate.getText(),
                deacceleration_rate.getText(),
                pickIdleSound.getFilePath(),
                pickRevvingSound.getFilePath()
        );
    }

    @Override
    protected void addExtraJsonMapFields(Map<String, Object> partialJson) {
        
        String soundLeadIn = ContentWriterUtils.writeTankPartContentLeadIn(
                ContentWriterUtils.TankPartType.PART_ENGINE,
                ContentWriterUtils.TankPartContentType.CONTENT_SOUND,
                nameField.getText());
        
        partialJson.put("torque", torque.getValue());
        partialJson.put("max_acceleration", max_acceleration.getValue());
        partialJson.put("acceleration_rate", acceleration_rate.getValue());
        partialJson.put("deacceleration_rate", deacceleration_rate.getValue());
        partialJson.put("idle_sound", soundLeadIn + pickIdleSound.getStrippedFileName());
        partialJson.put("revving_sound", soundLeadIn + pickRevvingSound.getStrippedFileName());
        
    }

    @Override
    protected void addExtraPostfixes(Map<String, String> postfixes) {
        super.addExtraPostfixes(postfixes); 
        postfixes.put("idle_sound", pickIdleSound.getFilenamePostfix());
        postfixes.put("revving_sound", pickRevvingSound.getFilenamePostfix());
    }

    @Override
    protected void addExtraPaths(Map<String, String> paths) {
        super.addExtraPaths(paths);
        paths.put("idle_sound", pickIdleSound.getFilePath());
        paths.put("revving_sound", pickRevvingSound.getFilePath());
    }
    
    
    
    
}
