/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.SystemUtils;

/**
 *
 * @author Anatolij
 */
public class Globals {
    
    private Globals() {
        if (SystemUtils.IS_OS_MAC_OSX) {
            CMD_EXECUTABLE = "/Applications/Unity.app/Contents/MacOS/Unity " ;
            CMD_LINE_SEPARATOR = " \\";
            CMD_FILE_SHEBANG = "#!/bin/sh";
        } else {
            CMD_EXECUTABLE = "\"C:\\Program Files\\Unity\\Unity.exe\"";
            CMD_LINE_SEPARATOR = " ^";
            CMD_FILE_SHEBANG = "";
        }
    }
    
    public static String PROJECT_ROOT_DIR = null;
    public static List<String> SIMPLE_ASSETS_PREFIXES = Arrays.asList(
            "!img;", 
            "!snd;"
    );
    public static String CMD_EXECUTABLE;
    public static String CMD_LINE_SEPARATOR; 
    public static String CMD_IMPORTER_SCRIPT = "DefaultImporter.ImportPart";
    public static String CMD_FILE_SHEBANG;
    
}
