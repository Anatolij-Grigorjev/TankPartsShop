/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller;

import com.tiem625.tankpartsshop.Globals;
import com.tiem625.tankpartsshop.scenes.Scenes;
import com.tiem625.tankpartsshop.scenes.WindowType;
import com.tiem625.tankpartsshop.utils.ContentWriterUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.codehaus.jackson.map.ObjectMapper;


/**ˆ
 *
 * @author Anatolij
 */
public class ResultCmdDialogController {
    
    @FXML
    private TextArea textArea;
    private String cookedCmd;
    
    private Map<String, Object> json;
    private Map<String, Integer> spriteMeta;
    private Map<String, String> filePostfixes;
    private Map<String, String> filePaths;
    private Map<String, String> cmdElements;
    
    
    private void setText(String text) {
        textArea.setText(text);
    }
    
    public void setJson(
            Map<String, Object> json,
            Map<String, Integer> spriteMeta,
            Map<String, String> filePostfixes,
            Map<String, String> filePaths) {
      
        //slightly more permissive map
        this.cmdElements = new HashMap<String, String>() {
            @Override
            public String get(Object key) {
                String value = super.get(key); 
                if (value == null) {
                    return "";
                } else {
                    return value;
                }
            }
            
        };
        this.json = json;
        this.spriteMeta = spriteMeta;
        this.filePostfixes = filePostfixes;
        this.filePaths = filePaths;
        
        StringBuilder cmdBuilder = new StringBuilder();
      
        //add known usual cmd lines
        Arrays.asList(
                Globals.CMD_EXECUTABLE, 
                "-batchmode", 
                "-quit", 
                "-projectPath " + quotePath(Globals.PROJECT_ROOT_DIR),
                "-executeMethod " + Globals.CMD_IMPORTER_SCRIPT
        ).stream().forEach(line -> {
            writeLineAsElement(line);
            addCmdLine(cmdBuilder.append(line));
                });
        
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
                    String pathVal = quotePath(jsonPath2CmdPath((String)json.get(key), filePostfixes.get(key)));
                    cmdElements.put(key, pathVal);
                    //add values at key
                    addCmdLine(
                            cmdBuilder
                                    .append("-").append(key)
                                    .append(" ").append(pathVal)
                    );
                    return key;
                }).collect(Collectors.joining(",", "-simpleassets ", ""));
        if (StringUtils.isNotBlank(simpleAssets)) {
            writeLineAsElement(simpleAssets);
            addCmdLine(cmdBuilder.append(simpleAssets));
        }
        
        //separate keys for the spritesheet stuff
        String sheetPath = quotePath(jsonPath2CmdPath((String)json.get("spritesheet"), filePostfixes.get("spritesheet")));
        cmdElements.put("spritesheet", sheetPath);
        addCmdLine(cmdBuilder.append("-spritesheet ").append(sheetPath));
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
    
    private void writeLineAsElement(String line) {
        String[] lineParts = line.split("\\s+", 2);
        //map elements without the minus
        cmdElements.put(lineParts[0].substring(1), lineParts.length > 1? lineParts[1] : "");
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
    private void handleExecuteCmd() throws IOException {
        
        //TODO: use chassis controller paths for stuff like copying files n shit
        //generate command file itself
        File commandFile = new File(Globals.PROJECT_ROOT_DIR + File.separator
                + "Commands" + File.separator 
                + json.get("name") + "_cmd" + Globals.CMD_EXTENSION);
        commandFile.getParentFile().mkdirs();
        commandFile.createNewFile();
        try (Writer writer = new FileWriter(commandFile)) {
            if (StringUtils.isNotBlank(Globals.CMD_FILE_SHEBANG)) {
                writer.write(Globals.CMD_FILE_SHEBANG);
                writer.write(System.lineSeparator());
            }
            writer.write(cookedCmd);
        }
        
        //try creating json file
        File jsonDestinationFile = new File(Globals.PROJECT_ROOT_DIR + 
                File.separator + 
                jsonPath2CmdPath((String) json.get("json"), filePostfixes.get("json")));
        
        File jsonSource = File.createTempFile("tankpartsjson", ".json");
        try (Writer writer = new FileWriter(jsonSource)) {
            writer.write(
                    new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(json)
            );
        }
        writePartFile(jsonSource, jsonDestinationFile);
        //do simple assets and spritesheet if they real
        List<String> pathsKeysList = Arrays.asList(cmdElements.get("simpleassets").split(","));
        pathsKeysList.add("spritesheet");
        pathsKeysList.forEach( asset -> {
            File destination = new File(Globals.PROJECT_ROOT_DIR + 
                    File.separator + 
                    cmdElements.get(asset));
            
            File source = new File(filePaths.get(asset));
            
            try {
                writePartFile(source, destination);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        //execute generated cmd
        
    }
    
    private void writePartFile(File srcPath, File destPath) throws IOException {
        
        if (!srcPath.exists()) {
            throw new RuntimeException("no such file " + srcPath.getAbsolutePath());
        }
        
        //make destination dirs
        File parentFile = destPath.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        destPath.createNewFile();
        
        IOUtils.copy(new FileInputStream(srcPath), 
                new FileOutputStream(destPath));
    }
    
    
}
