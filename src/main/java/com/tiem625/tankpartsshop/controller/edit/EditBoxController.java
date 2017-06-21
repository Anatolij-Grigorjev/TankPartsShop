/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller.edit;

import com.tiem625.tankpartsshop.components.DecimalInputField;
import com.tiem625.tankpartsshop.model.Box;
import javafx.fxml.FXML;

/**
 *
 * @author Anatolij
 */
public class EditBoxController extends AbstractEditController<Box> {

    @FXML
    DecimalInputField x, y, w, h;

    @Override
    protected Box makeDefaultValue() {
        return Box.ZERO;
    }

    @Override
    protected Box makeValueFromFields() {
        return new Box(
                x.getValue().doubleValue(),
                y.getValue().doubleValue(),
                w.getValue().doubleValue(),
                h.getValue().doubleValue()
        );
    }

    @Override
    protected void setValueToFields(Box value) {
        x.setText("" + value.x);
        y.setText("" + value.y);
        w.setText("" + value.w);        
        h.setText("" + value.h);
    }

}
