/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.Globals;
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;


/**ˆ
 *
 * @author Anatolij
 */
public class ResultCmdDialogController {
    
    @FXML
    private TextArea textArea;
    private String cookedCmd;
    
    
    private void setText(String text) {
        textArea.setText(text);
    }
    
    public void setJson(
            Map<String, Object> json,
            Map<String, Integer> spriteMeta,
            Map<String, String> filePostfixes) {
        
        StringBuilder cmdBuilder = new StringBuilder();
      
        //add known usual cmd lines
        Arrays.asList(
                Globals.CMD_EXECUTABLE, 
                "-batchmode", 
                "-quit", 
                "-projectPath " + quotePath(Globals.PROJECT_ROOT_DIR),
                "-executeMethod " + Globals.CMD_IMPORTER_SCRIPT
        ).stream().forEach(line -> addCmdLine(cmdBuilder.append(line)));
        
        //-simpleassets key has a CSV list that represents simple asset keys
        //loop the json structure - if a key represents a string with 
        //a "special form" then that is a "simple asset" and need to have its
        //key registered here, while the asset itsef gets registered elsewhere
        String simpleAssets = json.keySet().stream()
                .filter(key -> {
                    Object value = json.get(key);
                    return value instanceof String &&
                            Globals.SIMPLE_ASSETS_PREFIXES.stream().anyMatch(prefix -> ((String)value).startsWith(prefix));
                }).map(key -> {
                    //add values at key
                    addCmdLine(
                            cmdBuilder
                                    .append("-").append(key)
                                    .append(" ").append(
                                            quotePath(jsonPath2CmdPath((String)json.get(key), filePostfixes.get(key))))
                    );
                    return key;
                }).collect(Collectors.joining(",", "-simpleassets ", ""));
        if (StringUtils.isNotBlank(simpleAssets)) {
            addCmdLine(cmdBuilder.append(simpleAssets));
        }
        
        //separate keys for the spritesheet stuff
        addCmdLine(cmdBuilder.append("-spritesheet ").append(
                quotePath(jsonPath2CmdPath((String)json.get("spritesheet"), filePostfixes.get("spritesheet"))))
        );
        spriteMeta.entrySet().stream().forEach(entry -> {
            addCmdLine(cmdBuilder
                    .append("-").append(entry.getKey())
                    .append(" ").append(entry.getValue()));
        });
        //final param to end command (not interpreted, 
        //but keeps newline loop healthy)
        cmdBuilder.append("-end");
        
        
        cookedCmd = cmdBuilder.toString();
        setText(cookedCmd);
    }
    
    private static String quotePath(String path) {
        if (SystemUtils.IS_OS_WINDOWS) {
            return "\"" + path + "\"";
        } else {
            return path;
        }
    }
    
    private static StringBuilder addCmdLine(StringBuilder builder) {
        return builder
                .append(Globals.CMD_LINE_SEPARATOR)
                .append(System.lineSeparator());
    }
    
    //strip special prefix and append project path start
    private static String jsonPath2CmdPath(String jsonPath, String postfix) {
      
        String[] prefixAndaPath = jsonPath.split(";", 2);
        String barePath = prefixAndaPath.length < 2? prefixAndaPath[0] : prefixAndaPath[1];
        
        String prefix = "Assets" + File.separator + "Resources" + File.separator;
        return prefix + barePath.replace("\\", File.separator) + postfix;
    }
    
    
    @FXML 
    private void handleCancel() {
        ((Stage)textArea.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleExecuteCmd() {
        
        //copy files to needed dirs before writing command to file and performing it
        
        
    }
    
    
}
