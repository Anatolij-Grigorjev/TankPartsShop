/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Anatolij
 */
public class ContentWriterUtils {

    public final static String DOUBLE_SLASH = "\\";

    public static enum TankPartType {
        PART_CHASSIS("Chassis"),
        PART_TURRET("Turret"),
        PART_ENGINE("Engine"),
        PART_TRACKS("Tracks");
        private String folder;

        private TankPartType(String folder) {
            this.folder = folder;
        }

        public String getFolder() {
            return folder;
        }
    }

    public static enum TankPartContentType {
        CONTENT_IMAGE("!img;", Arrays.asList("Images")),
        CONTENT_SPRITESHEET("!sprites;", Arrays.asList("Images", "Sprites"));

        private List<String> preFolders;
        private String prefix;

        private TankPartContentType(String prefix, List<String> preFolders) {
            this.prefix = prefix;
            this.preFolders = preFolders;
        }

        public String getPrefix() {
            return prefix;
        }

        public List<String> getPreFolders() {
            return preFolders;
        }
    }

    private ContentWriterUtils() {
    }

    ;
    
    public static String writeLineValues(double... values) {
        StringBuilder builder = new StringBuilder(";");
        for (double val : values) {
            builder.append(val);
            builder.append(";");
        }

        return builder.toString();
    }

    public static String writeTankPartContentLeadIn(
            TankPartType partType,
            TankPartContentType contentType,
            String partName) {

        return contentType.getPrefix()
                + "TankParts" + DOUBLE_SLASH
                + partType.getFolder() + DOUBLE_SLASH
                + partName + DOUBLE_SLASH
                + contentType.getPreFolders().stream().collect(Collectors.joining(DOUBLE_SLASH, "", DOUBLE_SLASH));

    }

}
