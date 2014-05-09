package com.mabez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mabez.managers.AndroidInputProcessor;
import com.mabez.managers.MyInputProccesor;
import com.mabez.managers.MyKeys;
import com.mabez.managers.SceneManager;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private SceneManager sm;

    private static float accelX;
    private static float accelY;
    private static float accelZ;

    private OrthographicCamera cam;
    public float camHeight;
    public float camWidth;
    private String Device;

    public Main(String Device) {
        camWidth = 720;//Gdx.graphics.getWidth();
        camHeight = 480;//Gdx.graphics.getHeight();
        this.Device = Device;
    }

    @Override
	public void create () {
	    cam = new OrthographicCamera(camWidth,camHeight);
        Gdx.input.setInputProcessor(new MyInputProccesor());
        if(Device.equals("_desktop")){
            System.out.println("Gathering Input From: DESKTOP");
            Gdx.input.setInputProcessor(new MyInputProccesor());
        } else if(Device.equals("_android")){
            System.out.println("Gathering Input From: ANDROID");
            Gdx.input.setInputProcessor(new AndroidInputProcessor());
        }

        sm = new SceneManager(cam,Device);


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sm.update(Gdx.graphics.getDeltaTime());
        sm.draw();
        MyKeys.update();

        if(Device.equals("_android")) {
            HandleAcceleromter();
        }

	}

    public static void HandleAcceleromter(){
        float orentation = Gdx.input.getRotation();
        System.out.println("NATIVE-OREINTATION: " + Float.toString(orentation));
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();
        accelZ = Gdx.input.getAccelerometerZ();
        System.out.println("ACCEL-X: "+Float.toString(accelX));
        System.out.println("ACCEL-Y: "+Float.toString(accelY));
        System.out.println("ACCEL-Z: "+Float.toString(accelZ));



    }
}
