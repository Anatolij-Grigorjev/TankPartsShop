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
 *
 * @author Tiem625
 */
public class NewTracksController extends AbstractNewTankPartController {
    
    @FXML
    private DecimalInputField 
            coupling, 
            turnSpeed, 
            lowerIntegrity;
    
    @FXML
    private ReadOnlyWindowedControl 
            rowcLeftTrackPosition,
            rowcRightTrackPosition;
    

    @Override
    protected ContentWriterUtils.TankPartType getPartType() {
        return ContentWriterUtils.TankPartType.PART_TRACKS;
    }

    @Override
    protected String makeIdFromName(String name) {
        return "TRK_" + name + "01";
    }

    @Override
    protected List<String> getExtraBlankGuardFields() {
        return Arrays.asList(
                coupling.getText(),
                turnSpeed.getText(),
                lowerIntegrity.getText(),
                rowcLeftTrackPosition.getFieldValue(),
                rowcRightTrackPosition.getFieldValue(),
                pickSpriteSheet.getFilePath()
        );
    }

    @Override
    protected void addExtraJsonMapFields(Map<String, Object> partialJson) {
        partialJson.put("coupling", coupling.getValue());
        partialJson.put("turn_speed", turnSpeed.getValue());
        partialJson.put("lower_integrity", lowerIntegrity.getValue());
        partialJson.put("left_track_pos", rowcLeftTrackPosition.getFieldValue());
        partialJson.put("right_track_pos", rowcRightTrackPosition.getFieldValue());
    }
    
}
