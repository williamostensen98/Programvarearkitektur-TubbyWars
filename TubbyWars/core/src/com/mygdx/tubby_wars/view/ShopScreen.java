package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.tubby_wars.model.ControllerLogic;

public class ShopScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;
    private Texture titleText;

    private Texture background;

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

    private Button player1gun;
    private Button player1revolver;
    private Button player1rifle;

    private Button player2gun;
    private Button player2revolver;
    private Button player2rifle;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.shopBackground);
        titleText = Assets.getTexture(Assets.shopTitle); //Title text for shop
        newGameB = Assets.getTexture(Assets.newGameButton); // resume to game button
        gun = Assets.getTexture(Assets.gunWeapon); // choose gun button
        rifle = Assets.getTexture(Assets.rifleWeapon); // choose rifle button
        revolver = Assets.getTexture(Assets.revolverWeapon); //choose revolver button
        settingsB = Assets.getTexture(Assets.settingSignButton);

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
        title.setSize(Gdx.graphics.getWidth()/7f,Gdx.graphics.getHeight()/5f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        //Initialize information text
        final Label player1Text = new Label("Player 1's weapon:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        //infoText.setFontScale(1f,1f);
        player1Text.setPosition(Gdx.graphics.getWidth() / 7f - player1Text.getWidth()/2f , Gdx.graphics.getHeight() / 13f*9f - player1Text.getHeight() / 2f);

        //Initialize information text
        final Label player2Text = new Label("Player 2's weapon:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        //infoText.setFontScale(1f,1f);
        player2Text.setPosition(Gdx.graphics.getWidth() / 7f - player2Text.getWidth()/2f , Gdx.graphics.getHeight() /100f*41f);

        //Add objects to stage
        stage.addActor(title);
        stage.addActor(player1Text);
        stage.addActor(player2Text);

        makeButtons();

        //Add buttons to stage
        stage.addActor(newGameButton);
        stage.addActor(settingsButton);
        stage.addActor(player1gun);
        stage.addActor(player1rifle);
        stage.addActor(player1revolver);
        stage.addActor(player2gun);
        stage.addActor(player2revolver);
        stage.addActor(player2rifle);
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().end();

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
        newGameButton.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/10f);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                game.setScreen(ControllerLogic.currentGame);
            }

        });

        //Initialize button to get to SettingsScreen
        settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(Gdx.graphics.getWidth()/24f, Gdx.graphics.getHeight()/13f);
        settingsButton.setPosition(Gdx.graphics.getWidth() * 90/ 100f - settingsButton.getWidth() / 2f, Gdx.graphics.getHeight() * 75f / 90f - settingsButton.getHeight() / 2f);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.fromShopScreen = true;
                game.setScreen(new SettingScreen(game, engine));
            }
        });

        ///// Player 1 weapon choices /////

        //Initialize button to change weapon to gun
        player1gun = new Button(new TextureRegionDrawable(new TextureRegion(gun)));
        player1gun.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/10f);
        player1gun.setPosition(Gdx.graphics.getWidth() / 3f - player1gun.getWidth(), Gdx.graphics.getHeight() / 13f*8f - player1gun.getHeight() / 2f);
        player1gun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                //TODO: hva skal skje når vi trykker på knappen?
            }
        });

        //Initialize button to change weapon to Revolver
        player1revolver = new Button(new TextureRegionDrawable(new TextureRegion(revolver)));
        player1revolver.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/10f);
        player1revolver.setPosition(Gdx.graphics.getWidth() / 2f - player1revolver.getWidth() / 2f, Gdx.graphics.getHeight() / 13f*8f - player1revolver.getHeight() / 2f);
        player1revolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
            }
        });

        //Initialize button to change weapon to rifle
        player1rifle = new Button(new TextureRegionDrawable(new TextureRegion(rifle)));
        player1rifle.setSize(Gdx.graphics.getWidth()/6f, Gdx.graphics.getHeight()/5f);
        player1rifle.setPosition(Gdx.graphics.getWidth() / 3f * 2f, Gdx.graphics.getHeight() / 13f*8f - player1rifle.getHeight() / 2f);
        player1rifle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
            }
        });

        ///// Player 2 weapon choices /////

        //Initialize button to change weapon to gun
        player2gun = new Button(new TextureRegionDrawable(new TextureRegion(gun)));
        player2gun.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/10f);
        player2gun.setPosition(Gdx.graphics.getWidth() / 3f - player2gun.getWidth(), Gdx.graphics.getHeight() /50f*18f - player2gun.getHeight() / 2f);
        player2gun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                //TODO: hva skal sje når vi trykker på knappen?
            }
        });

        //Initialize button to change weapon to Revolver
        player2revolver = new Button(new TextureRegionDrawable(new TextureRegion(revolver)));
        player2revolver.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/10f);
        player2revolver.setPosition(Gdx.graphics.getWidth() / 2f - player2revolver.getWidth() / 2f, Gdx.graphics.getHeight() /50f*18f - player2revolver.getHeight() / 2f);
        player2revolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
            }
        });

        //Initialize button to change weapon to rifle
        player2rifle = new Button(new TextureRegionDrawable(new TextureRegion(rifle)));
        player2rifle.setSize(Gdx.graphics.getWidth()/6f, Gdx.graphics.getHeight()/5f);
        player2rifle.setPosition(Gdx.graphics.getWidth() / 3f * 2f, Gdx.graphics.getHeight() /50f*18f - player2rifle.getHeight() / 2f);
        player2rifle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
            }
        });
    }
}

