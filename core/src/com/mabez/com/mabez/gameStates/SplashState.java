package com.mabez.com.mabez.gameStates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.managers.SceneManager;

/**
 * Created by user on 18/05/2014.
 */
public class SplashState extends BaseState {

    private float TimeIncrementer;
    private static final int TIMER = 3;

    private BitmapFont font1;
    private SpriteBatch sb;


    public SplashState(SceneManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        font1 = new BitmapFont();
        font1.setColor(Color.WHITE);
        sb = new SpriteBatch();
    }

    @Override
    public void draw() {
        sb.begin();
        font1.draw(sb,"MABEZ-DEV",sm.cam.viewportWidth/2- font1.getBounds("MABEZ-DEV").width/2,sm.cam.viewportHeight/2);
        sb.end();
    }

    @Override
    public void update(float dt) {
        TimeIncrementer +=dt;
        if(TIMER<TimeIncrementer){
            sm.setState(SceneManager.MENU);
        }

    }

    @Override
    public void HandleInput() {

    }

    @Override
    public void dispose() {

    }
}
