package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Assets {
    //Backgrounds
    public static String mainBackground = "textures/background.png"; //Background used outside gameplay
    public static String settingsBackground = "textures/settingsBackground.png"; //Background used outside gameplay
    public static String characterBackground = "textures/characterBackground.png"; //Background used in CharacherCreationScreen
    public static String shopBackground = "textures/shopBackground.png"; //Background used in shopScreen
    public static String highscoreBackground = "textures/highscoreBackground.png"; //Background used in HighScoreScreen

    //TextField
    public static String textFieldBackground = "textures/textfield.png"; //Used to make TextField visible

    //Buttons in MenuScreen
    public static String highScoreButton = "textures/highscoreButton.png"; //Go to HighScoreScreen
    public static String settingScreenButton = "textures/settingsButton.png"; //Go to SettingScreen
    public static String gameScreenButton = "textures/play.png"; //Go to Game screen

    //Buttons for navigation
    public static String menuScreenButton = "textures/backButton.png"; //Used in SettingsScreen and HighScoreScreen to go to MenuScreen
    public static String newGameButton = "textures/playGameButton.png"; // Used in HighscoreScreen to go to ShopScreen
    public static String pauseGameButton = "textures/pauseButton.png"; //Used in GameScreen to go to SettingScreen
    public static String settingSignButton = "textures/innstillingerButton.png"; //Used in GameScreen to go to SettingScreen
    public static String continueGameButton = "textures/continueGameButton.png"; //Used in SettingScreen to go to GameScreen
    public static String quitGameButton = "textures/quitGameButton.png"; //Used in SettingScreen to go to MenuScreen

    //Sound buttons
    public static String soundOnButton = "textures/soundOn.png";
    public static String soundOffButton = "textures/soundOff.png";

    //Text titles for pages
    public static String logo = "textures/logo.png"; //Logo for game
    public static String settingsTitle =  "textures/settings.png"; //Title for settings page
    public static String shopTitle =  "textures/shop.png"; //Title for shop page
    public static String highscoreTitle =  "textures/HighscoreLogo.png"; //Title for highscore page
    public static String characterTitle = "textures/characterLogo.png"; //"Registrer brukernavn"

    //Sprites
    public static String gulTubby = "textures/gulTubby.png";
    public static String gronnTubby = "textures/gronnTubby.png";
    public static String rodTubby = "textures/rodTubby.png";
    public static String lillaTubby = "textures/lillaTubby.png";

    //Weapons
    public static String gunWeapon = "textures/gun.png";
    public static String rifleWeapon = "textures/Rifle.png";
    public static String revolverWeapon = "textures/revolver.png";

    //Music and sounds TODO: Implement in game
    public static String backgroundMusic = "music/music.mp3";
    public static String shootingSound = "music/Shotgun-sound.mp3"; //When shooting weapon
    public static String hitSound = "music/Cartoon-game-ending.mp3"; //When player is hit by opponent
    public static String clickSound = "music/Mouse-click-sound.mp3"; //When clicking on button

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
        assetManager.load(gunWeapon, Texture.class);
        assetManager.load(rifleWeapon, Texture.class);
        assetManager.load(revolverWeapon, Texture.class);

        //Music and sounds
        assetManager.load(backgroundMusic, Music.class);
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
