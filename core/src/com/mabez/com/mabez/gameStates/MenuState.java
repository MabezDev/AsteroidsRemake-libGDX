package com.mabez.com.mabez.gameStates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mabez.com.mabez.entities.Player;
import com.mabez.managers.MyKeys;
import com.mabez.managers.SceneManager;

import java.awt.*;

/**
 * Created by user on 03/05/2014.
 */
public class MenuState extends BaseState {

    private BitmapFont font;

    private SpriteBatch sb;

    public MenuState(SceneManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        font= new BitmapFont();
        sb = new SpriteBatch();
        font.setColor(1,1,1,1);
        font.setScale(3,3);


    }



    @Override
    public void draw() {
        sb.begin();
        font.draw(sb,"ASTEROIDS",sm.cam.viewportWidth/2-font.getBounds("ASTEROIDS").width/2,sm.cam.viewportHeight/2-font.getBounds("ASTEROIDS").height+ 150);

        //font.draw(sb,"Play",sm.cam.viewportWidth/2-font.getBounds("Play").width/2,sm.cam.viewportHeight/2-font.getBounds("Play").height+ 50);
        sb.end();
    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void HandleInput() {
        if(MyKeys.isDown(MyKeys.SPACE)){
            sm.setState(sm.GAME);
        }

    }

    @Override
    public void dispose() {

    }
}


