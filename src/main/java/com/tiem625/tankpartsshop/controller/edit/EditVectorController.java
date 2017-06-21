/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller.edit;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.model.Vector3;
import javafx.fxml.FXML;

/**
 *
 * @author Anatolij
 */
public class EditVectorController extends AbstractEditController<Vector3> {

    @FXML
    DecimalInputField x, y, z;

    @Override
    protected Vector3 makeDefaultValue() {
        return Vector3.ZERO;
    }

    @Override
    protected Vector3 makeValueFromFields() {
        return new Vector3(
                x.getValue().doubleValue(),
                y.getValue().doubleValue(),
                z.getValue().doubleValue()
        );
    }

    @Override
    protected void setValueToFields(Vector3 value) {
        x.setText("" + value.x); 
        y.setText("" + value.y);
        z.setText("" + value.z);   
    }

}
