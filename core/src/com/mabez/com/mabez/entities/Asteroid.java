package com.mabez.com.mabez.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by user on 04/05/2014.
 */
public class Asteroid extends SpaceObject {

        private float maxSpeed;
        private OrthographicCamera cam;

        private boolean shouldRemove;

    public Asteroid(OrthographicCamera cam){
        shouldRemove=false;
        this.cam=cam;
        maxSpeed = 50;
        dx = 0;
        dy=0;
        x = MathUtils.random(0,cam.viewportWidth);
        y = MathUtils.random(0,cam.viewportHeight);
        directionRad = 3.14f/2;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }



    public void update(float dt){
        directionRad += MathUtils.random(-1,1)*10*dt;
        dx = MathUtils.cos(directionRad)*maxSpeed;
        dx = MathUtils.sin(directionRad)*maxSpeed;


        y+=dy*dt;
        x+=dx*dt;

    }

    public boolean ShouldRemove(){
         return shouldRemove;
    }

    public void draw(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(1,1,1,1);
        sr.circle(x,y,30);

        sr.end();
    }

}
