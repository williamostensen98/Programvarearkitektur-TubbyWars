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
    private Texture menuB;
    private Texture newGameB;

    // Weapons
    private Texture gun;
    private Texture rifle;
    private Texture revolver;

    private Sound click;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        titleText = Assets.getTexture(Assets.shopTitle); //Title text for shop
        menuB = Assets.getTexture(Assets.menuScreenButton); // Back to menu button
        newGameB = Assets.getTexture(Assets.newGameButton); // resume to game button
        gun = Assets.getTexture(Assets.gunWeapon); // choose gun button
        rifle = Assets.getTexture(Assets.rifleWeapon); // choose rifle button
        revolver = Assets.getTexture(Assets.revolverWeapon); //choose revolver button
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
        final Label infoText = new Label("Choose weapon:", new Label.LabelStyle(new BitmapFont(), Color.BLACK)); // Blåfarge: new Color(56.0f/255.0f, 145.0f/255.0f, 206.0f/255.0f, 1.0f)
        infoText.setFontScale(1f,1f);
        infoText.setPosition(Gdx.graphics.getWidth() / 5f - infoText.getWidth()/2f , Gdx.graphics.getHeight() / 12f*8f);

        //Initialize button to get GameScreen
        final Button newGameButton = new Button(new TextureRegionDrawable(new TextureRegion(newGameB)));
        newGameButton.setSize(100, 50);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f*5f - newGameButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        //Add click effect
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new GameScreen(game, engine));
            }

        });

        //Initialize button to get back to menu
        final Button menuButton = new Button(new TextureRegionDrawable(new TextureRegion(menuB)));
        menuButton.setSize(100, 50);
        menuButton.setPosition(Gdx.graphics.getWidth() / 6f - menuButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - menuButton.getHeight() / 2f);
        //Add click effect
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new MenuScreen(game, engine));
            }
        });

        //Initialize button to change weapon to gun
        final Button newGun = new Button(new TextureRegionDrawable(new TextureRegion(gun)));
        newGun.setSize(150, 50);
        newGun.setPosition(Gdx.graphics.getWidth()/3f - newGun.getWidth() , Gdx.graphics.getHeight() / 2f - newGun.getHeight() / 2f);
        //Add click effect
        newGun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                //game.setScreen(new GameScreen(game, engine));
            }
        });

        //Initialize button to change weapon to rifle
        final Button newRifle = new Button(new TextureRegionDrawable(new TextureRegion(rifle)));
        newRifle.setSize(150, 50);
        newRifle.setPosition(Gdx.graphics.getWidth() / 2f - newRifle.getWidth()/2f , Gdx.graphics.getHeight() /2f - newRifle.getHeight() / 2f);
        //Add click effect
        newRifle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                //game.setScreen(new GameScreen(game, engine));
                //hva skal sje når vi trykker på knappen?
            }
        });

        //Initialize button to change weapon to Revolver
        final Button newRevolver = new Button(new TextureRegionDrawable(new TextureRegion(revolver)));
        newRevolver.setSize(150, 50);
        newRevolver.setPosition(Gdx.graphics.getWidth() / 3f*2f , Gdx.graphics.getHeight() / 2f - newRevolver.getHeight() / 2f);
        //Add click effect
        newRevolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                //game.setScreen(new GameScreen(game, engine));
            }
        });

        //Add objects to stage
        stage.addActor(title);
        stage.addActor(newGameButton);
        stage.addActor(menuButton);
        stage.addActor(newGun);
        stage.addActor(newRifle);
        stage.addActor(newRevolver);
        stage.addActor(infoText);
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
}
