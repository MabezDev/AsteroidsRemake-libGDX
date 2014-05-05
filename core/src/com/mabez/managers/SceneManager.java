package com.mabez.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mabez.com.mabez.gameStates.BaseState;
import com.mabez.com.mabez.gameStates.GameState;
import com.mabez.com.mabez.gameStates.MenuState;

/**
 * Created by user on 03/05/2014.
 */
public class SceneManager {

    public static final int MENU = 0;
    public static final int GAME = 1;
    public static final int GG = 2;
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
        if(i==GAME){
            currentState = new GameState(this);
        }
    }

    public void update(float dt){
        currentState.update(dt);
        currentState.HandleInput();
    }
    public void draw(){
        currentState.draw();
    }
}
