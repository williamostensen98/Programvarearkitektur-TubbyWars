package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.badlogic.gdx.audio.Music;
import com.mygdx.tubby_wars.model.Assets;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.badlogic.gdx.audio.Sound;

public class MenuScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;

    //Initialize textures
    private Texture logo;
    private Texture background;
    private Texture playB;
    private Texture highScoreB;
    private Texture settingsB;

    private Button settingsButton;
    private Button highScoreButton;
    private Button gameButton;

    //Initialize  background music and sound effects
    private Music music;
    private Sound click;

    private Stage stage;
    private Engine engine;

    public MenuScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;


        background = Assets.getTexture(Assets.mainBackground);
        logo = Assets.getTexture(Assets.logo);
        playB = Assets.getTexture(Assets.gameScreenButton);
        highScoreB = Assets.getTexture(Assets.highScoreButton);
        settingsB = Assets.getTexture(Assets.settingScreenButton);

        this.music = game.getBackgroundMusic();
        this.game.playMusic(music);

        this.click = game.getClickSound();

        // one-time operations
        create();
    }

    public void create(){
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);



        makeButtons();


        stage.addActor(gameButton);
        stage.addActor(settingsButton);
        stage.addActor(highScoreButton);
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){

        game.getBatch().begin(); // Draw elements to Sprite Batch
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().draw(logo, Gdx.graphics.getWidth()/2f - Gdx.graphics.getWidth()/8f ,
                Gdx.graphics.getHeight()/2f,   Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/3f); //Draws logo
        game.getBatch().end();


        stage.draw();
    }

    @Override
    public void handleinput(){
    }

    @Override
    public void render(float dt){
        update(dt);
        draw();
    }

    @Override
    public void dispose(){

        super.dispose();

    }

    private void makeButtons() {
        //Initialize button to get GameScreen
        gameButton = new Button(new TextureRegionDrawable(new TextureRegion(playB)));
        gameButton.setSize( Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        gameButton.setPosition(Gdx.graphics.getWidth() / 2f - gameButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - gameButton.getHeight() / 2f);

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new CharacterCreationScreen(game));
            }
        });

        //Initialize button to get to SettingsScreen
        settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(  Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        settingsButton.setPosition(Gdx.graphics.getWidth()/4f - settingsButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - settingsButton.getHeight() / 2f);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new SettingScreen(game, engine));
            }
        });

        //Initialize button to get to HighScoreScreen
        highScoreButton = new Button(new TextureRegionDrawable(new TextureRegion(highScoreB)));
        highScoreButton.setSize( Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        highScoreButton.setPosition(Gdx.graphics.getWidth()/4f*3f - highScoreButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - highScoreButton.getHeight() / 2f);

        highScoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new HighScoreScreen(game, engine));
            }
        });

    }
}