/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Anatolij
 */
public class DialogUtils {
    
    private DialogUtils() {}
    
    
    public static TextInputDialog newPartNameDialog(String partType) {
        TextInputDialog dialog = new TextInputDialog("default");
        dialog.setTitle("New " + partType + " name");
        dialog.setContentText("Input new " + partType + " name: ");
        
        return dialog;
    }
    
    
    public static Alert projectRootNotSet() {
        Alert alert = makeAlert();
        alert.setTitle("No Project root found");
        alert.setContentText("Globals.PROJECT_ROOT_DIR was null when checked!");
        
        return alert;
    }
    
    public static Alert formHasBlanks() {
        Alert alert = makeAlert();
        alert.setTitle("Form has blanks");
        alert.setContentText("Form ahs blanks, finish filling out form first!");
        
        return alert;
    }
    
    private static Alert makeAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        
        return alert;
    }
    
}
