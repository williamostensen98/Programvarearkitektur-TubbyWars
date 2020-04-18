package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;
import com.badlogic.gdx.audio.Sound;

public class ShopScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;
    private Texture titleText;

    // Navigation buttons
    private Texture newGameB;
    private Texture settingsB;

    // Weapons buttons
    private Texture gun;
    private Texture rifle;
    private Texture revolver;

    private Sound click;

    //Buttons
    private Button newGameButton;
    private Button settingsButton;
    private Button newGun;
    private Button newRifle;
    private Button newRevolver;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        titleText = Assets.getTexture(Assets.shopTitle); //Title text for shop
        newGameB = Assets.getTexture(Assets.newGameButton); // resume to game button
        gun = Assets.getTexture(Assets.gunWeapon); // choose gun button
        rifle = Assets.getTexture(Assets.rifleWeapon); // choose rifle button
        revolver = Assets.getTexture(Assets.revolverWeapon); //choose revolver button
        settingsB = Assets.getTexture(Assets.pauseGameButton);

        click = game.getClickSound();

        // one-time operations
        create();
    }

    @Override
    public void create() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

       //Initialize title text image
        final Image title = new Image(titleText);
        title.setSize(150,75);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        //Initialize information text
        final Label infoText = new Label("Choose weapon:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        //infoText.setFontScale(1f,1f);
        infoText.setPosition(Gdx.graphics.getWidth() / 5f - infoText.getWidth()/2f , Gdx.graphics.getHeight() / 12f*8f);

        //Add objects to stage
        stage.addActor(title);
        stage.addActor(infoText);

        makeButtons();

        //Add buttons to stage
        stage.addActor(newGameButton);
        stage.addActor(settingsButton);
        stage.addActor(newGun);
        stage.addActor(newRifle);
        stage.addActor(newRevolver);
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(187.0f/255.0f, 231.0f/255.0f, 255.0f/255.0f, 1.0f);
        stage.draw();
    }

    @Override
    public void handleinput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new MenuScreen(game, engine));
        }
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
        newGameButton = new Button(new TextureRegionDrawable(new TextureRegion(newGameB)));
        newGameButton.setSize(100, 50);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f*5f - newGameButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                game.setScreen(new GameScreen(game, engine));
            }

        });

        //Initialize button to get to SettingsScreen
        settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(50, 50);
        settingsButton.setPosition(Gdx.graphics.getWidth()*85f/90f - settingsButton.getWidth() / 2f , Gdx.graphics.getHeight()* 75f/90f - settingsButton.getHeight() / 2f);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //game.playSound(click);
                game.setScreen(new SettingScreen(game, engine));
            }
        });

        //Initialize button to change weapon to gun
        newGun = new Button(new TextureRegionDrawable(new TextureRegion(gun)));
        newGun.setSize(150, 50);
        newGun.setPosition(Gdx.graphics.getWidth()/3f - newGun.getWidth() , Gdx.graphics.getHeight() / 2f - newGun.getHeight() / 2f);
        newGun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect TODO: hva skal sje når vi trykker på knappen?
                game.playSound(click);
            }
        });

        //Initialize button to change weapon to rifle
        newRifle = new Button(new TextureRegionDrawable(new TextureRegion(rifle)));
        newRifle.setSize(150, 50);
        newRifle.setPosition(Gdx.graphics.getWidth() / 2f - newRifle.getWidth()/2f , Gdx.graphics.getHeight() /2f - newRifle.getHeight() / 2f);
        newRifle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
            }
        });

        //Initialize button to change weapon to Revolver
        newRevolver = new Button(new TextureRegionDrawable(new TextureRegion(revolver)));
        newRevolver.setSize(150, 50);
        newRevolver.setPosition(Gdx.graphics.getWidth() / 3f*2f , Gdx.graphics.getHeight() / 2f - newRevolver.getHeight() / 2f);
        newRevolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
            }
        });
    }

}

