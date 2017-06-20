/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.model;

/**
 *
 * @author Anatolij
 */
public interface ContentWriteable {
    
    String getSpecialContentPrefix();
    
    default String getContentString() {
        return getSpecialContentPrefix() + this.toString();
    }
    
}
