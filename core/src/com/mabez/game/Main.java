package com.mabez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.managers.MyInputProccesor;
import com.mabez.managers.MyKeys;
import com.mabez.managers.SceneManager;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private SceneManager sm;
    private OrthographicCamera cam;
    public static int camHeight;
    public static int camWidth;

    public Main(int height,int width) {
        camWidth=width;
        camHeight=height;
    }

    @Override
	public void create () {
	    cam = new OrthographicCamera(camWidth,camHeight);
        Gdx.input.setInputProcessor(new MyInputProccesor());
        sm = new SceneManager(cam);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sm.update(Gdx.graphics.getDeltaTime());
        sm.draw();
        MyKeys.update();
	}
}
