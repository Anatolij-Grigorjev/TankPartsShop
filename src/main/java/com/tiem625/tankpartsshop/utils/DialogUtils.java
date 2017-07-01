/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 *
 * @author Anatolij
 */
public class DialogUtils {
    
    private DialogUtils() {}
    
    
    public static TextInputDialog newPartNameDialog(Window owner, String partType) {
        TextInputDialog dialog = new TextInputDialog("default");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("New " + partType + " name");
        dialog.setContentText("Input new " + partType + " name: ");

        return dialog;
    }
    
    
    public static Alert projectRootNotSet(Window owner) {
        Alert alert = makeAlert(owner, Alert.AlertType.WARNING);
        alert.setTitle("No Project root found");
        alert.setContentText("Globals.PROJECT_ROOT_DIR was null when checked!");
        
        return alert;
    }
    
    public static Alert formHasBlanks(Window owner) {
        Alert alert = makeAlert(owner, Alert.AlertType.WARNING);
        alert.setTitle("Form has blanks");
        alert.setContentText("Form has blanks, finish filling out form first!");
        
        return alert;
    }
    
    public static Alert commandCompleteOK(Window owner) {
        Alert alert = makeAlert(owner, Alert.AlertType.INFORMATION);
        alert.setTitle("Command OK");
        alert.setContentText("Command completed successfully! Check results.");
        
        return alert;
    }
    
    private static Alert makeAlert(Window owner, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(owner);
        
        return alert;
    }
    
    
   
    
}
