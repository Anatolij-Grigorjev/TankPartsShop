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

    public static TransformState ZERO = new TransformState();

    public TransformState() {
        this(Vector3.ZERO, Vector3.ZERO, Vector3.ONE);
    }

    public TransformState(Vector3 pos, Vector3 rot, Vector3 scale) {
        this.position = pos;
        this.rotation = rot;
        this.scale = scale;
    }

    public TransformState(double[] array) {

        this(new Vector3(array[0], array[1], array[2]),
                new Vector3(array[3], array[4], array[5]),
                new Vector3(array[6], array[7], array[8]));
    }

    @Override
    public String getSpecialContentPrefix() {
        return "!transf";
    }

    @Override
    public String toString() {
        //skip the leading ';' when adding more vectors
        return position.toString()
                + rotation.toString().substring(1)
                + scale.toString().substring(1);
    }

}
