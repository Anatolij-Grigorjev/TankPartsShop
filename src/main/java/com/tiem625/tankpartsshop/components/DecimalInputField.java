/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.components;

import java.math.BigDecimal;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Tiem625
 */
public class DecimalInputField extends TextField {
    
    private final String DECIMAL_REGEX = "^-{0,1}\\d+\\.?\\d*$";
    
    public DecimalInputField() {
        super();
        this.textProperty().addListener((value, oldVal, newVal) -> {
            if (!newVal.matches(DECIMAL_REGEX)) {
                if (oldVal.matches(DECIMAL_REGEX)) {
                   this.setText(oldVal); 
                } else {
                    this.setValue(BigDecimal.ZERO);
                }
            }
        });
    }
    
    public DecimalInputField(BigDecimal value) {
        this();
        setValue(value);
    }
   
    public BigDecimal getValue() {
        if (!StringUtils.isEmpty(getText())) {
            return new BigDecimal(getText());
        } else {
            return BigDecimal.ZERO;
        }
    }
    
    public void setValue(BigDecimal value) {
        setText(value != null? value.toString(): "");
    }
    
}
