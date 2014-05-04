package com.mabez.com.mabez.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by user on 04/05/2014.
 */
public class Player extends SpaceObject {


    private boolean left;
    private boolean right;
    private boolean up;
    //private ShapeRenderer sr;

    private float maxSpeed;
    private float accleration;
    private float retardation;
    private static final float Pi = 3.14f;

    public Player() {
        up=true;
        y = 200;
        x = 200;
        dy=0;
        dx=0;
        maxSpeed = 250;
        accleration = 150;
        retardation = 10;
        shapex = new float[4];
        shapey = new float[4];
    }

    public void setShape(){
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
        if(up){
           dx+= MathUtils.cos(directionRad) *accleration*dt;
           dy += MathUtils.sin(directionRad)*accleration*dt;
        }
        setShape();
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(1,1,1,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for(int i = 0, j = shapex.length -1;i<shapex.length;j=i++){
            sr.line(shapex[i],shapey[i],shapex[j],shapey[j]);
        }
        sr.end();

    }
}
