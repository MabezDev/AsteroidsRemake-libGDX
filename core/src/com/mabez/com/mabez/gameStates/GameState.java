package com.mabez.com.mabez.gameStates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mabez.com.mabez.entities.Asteroid;
import com.mabez.com.mabez.entities.Bullet;
import com.mabez.com.mabez.entities.Player;
import com.mabez.managers.MyKeys;
import com.mabez.managers.SceneManager;

import java.util.ArrayList;

public class GameState extends BaseState {
    public GameState(SceneManager sm) {
        super(sm);
    }
    private ShapeRenderer sr;
    private BitmapFont font;
    private SpriteBatch sb;
    private boolean isPaused;

    private Player player;
    private ArrayList<Asteroid> asteroids;
    private static int  NUM_ASTEROIDS = 4;
    private boolean EscapeToggle = false;
    @Override

    public void init() {
        isPaused=false;

        font = new BitmapFont();
        font.setColor(Color.WHITE);

        sb =  new SpriteBatch();


        player = new Player(sm.cam);
        asteroids = new ArrayList<Asteroid>();
        sr = new ShapeRenderer();
        player.setShape();
    }

       public void Pause(){
           isPaused = true;

       }

        public void Resume(){
            isPaused = false;
        }


    @Override
    public void draw() {

        player.draw(sr);

        for(Bullet b: player.bullets){
            b.draw(sr);
        }

        for(int i =0; i<asteroids.size();i++){
            asteroids.get(i).draw(sr);
        }
        if(isPaused){
            sb.begin();
            font.draw(sb,"Paused",sm.cam.viewportWidth/2-(float)font.getBounds("Paused").width/2,
                    sm.cam.viewportHeight/2-font.getBounds("Paused").height/2);
            sb.end();
        }

    }

    @Override
    public void update(float dt) {

        if(isPaused==false) {
            player.update(dt);


            for (int i = 0; i < asteroids.size(); i++) {
                asteroids.get(i).update(dt);
            }

            for (int i = 0; i < player.bullets.size(); i++) {
                if (player.bullets.get(i).shouldRemove()) {
                    player.bullets.remove(i);
                } else {
                    player.bullets.get(i).update(dt);
                }

            }


            if (asteroids.size() == NUM_ASTEROIDS) {

            } else {
                asteroids.add(new Asteroid(sm.cam));
            }

            checkCollisions();
        }
    }

    private void checkCollisions(){
        for(int i=0;i<player.bullets.size();i++){
            for(int j=0;j<asteroids.size();j++){
                Bullet b = player.bullets.get(i);
                Asteroid a = asteroids.get(j);
                float xa= a.getX();
                float ya= a.getY();
                float xb= b.getX();
                float yb = b.getY();
                contains(xa,ya,xb,yb);

            }
        }
    }

    private boolean contains(float xa,float ya,float xb, float yb){//need to sorth out collision
        boolean b = false;

        return b;
    }




    @Override
    public void HandleInput() {
        if (MyKeys.isDown(MyKeys.W)) {
            player.up = true;
        } else {
            player.up = false;
        }
        if (MyKeys.isDown(MyKeys.A)) {
            player.left = true;
        } else {
            player.left = false;
        }
        if (MyKeys.isDown(MyKeys.D)) {
            player.right = true;
        } else {
            player.right = false;
        }
        if (MyKeys.isDown(MyKeys.SPACE)) {
            player.space = true;
        } else {
            player.space = false;
        }
        if (MyKeys.isPressed(MyKeys.SHIFT)) {
            player.shift = true;
        } else {
            player.shift = false;
        }
        if (MyKeys.isPressed(MyKeys.ESCAPE)) {
            EscapeToggle = !EscapeToggle;


            if (EscapeToggle) {
                Pause();
            } else{
                Resume();
            }

        }
    }

    @Override
    public void dispose() {

    }
}




