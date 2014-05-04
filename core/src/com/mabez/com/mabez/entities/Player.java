package com.mabez.com.mabez.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Created by user on 04/05/2014.
 */
public class Player extends SpaceObject {


    public boolean left;
    public boolean right;
    public boolean up;
    public boolean space;
    public boolean shift;
    private static final int maxBullets = 4;

    private float maxSpeed;
    private float acceleration;
    private float retardation;
    private static final float Pi = 3.14f;
    private ArrayList<Bullet> bullets;
    protected OrthographicCamera cam;
    float vel;
    private float boostTimer;
    private float boostTime;

    public Player(OrthographicCamera cam) {
        this.cam=cam;
        y = 200;
        x = 200;
        dy=0;
        dx=0;
        maxSpeed = 250;
        acceleration = 150;
        retardation = 10;
        shapex = new float[4];
        shapey = new float[4];
        rotationSpeed = Pi;
        bullets = new ArrayList<Bullet>();
        boostTimer = 0;
        boostTime = 1;

    }

    public void setShape(){

        //vertices of polygon
        shapex[0] = x + MathUtils.cos(directionRad)*8;
        shapey[0] = y +MathUtils.sin(directionRad)*8;

        shapex[1] = x + MathUtils.cos(directionRad - 4*Pi/5) *8;
        shapey[1] = y + MathUtils.sin(directionRad - 4*Pi/5)*8;

        shapex[2] = x + MathUtils.cos(directionRad + Pi)*5;
        shapey[2] = y + MathUtils.sin(directionRad + Pi)*5;

        shapex[3] = x + MathUtils.cos(directionRad + 4*Pi/5)*8;
        shapey[3] = y + MathUtils.sin(directionRad + 4*Pi/5)*8;

    }

    public void update(float dt){
        //get magnitude of speed(pythag)
        vel = (float)Math.sqrt(dx*dx+dy*dy);
        //mirror to eacvh side
        if(x<0){
            x=cam.viewportWidth;
        }else if(x>cam.viewportWidth){
            x=0;
        }
        if(y<0){
            y=cam.viewportHeight;
        }else if(y>cam.viewportHeight){
            y=0;
        }
        //up is going forward
        if(left) {
            directionRad += rotationSpeed * dt;
        }
        else if(right) {
            directionRad -= rotationSpeed * dt;
        }

        // accelerating
        if(up) {
            dx += MathUtils.cos(directionRad) * acceleration * dt;
            dy += MathUtils.sin(directionRad) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if(vec > 0) {
            dx -= (dx / vec) * retardation * dt;
            dy -= (dy / vec) * retardation * dt;
        }
        if(vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position

        if(space){
            fire(x,y,directionRad);
        }

        if(shift){//not workings
            boostTimer+=dt;
            if(boostTimer<boostTime) {
                maxSpeed = 400;
                System.out.println("BOOST-ACTIVATED");
                dx += MathUtils.cos(directionRad) * 2;
                dy += MathUtils.sin(directionRad) * 2;
            }
            boostTimer = 0;
            System.out.println("BOOST-DE-ACTIVATED");
        } else{
            maxSpeed = 250;
        }


        x += dx * dt;
        y += dy * dt;
        //update bullets
        for(int i=0; i<bullets.size();i++){
            if(bullets.get(i).shouldRemove()){
                bullets.remove(i);
            } else {
                bullets.get(i).update(dt);
            }

        }
        setShape();
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(1,1,1,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for(int i = 0, j = shapex.length - 1;i<shapex.length;j=i++){
            sr.line(shapex[i],shapey[i],shapex[j],shapey[j]);
        }

        sr.end();
        //render bullets
        for(Bullet b: bullets){
            b.draw(sr);
        }

    }

    public void fire(float xsize,float ysize, float direction){
       if(bullets.size()==maxBullets){return;}
        else {
           bullets.add(new Bullet(xsize, ysize, direction,cam));
       }
    }
}
