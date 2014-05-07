package com.mabez.com.mabez.gameStates;

import com.mabez.managers.ResourceManager;
import com.mabez.managers.SceneManager;


/**
 * Created by user on 03/05/2014.
 */
public abstract class BaseState {
    protected SceneManager sm;
    protected ResourceManager resManager;
    public BaseState(SceneManager sm){
        this.sm=sm;
        this.resManager = ResourceManager.getInstance();
        init();
    }

    public abstract void init();
    public abstract void draw();
    public abstract void update(float dt);
    public abstract void HandleInput();
    public abstract void dispose();
}
