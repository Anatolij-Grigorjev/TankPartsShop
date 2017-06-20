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
public class Box implements ContentWriteable {

    public double x;
    public double y;
    public double w;
    public double h;

    public static Box ZERO = new Box();

    public Box() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public Box(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return ContentWriterUtils.writeLineValues(x, y, w, h);
    }

    @Override
    public String getSpecialContentPrefix() {
        return "!box";
    }

}
