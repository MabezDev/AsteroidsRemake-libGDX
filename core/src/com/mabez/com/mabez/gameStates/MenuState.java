package com.mabez.com.mabez.gameStates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mabez.com.mabez.entities.Player;
import com.mabez.managers.MyKeys;
import com.mabez.managers.SceneManager;

/**
 * Created by user on 03/05/2014.
 */
public class MenuState extends BaseState {
    public MenuState(SceneManager sm) {
        super(sm);
    }
    private BitmapFont font;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private Player player;
    @Override
    public void init() {
        font = new BitmapFont();
        font.setColor(Color.GREEN);
        sb = new SpriteBatch();
        player = new Player();
        sr = new ShapeRenderer();
        player.setShape();


    }



    @Override
    public void render() {
        sb.begin();
        font.draw(sb,"Test",100,100);
        sb.end();
        player.draw(sr);
    }

    @Override
    public void update(float dt) {
        player.update(dt);
    }

    @Override
    public void HandleInput() {
        if(MyKeys.isDown(MyKeys.W)){
            font.setColor(Color.BLUE);
            System.out.println("W");
        } else {
            font.setColor(Color.GREEN);
        }
    }

    @Override
    public void dispose() {

    }
}


