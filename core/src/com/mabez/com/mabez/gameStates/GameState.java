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
public class GameState extends BaseState {
    public GameState(SceneManager sm) {
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
        player = new Player(sm.cam);
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
            player.up=true;
        } else {
            player.up=false;
        }
        if(MyKeys.isDown(MyKeys.A)){
            player.left=true;
        } else {
            player.left=false;
        }
        if(MyKeys.isDown(MyKeys.D)){
            player.right=true;
        } else {
            player.right=false;
        }
        if(MyKeys.isDown(MyKeys.SPACE)){
            player.space=true;
        } else {
            player.space=false;
        }
    }

    @Override
    public void dispose() {

    }
}


