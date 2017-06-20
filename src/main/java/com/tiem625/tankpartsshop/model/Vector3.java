/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.model;

import com.tiem625.tankpartsshop.utils.ContentWriterUtils;

/**
 *
 * @author Anatolij
 */
public class Vector3 implements ContentWriteable {
    
    public float x;
    public float y;
    public float z;
    
    public static Vector3 ZERO = new Vector3();
    public static Vector3 ONE = new Vector3(1.0f, 1.0f, 1.0f);
    
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3() {
        this(0.0f, 0.0f, 0.0f);
    }
    
    @Override
    public String toString() {
        return ContentWriterUtils.writeLineValues(x, y, z);
    }

    @Override
    public String getSpecialContentPrefix() {
        return "!v3";
    }
    
}
