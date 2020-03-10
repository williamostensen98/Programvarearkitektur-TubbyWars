package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    // alle textures legges inn her på denne måten.
    // nb, vi må huske å ha fin mappestruktur i Android/assets

    public static String test = "badlogic.jpg";


    private static AssetManager assetManager;

    public Assets(){
        assetManager = new AssetManager();
    }

    public static void dispose(){
        assetManager.dispose();
    }

    public static void load(){
        // her skal alle textures inn! dette er bare en foreløbig test
        assetManager.load(test, Texture.class);
    }

    public static float getProgress(){
        return assetManager.getProgress();
    }

    public static boolean update(){
        return assetManager.update();
    }

    public static Texture getTexture(String tex){
        return assetManager.get(tex, Texture.class);
    }

    // må også ha get sound og music når det blir lagt inn.

}
