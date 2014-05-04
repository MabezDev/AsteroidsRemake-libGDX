package com.mabez.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mabez.com.mabez.gameStates.BaseState;
import com.mabez.com.mabez.gameStates.MenuState;

/**
 * Created by user on 03/05/2014.
 */
public class SceneManager {

    protected static final int MENU = 0;
    protected static final int GAME = 1;
    protected static final int GG = 2;
    private static BaseState currentState;
    public OrthographicCamera cam;

    public SceneManager(OrthographicCamera cam) {
        this.cam = cam;
        setState(MENU);

    }

    public void setState(int i){
        if(i==MENU){
            currentState = new MenuState(this);
        }
    }

    public void update(float dt){
        currentState.update(dt);
        currentState.HandleInput();
    }
    public void draw(){
        currentState.render();
    }
}
