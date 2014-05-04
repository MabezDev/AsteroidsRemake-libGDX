package com.mabez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.managers.MyInputProccesor;
import com.mabez.managers.MyKeys;
import com.mabez.managers.SceneManager;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private SceneManager sm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
        Gdx.input.setInputProcessor(new MyInputProccesor());
        sm = new SceneManager();
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
