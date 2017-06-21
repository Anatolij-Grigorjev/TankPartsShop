/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller.edit;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.model.TransformState;
import java.util.stream.Stream;
import javafx.fxml.FXML;

/**
 *
 * @author Anatolij
 */
public class EditTransformController extends AbstractEditController<TransformState> {

    @FXML
    private DecimalInputField posX, posY, posZ,
            rotX, rotY, rotZ,
            scaleX, scaleY, scaleZ;

    @Override
    protected TransformState makeDefaultValue() {
        return TransformState.ZERO;
    }

    @Override
    protected TransformState makeValueFromFields() {
        double[] floats = Stream.of(posX, posY, posZ, rotX, rotY, rotZ, scaleX, scaleY, scaleZ)
                .mapToDouble(dif -> dif.getValue().doubleValue()).toArray();
        return new TransformState(floats);
    }

    @Override
    protected void setValueToFields(TransformState value) {
        posX.setText("" + value.position.x);
        posY.setText("" + value.position.y);
        posZ.setText("" + value.position.z);
        rotX.setText("" + value.rotation.x);
        rotY.setText("" + value.rotation.y);
        rotZ.setText("" + value.rotation.z);
        scaleX.setText("" + value.scale.x);
        scaleY.setText("" + value.scale.y);
        scaleZ.setText("" + value.scale.z);
    }

}
