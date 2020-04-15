package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Assets {
    //Backgrounds
    public static String mainBackground = "textures/background.jpg"; //Background used outside gameplay
    public static String gameBackground = "textures/background.jpg"; //Background used in gameplay TODO: Legg inn her og bruk

    //Screen Buttons TODO: Make for all screens
    public static String playButton = "textures/play.png"; //Go to Game screen
    public static String newGameButton = "textures/play.png"; //Go to Game screen
    public static String highScoreButton = "textures/ButtonHighscore.png"; //Go to HighScore screen
    public static String menuScreenButton = "textures/ButtonStart.png"; //Go to Menu screen
    public static String shopScreenButton = "textures/shop.png";
    public static String settingsButton = "textures/ButtonStart.png"; //Go to Settings screen

    //Navigation buttons
    public static String backButton = "textures/back.png"; //Go back to former screen
    public static String resumeGameButton = "textures/play.png"; //Used in SettingsScreen for continuing to play game
    public static String quitGameButton = "textures/back.png"; //Used in SettingsScreen for continuing to quit game (go to menu)

    //Sound buttons
    public static String soundOnButton = "textures/soundOn.png";
    public static String soundOffButton = "textures/soundOff.png";

    //Text titles for pages TODO: Make desings
    public static String logo = "textures/Logo.png"; //Logo for game
    public static String settingsTitle =  "textures/settings.png"; //Title for settings page
    public static String shopTitle =  "textures/shop.png"; //Title for shop page
    public static String highscoreTitle =  "textures/settings.png"; //Title for highscore page

    //Obstacles
    public static String cloudObstacle = "textures/cloud.png";
    public static String treeObstacle = "textures/tree.png";
    public static String wallObstacle = "textures/wall.png";

    //Weapons
    public static String gunWeapon = "textures/gun.png";
    public static String rifleWeapon = "textures/Rifle.png";
    public static String revolverWeapon = "textures/revolver.png";

    //Music and sounds TODO: Implement in game
    public static String backgroundMusic = "music/music.mp3";
    public static String jumpingSound = "music/Jumping-sound-effect.mp3";
    public static String shootingSound = "music/Shotgun-sound.mp3"; //When shooting weapon
    public static String hitSound = "music/Cartoon-game-ending.mp3"; //When player is hit by opponent
    public static String clickSound = "music/Mouse-click-sound.mp3"; //When clicking on button TODO: Add in game screen

    private static AssetManager assetManager;

    public Assets(){
        assetManager = new AssetManager();
    }

    public static void dispose(){
        assetManager.dispose();
    }

    public static void load(){
        //Backgrounds
        assetManager.load(mainBackground, Texture.class);
        assetManager.load(gameBackground, Texture.class);

        //Screen Buttons
        assetManager.load(playButton, Texture.class);
        assetManager.load(newGameButton, Texture.class);
        assetManager.load(highScoreButton, Texture.class);
        assetManager.load(menuScreenButton, Texture.class);
        assetManager.load(shopScreenButton, Texture.class);
        assetManager.load(settingsButton, Texture.class);

        //Navigation buttons
        assetManager.load(backButton, Texture.class);
        assetManager.load(resumeGameButton, Texture.class);
        assetManager.load(quitGameButton, Texture.class);

        //Sound buttons
        assetManager.load(soundOnButton, Texture.class);
        assetManager.load(soundOffButton, Texture.class);

        //Text titles for pages
        assetManager.load(logo, Texture.class);
        assetManager.load(shopTitle, Texture.class);
        assetManager.load(settingsTitle, Texture.class);
        assetManager.load(highscoreTitle, Texture.class);

        //Obstacles
        assetManager.load(cloudObstacle, Texture.class);
        assetManager.load(treeObstacle, Texture.class);
        assetManager.load(wallObstacle, Texture.class);

        //Weapons
        assetManager.load(gunWeapon, Texture.class);
        assetManager.load(rifleWeapon, Texture.class);
        assetManager.load(revolverWeapon, Texture.class);

        //Music and sounds
        assetManager.load(backgroundMusic, Music.class);
        assetManager.load(jumpingSound, Sound.class);
        assetManager.load(shootingSound, Sound.class);
        assetManager.load(hitSound, Sound.class);
        assetManager.load(clickSound, Sound.class);
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

    //Getter for sound effects
    public  static Sound getSound(String path) {
        return assetManager.get(path, Sound.class);
    }
}
