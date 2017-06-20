/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.utils;

/**
 *
 * @author Anatolij
 */
public class ContentWriterUtils {
    
    private ContentWriterUtils() {};
    
    
    public static String writeLineValues(double... values) {
        StringBuilder builder = new StringBuilder(";");
        for (double val: values) {
            builder.append(val);
            builder.append(";");
        }
        
        return builder.toString();
    }
    
}
