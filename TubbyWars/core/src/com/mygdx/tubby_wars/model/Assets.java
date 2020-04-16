package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class Assets {

    // alle textures legges inn her på denne måten.
    // nb, vi må huske å ha fin mappestruktur i Android/assets
    public static String logo = "textures/logo.png"; //Logo for game
    public static String mainBackground = "textures/background.png"; //Background used outside gameplay
    public static String highScoreButton = "textures/highscoreButton.png"; //Go to HighScore screen
    public static String menuScreenButton = "textures/ButtonStart.png"; //Go to Menu screen
    public static String shopScreenButton = "textures/shop.png";
    public static String settingsButton = "textures/settingsButton.png"; //Go to Settings screen
    public static String playButton = "textures/play.png"; //Go to Game screen
    public static String backButton = "textures/back.png"; //Go back to former screen
    public static String soundOnButton = "textures/soundOn.png";
    public static String soundOffButton = "textures/soundOff.png";
    public static String resumeGameButton = "textures/play.png"; //Used in SettingsScreen for continuing to play game
    public static String quitGameButton = "textures/back.png"; //Used in SettingsScreen for continuing to quit game (go to menu)
    public static String cloudObstacle = "textures/cloud.png";
    public static String treeObstacle = "textures/tree.png";
    public static String wallObstacle = "textures/wall.png";
    public static String gunWeapon = "textures/gun.png";
    public static String rifleWeapon = "textures/Rifle.png";
    public static String revolverWeapon = "textures/revolver.png";
    public static String shop = "textures/shop.png";

    public static String backgroundMusic = "music/music.mp3";

    private static AssetManager assetManager;

    public Assets(){
        assetManager = new AssetManager();
    }

    public static void dispose(){
        assetManager.dispose();
    }

    public static void load(){
        // her skal alle textures inn!
        assetManager.load(logo, Texture.class);
        assetManager.load(mainBackground, Texture.class);
        assetManager.load(highScoreButton, Texture.class);
        assetManager.load(menuScreenButton, Texture.class);
        assetManager.load(shopScreenButton, Texture.class);
        assetManager.load(playButton, Texture.class);
        assetManager.load(backButton, Texture.class);
        assetManager.load(settingsButton, Texture.class);
        assetManager.load(soundOnButton, Texture.class);
        assetManager.load(soundOffButton, Texture.class);
        assetManager.load(resumeGameButton, Texture.class);
        assetManager.load(quitGameButton, Texture.class);
        assetManager.load(cloudObstacle, Texture.class);
        assetManager.load(treeObstacle, Texture.class);
        assetManager.load(wallObstacle, Texture.class);
        assetManager.load(gunWeapon, Texture.class);
        assetManager.load(rifleWeapon, Texture.class);
        assetManager.load(revolverWeapon, Texture.class);
        assetManager.load(shop, Texture.class);

        assetManager.load(backgroundMusic, Music.class);
    }

    public static boolean update(){
        return assetManager.update();
    }

    public static float getProgress(){
        return assetManager.getProgress();
    }

    public static Texture getTexture(String tex){
        return assetManager.get(tex, Texture.class);
    }

    //Getter for music
    public static Music getMusic(String path) { return assetManager.get(path, Music.class); }

    // må også ha get sound når det blir lagt inn.
}
