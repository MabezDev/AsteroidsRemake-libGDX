package com.mabez.com.mabez.entities;

/**
 * Created by user on 04/05/2014.
 */
public class SpaceObject {

    protected float x;
    protected float y;

    protected float dx;
    protected float dy;

    protected float directionRad;
    protected float maxSpeed;
    protected float rotationSpeed;

    protected int width;
    protected int height;

    protected float[] shapex;
    protected float[] shapey;

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setMaxSpeed(float s){
        maxSpeed = s;
    }
}
