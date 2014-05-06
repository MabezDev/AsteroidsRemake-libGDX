package com.mabez.com.mabez.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by user on 04/05/2014.
 */
public class Asteroid extends SpaceObject {

        private float retardation = 10;
        private OrthographicCamera cam;

        private boolean shouldRemove;

    public Asteroid(OrthographicCamera cam){
        shouldRemove=false;
        this.cam=cam;
        setMaxSpeed(50);
        dx = 0;
        dy=0;
        x = MathUtils.random(0,cam.viewportWidth);
        y = MathUtils.random(0,cam.viewportHeight);
        rotationSpeed = MathUtils.random(0.2f,1);
    }





    public boolean ShouldRemove(){
         return shouldRemove;
    }

    public void update(float dt){
        directionRad += rotationSpeed*dt;
        dx = MathUtils.cos(directionRad)*100;
        dx = MathUtils.sin(directionRad)*100;

        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if(vec > 0) {
            dx -= (dx / vec) * retardation * dt;
            dy -= (dy / vec) * retardation * dt;
        }
        if(vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }


        y+=dy*dt;
        x+=dx*dt;


    }

    public void draw(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Line);

        sr.setColor(1,1,1,1);

        sr.circle(x,y,30);

        sr.end();
    }

}
