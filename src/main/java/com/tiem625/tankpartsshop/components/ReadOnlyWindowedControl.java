/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.components;

import com.tiem625.tankpartsshop.controller.edit.AbstractEditController;
import com.tiem625.tankpartsshop.model.Box;
import com.tiem625.tankpartsshop.model.ContentWriteable;
import com.tiem625.tankpartsshop.model.TransformState;
import com.tiem625.tankpartsshop.model.Vector3;
import com.tiem625.tankpartsshop.scenes.Scenes;
import com.tiem625.tankpartsshop.scenes.ShopScene;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Anatolij
 */
public class ReadOnlyWindowedControl extends CustomVBoxControl {

    @FXML
    private Label topLabel;
    @FXML
    private Label fieldLabel;
    @FXML
    private Label valueLabel;

    private Stage editValueStage;
    private ShopScene editScene;
    private final EditWindowType editType;

    public ReadOnlyWindowedControl(@NamedArg("type") String type) {
        super("/fxml/components/ReadOnlyWindowedControl.fxml");
        //need init for later, scenes not ready for stuff yet
        editType = EditWindowType.valueOf(type);
    }

    public void setTopText(String topText) {
        topLabel.setText(topText);
    }

    public void setFieldText(String fieldText) {
        fieldLabel.setText(fieldText);
    }

    public void setFieldValue(String fieldValue) {
        valueLabel.setText(fieldValue);
    }

    public String getTopText() {
        return topLabel.getText();
    }

    public String getFieldText() {
        return fieldLabel.getText();
    }

    public String getFieldValue() {
        return valueLabel.getText();
    }

    @FXML
    private void handleNeedEditWindow() {
        AbstractEditController<ContentWriteable> controller = null;
        if (editValueStage == null) {
            ContentWriteable value = null;
            
            switch (editType) {
                case TRANSFORM:
                    editScene = Scenes.SCENE_EDIT_TRANSFORM();
                    value = TransformState.ZERO;
                    break;
                case VECTOR3:
                    editScene = Scenes.SCENE_EDIT_VECTOR3();
                    value = Vector3.ZERO;
                    break;
                case BOX:
                    editScene = Scenes.SCENE_EDIT_BOX();
                    value = Box.ZERO;
                    break;
                default:
                    throw new AssertionError(editType);
            }
            editValueStage = Scenes.initUtilityStage(editScene);
            controller = (AbstractEditController<ContentWriteable>) editScene.getController();
            controller.setValue(value);
        } else {
            controller = (AbstractEditController<ContentWriteable>) editScene.getController();
        }
        editValueStage.showAndWait();
        setFieldValue(controller.getContentWriteable().getContentString());
    }

    public static enum EditWindowType {
        TRANSFORM, VECTOR3, BOX
    }

}
