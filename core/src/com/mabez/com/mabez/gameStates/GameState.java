package com.mabez.com.mabez.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

    private static final int maxBullets = 4;
    private ShapeRenderer sr;
    private BitmapFont font;
    private SpriteBatch sb;
    private boolean isPaused;

    private Player player;
    private ArrayList<Asteroid> asteroids;
    public ArrayList<Bullet> bullets;
    private static int  NUM_ASTEROIDS = 4;
    private boolean EscapeToggle = false;
    private Sound bulletNoise;
    private Sound thruster;

    @Override

    public void init() {
        isPaused=false;


        resManager.loadSound("sounds/bounce.ogg", "bullet");
        resManager.loadSound("sounds/flame.wav","flame");
        bulletNoise = resManager.getSound("bullet");//sound for bullets, pretty good Imo
        thruster = resManager.getSound("flame");//sound for thrusters, need work because it sounds retarded


        font = new BitmapFont();
        font.setColor(Color.WHITE);

        sb =  new SpriteBatch();


        player = new Player(sm.cam,sm);
        asteroids = new ArrayList<Asteroid>();
        bullets = new ArrayList<Bullet>();

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

        for(Bullet b: bullets){
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


            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).shouldRemove()) {
                    bullets.remove(i);
                } else {
                    bullets.get(i).update(dt);
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
        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<asteroids.size();j++){
                Bullet b = bullets.get(i);
                Asteroid a = asteroids.get(j);
                float xa= a.getX();
                float ya= a.getY();
                float xb= b.getX();
                float yb = b.getY();
                if(contains(xa,ya,xb,yb)){

                    //bullets.remove(i);
                    asteroids.remove(j);

                }

            }
        }
    }

    private boolean contains(float asteroidx,float asteroidy,float bulletx, float bullety) {//need to sorth out collision
        boolean b = false;
        if ((bulletx > asteroidx && bulletx < asteroidx + 40) && (bullety > asteroidy && bullety < asteroidy + 40)){
            b = true;
        }
        return b;
    }

    public void fire(float x,float y, float direction){
        if(bullets.size()== maxBullets){
            return;
        } else {
            bullets.add(new Bullet(x, y, direction,sm.cam));
            bulletNoise.play(0.08f);


        }
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
        if (MyKeys.isPressed(MyKeys.SPACE)) {
            fire(player.getX(), player.getY(), player.getDirectionRad());
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
        resManager.unLoadSound("flame");
        resManager.unLoadSound("bullet");
    }
}




