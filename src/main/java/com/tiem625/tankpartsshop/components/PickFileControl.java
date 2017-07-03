/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.components;

import com.tiem625.tankpartsshop.Globals;
import java.io.File;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author Tiem625
 */
public class PickFileControl extends CustomVBoxControl {
    
    @FXML
    private Label fieldLabel;
    
    @FXML
    private TextField inputField;
    
    private final FileChooser fileChooser;
    
    
    public PickFileControl() {
        super("/fxml/components/PickFileControl.fxml");
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(Globals.PROJECT_ROOT_DIR));
    }
    
    public String getFieldName() {
        return fieldLabel.getText();
    }
    
    public void setFieldName(String value) {
        fieldLabel.setText(value);
    }
    
    public String getFilePath() {
        return inputField.getText();
    }
    
    public String getFileName() {
        
        String[] patternBits = getFilePath()
                .split(Pattern.compile(StringEscapeUtils.escapeJava(File.separator)).pattern()); 
        //take last
        return patternBits[patternBits.length - 1];
    }
    
    public String getStrippedFileName() {
        String filename = getFileName();
        return filename.substring(0, filename.lastIndexOf("."));
    }
    
    public String getFilenamePostfix() {
        return getFileName().substring(getStrippedFileName().length());
    }
    
    @FXML
    private void handleChoosingFile() {
        
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        if (file != null) {
            inputField.setText(file.getAbsolutePath());
        }
    }
}
