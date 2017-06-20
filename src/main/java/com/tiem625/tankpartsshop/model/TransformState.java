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
public class TransformState implements ContentWriteable {
    
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    @Override
    public String getSpecialContentPrefix() {
        return "!transf";
    }

    @Override
    public String toString() {
        return "" + position + rotation + scale;
    }
    
}
