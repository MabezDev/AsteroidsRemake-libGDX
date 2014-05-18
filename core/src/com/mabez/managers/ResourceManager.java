package com.mabez.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 07/05/2014.
 */
public class ResourceManager {

    private static final String FONT_CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    private BitmapFont font;

    private HashMap<String,Sound> sounds;

    private static final ResourceManager INSTANCE = new ResourceManager();

    public ResourceManager(){
        sounds = new HashMap<String, Sound>();
    }//HaspMap of Sounds

    public void loadSound(String path,String Key){//loads the sound into memory
        Sound s = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(Key,s);
    }

    public void unLoadSound(String Key){//unload sound from memory
        Sound s = sounds.get(Key);
        if(s!= null){
            s.dispose();
        }
        sounds.remove(Key);
    }

    public Sound getSound(String Key){//returns the sound file
        Sound s = sounds.get(Key);
        return s;
    }

    public BitmapFont TTFLoader(String TTFPATH){

        //font = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal(TTFPATH), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return font;

    }

    public static ResourceManager getInstance()//returns the ResourceManager in its current state
    {
        return INSTANCE;
    }



}
