/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.utils;

import java.util.Arrays;
import java.util.Collections;
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
        private final String folder;

        private TankPartType(String folder) {
            this.folder = folder;
        }

        public String getFolder() {
            return folder;
        }
        
        public String getPartTypeName() {
            return folder;
        }
    }

    public static enum TankPartContentType {
        CONTENT_IMAGE("!img;", Arrays.asList("Images")),
        CONTENT_SPRITESHEET("!sprites;", Arrays.asList("Images", "Sprites")),
        CONTENT_JSON("!json;", Collections.EMPTY_LIST);

        private final List<String> preFolders;
        private final String prefix;

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
        return Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(";", ";", ""));
    }

    public static String writeTankPartContentLeadIn(
            TankPartType partType,
            TankPartContentType contentType,
            String partName) {

        return contentType.getPrefix()
                + "TankParts" + DOUBLE_SLASH
                + partType.getFolder() + DOUBLE_SLASH
                + partName + DOUBLE_SLASH
                + (contentType.getPreFolders().isEmpty()
                ? ""
                : contentType.getPreFolders().stream().collect(Collectors.joining(DOUBLE_SLASH, "", DOUBLE_SLASH)));

    }

}
