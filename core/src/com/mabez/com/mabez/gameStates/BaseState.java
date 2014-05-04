package com.mabez.com.mabez.gameStates;

import com.mabez.managers.SceneManager;

/**
 * Created by user on 03/05/2014.
 */
public abstract class BaseState {
    private SceneManager sm;
    public BaseState(SceneManager sm){
        this.sm=sm;
        init();
    }

    public abstract void init();
    public abstract void render();
    public abstract void update(float dt);
    public abstract void HandleInput();
    public abstract void dispose();
}
