package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Assets {


    //Backgrounds
    public static String mainBackground = "textures/backgrounds/background.png"; //Background used outside gameplay
    public static String settingsBackground = "textures/backgrounds/settingsBackground.png"; //Background used outside gameplay
    public static String characterBackground = "textures/backgrounds/characterBackground.png"; //Background used in CharacherCreationScreen
    public static String shopBackground = "textures/backgrounds/shopBackground.png"; //Background used in shopScreen
    public static String highscoreBackground = "textures/backgrounds/highscoreBackground.png"; //Background used in HighScoreScreen

    //TextField
    public static String textFieldBackground = "textures/backgrounds/textfield.png"; //Used to make TextField visible

    //Buttons in MenuScreen
    public static String highScoreButton = "textures/buttons/highscoreButton.png"; //Go to HighScoreScreen
    public static String settingScreenButton = "textures/buttons/settingsButton.png"; //Go to SettingScreen
    public static String gameScreenButton = "textures/buttons/play.png"; //Go to Game screen

    //Buttons for navigation
    public static String menuScreenButton = "textures/buttons/backButton.png"; //Used in SettingsScreen and HighScoreScreen to go to MenuScreen
    public static String newGameButton = "textures/buttons/playGameButton.png"; // Used in HighscoreScreen to go to ShopScreen
    public static String pauseGameButton = "textures/buttons/pauseButton.png"; //Used in GameScreen to go to SettingScreen
    public static String settingSignButton = "textures/buttons/innstillingerButton.png"; //Used in GameScreen to go to SettingScreen
    public static String continueGameButton = "textures/buttons/continueGameButton.png"; //Used in SettingScreen to go to GameScreen
    public static String quitGameButton = "textures/buttons/quitGameButton.png"; //Used in SettingScreen to go to MenuScreen

    //Sound buttons
    public static String soundOnButton = "textures/buttons/soundOn.png";
    public static String soundOffButton = "textures/buttons/soundOff.png";

    //Text titles for pages
    public static String logo = "textures/logos/logo.png"; //Logo for game
    public static String settingsTitle =  "textures/logos/settings.png"; //Title for settings page
    public static String shopTitle =  "textures/logos/shop.png"; //Title for shop page
    public static String highscoreTitle =  "textures/logos/HighscoreLogo.png"; //Title for highscore page
    public static String characterTitle = "textures/logos/characterLogo.png"; //"Registrer brukernavn"

    //Sprites
    public static String gulTubby = "textures/tubbies/gulTubby.png";
    public static String gronnTubby = "textures/tubbies/gronnTubby.png";
    public static String rodTubby = "textures/tubbies/rodTubby.png";
    public static String lillaTubby = "textures/tubbies/lillaTubby.png";

    //Weapons
    public static String revolverWeapon = "textures/weapons/revolver.png";
    public static String gunSheet = "textures/weapons/GunsSpriteSheet.png";
    public static String bullet = "textures/weapons/explosions.png";

    //Music and sounds
    public static String backgroundMusic = "music/music.mp3";
    public static String shootingSound = "music/Shotgun-sound.mp3"; //When shooting weapon
    public static String hitSound = "music/Cartoon-game-ending.mp3"; //When player is hit by opponent
    public static String clickSound = "music/Mouse-click-sound.mp3"; //When clicking on button

    //Maps
    public static String map1 = "maps/tubbymap3.tmx";
    public static String map2 = "maps/tubbymap2.tmx";
    public static String map3 = "maps/tubbymap1.tmx";
    public static String map4 = "maps/tubbymap5.tmx";
    public static String map5 = "maps/tubbymap4.tmx";

    public static String trajectory = "textures/weapons/white-circle.png";

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
        assetManager.load(settingsBackground, Texture.class);
        assetManager.load(characterBackground, Texture.class);
        assetManager.load(shopBackground, Texture.class);
        assetManager.load(highscoreBackground, Texture.class);

        //TextField
        assetManager.load(textFieldBackground, Texture.class);

        //Buttons in MenuScreen
        assetManager.load(highScoreButton, Texture.class);
        assetManager.load(settingScreenButton, Texture.class);
        assetManager.load(gameScreenButton, Texture.class);

        //Buttons for navigation
        assetManager.load(menuScreenButton, Texture.class);
        assetManager.load(pauseGameButton, Texture.class);
        assetManager.load(settingSignButton, Texture.class);
        assetManager.load(newGameButton, Texture.class);
        assetManager.load(continueGameButton, Texture.class);
        assetManager.load(quitGameButton, Texture.class);

        //Sound buttons
        assetManager.load(soundOnButton, Texture.class);
        assetManager.load(soundOffButton, Texture.class);

        //Text titles for pages
        assetManager.load(logo, Texture.class);
        assetManager.load(shopTitle, Texture.class);
        assetManager.load(settingsTitle, Texture.class);
        assetManager.load(highscoreTitle, Texture.class);
        assetManager.load(characterTitle, Texture.class);

        //Sprites
        assetManager.load(gulTubby, Texture.class);
        assetManager.load(gronnTubby, Texture.class);
        assetManager.load(rodTubby, Texture.class);
        assetManager.load(lillaTubby, Texture.class);

        //Weapons
        assetManager.load(revolverWeapon, Texture.class);
        assetManager.load(gunSheet, Texture.class);
        assetManager.load(bullet, Texture.class);

        //Music and sounds
        assetManager.load(backgroundMusic, Music.class);
        assetManager.load(shootingSound, Sound.class);
        assetManager.load(hitSound, Sound.class);
        assetManager.load(clickSound, Sound.class);

        assetManager.load(trajectory, Texture.class);

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
    public static Sound getSound(String path) {
        return assetManager.get(path, Sound.class);
    }

    //Getter for sound effects
    public static TiledMap getMap(String path) {return assetManager.get(path, TiledMap.class);}
}
