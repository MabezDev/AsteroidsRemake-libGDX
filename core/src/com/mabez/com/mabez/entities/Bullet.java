package com.mabez.com.mabez.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by user on 04/05/2014.
 */
public class Bullet extends SpaceObject {

    private float x;
    private float y;
    private float directionRad;

    private float lifeTime;
    private float lifeTimer;

    private int width;
    private int height;

    private boolean remove;
    private OrthographicCamera cam;

    private float maxSpeed;



    public Bullet(float x, float y, float direction,OrthographicCamera cam) {
        this.cam = cam;
        this.x=x;
        this.y=y;
        this.directionRad = direction;
        width = 2;
        height = 2;
        lifeTime = 1;
        lifeTimer = 0;
        dx = 0;
        dy=0;
        maxSpeed = 350;

    }

    public boolean shouldRemove(){return remove;}

    public void update(float dt){

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
        float vel = (float) Math.sqrt(dx*dx+dy*dy);
        if(vel<maxSpeed) {
            dx += MathUtils.cos(directionRad) * 100;
            dy += MathUtils.sin(directionRad) * 100;
            x += dx * dt;
            y += dy * dt;
        } else{
            dx = (dx/vel)*maxSpeed;
            dy = (dy/vel)*maxSpeed;
            x+=dx*dt;
            y+=dy*dt;
        }



        lifeTimer+=dt;
        if(lifeTimer>lifeTime){
            remove=true;
        }

    }

    public void draw(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x,y,width,height);
        sr.end();

    }
}
