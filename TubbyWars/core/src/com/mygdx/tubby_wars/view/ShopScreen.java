package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.controller.ScreenFactory;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

import java.util.List;

public class ShopScreen implements Screen {

    private TubbyWars game;
    private Engine engine;
    private Entity currentPlayer;
    private PlayerSystem ps;
    private ImmutableArray players;
    private Stage stage;

    private Texture titleText;
    private Texture background;

    // Navigation buttons
    private Texture newGameB;
    private Texture quitB;
    private Texture nextPlayer;

    // Weapon texture pack
    private Texture gunSheet;

    private Sound click;

    //Buttons
    private Button newGameButton;
    private Button quitButton;
    private Button next;

    // Weapons buttons
    private Button gun1;
    private Button gun2;
    private Button gun3;

    // LABELS
    private Label infoText;
    private Label scoreText;
    private Label gun1Label;
    private Label gun2Label;
    private Label gun3Label;

    private int currentlyPaying;
    private float currentWeaponDamage;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;
        ps = engine.getSystem(PlayerSystem.class);
        this.players = ps.getEntities();

        gunSheet = Assets.getTexture(Assets.gunSheet);
        background = Assets.getTexture(Assets.shopBackground);
        titleText = Assets.getTexture(Assets.shopTitle); //Title text for shop
        newGameB = Assets.getTexture(Assets.newGameButton); // resume to game button
        quitB = Assets.getTexture(Assets.quitGameButton);
        nextPlayer = Assets.getTexture(Assets.continueGameButton);
        click = Assets.getSound(Assets.clickSound);

        currentlyPaying = 0;
        currentWeaponDamage = 0;
    }

    @Override
    //Make stage and everything to the stage
    public void show() {
        currentPlayer = (Entity)players.get(0);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title text image
        final Image title = new Image(titleText);
        title.setSize(Gdx.graphics.getWidth()/7f,Gdx.graphics.getHeight()/5f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        //Player 1 score text
        scoreText = new Label(ps.getUsername((Entity)players.get(0)) + ": " + ps.getScore((Entity)players.get(0)) + " SCORE POINTS",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        scoreText.setFontScale(1.5f,1.2f);
        scoreText.setPosition(Gdx.graphics.getWidth() / 2f - scoreText.getWidth() / 2, Gdx.graphics.getHeight() /100f*69f);

        //player 1 choose weapon text
        infoText = new Label(ps.getUsername((Entity)players.get(0)) + " you can now exchange score for a new weapon!",new Label.LabelStyle(new BitmapFont(),Color.BLACK));
        infoText.setFontScale(1f,1f);
        infoText.setPosition(Gdx.graphics.getWidth() / 2f - infoText.getWidth() / 2, Gdx.graphics.getHeight() /100f*55f);


        makeButtons();

        gun1Label = new Label("1000 SCORE POINTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gun2Label = new Label("2500 SCORE POINTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gun3Label = new Label("5000 SCORE POINTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gun1Label.setFontScale(1f,1f);
        gun2Label.setFontScale(1f,1f);
        gun3Label.setFontScale(1f,1f);
        gun1Label.setPosition(Gdx.graphics.getWidth() / 2.86f - Gdx.graphics.getWidth() / 6f,Gdx.graphics.getHeight() / 100f*40f - 128 / 2f);
        gun2Label.setPosition(Gdx.graphics.getWidth() / 1.93f - (Gdx.graphics.getWidth() / 6f) / 2f , Gdx.graphics.getHeight() / 100f*40f - 128 / 2f);
        gun3Label.setPosition(Gdx.graphics.getWidth() / 2.9f * 2f, Gdx.graphics.getHeight() / 100f*40f - 128 / 2f);
        updateColors(currentPlayer);

        // player 1 stage
        stage.addActor(title);
        stage.addActor(infoText);

        if (ControllerLogic.roundCount != 0) {
            stage.addActor(quitButton);
        }
        stage.addActor(quitButton);
        stage.addActor(scoreText);
        stage.addActor(next);
        stage.addActor(gun1);
        stage.addActor(gun2);
        stage.addActor(gun3);
        stage.addActor(gun1Label);
        stage.addActor(gun2Label);
        stage.addActor(gun3Label);
    }

    @Override
    //Draw everything
    public void render(float dt){
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void makeButtons() {
        //Initialize button to get GameScreen
        newGameButton = new Button(new TextureRegionDrawable(new TextureRegion(newGameB)));
        newGameButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                ps.setScore(currentPlayer,-currentlyPaying);
                ps.setWeaponDamage(currentPlayer, currentWeaponDamage != 0 ? currentWeaponDamage : ps.getWeaponDamage(currentPlayer));
                ControllerLogic.roundCount ++;
                game.gsm.changeScreen("PLAY");
            }
        });
        next = new Button(new TextureRegionDrawable(new TextureRegion(nextPlayer)));
        next.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        next.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                gun1.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                gun2.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                gun3.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);

                //Add click effect
                game.playSound(click);
                ps.setScore(currentPlayer,-currentlyPaying);
                currentlyPaying = 0;
                ps.setWeaponDamage(currentPlayer, currentWeaponDamage != 0 ? currentWeaponDamage : ps.getWeaponDamage(currentPlayer));
                currentWeaponDamage = 0;
                currentPlayer = (Entity) players.get(1);

                //player 2 chooses weapon text
                scoreText.setText(ps.getUsername((Entity) players.get(1)) + ": " + ps.getScore((Entity)players.get(1)) + " SCORE POINTS!");
                infoText.setText(ps.getUsername((Entity) players.get(1)) + " you can now exchange score for a new weapon!");
                stage.addActor(newGameButton);
                updateColors((Entity) players.get(1));
                next.remove();
            }
        });

        //Initialiserer quit button, going back to settings
        quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitB)));
        quitButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        quitButton.setPosition(Gdx.graphics.getWidth() / 6f - quitButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - quitButton.getHeight() / 2f);

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.loggedIn = false; //Quits game
                game.gsm.changeScreen("MENU");
            }
        });

        ///// Player 1 weapon choices /////

        //Initialize button to change weapon to gun
        gun1 = new Button(new TextureRegionDrawable(new TextureRegion(gunSheet,0,0,128,128)));
        gun1.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        gun1.setPosition(Gdx.graphics.getWidth() / 3f - gun1.getWidth(), Gdx.graphics.getHeight() / 100f*40f - gun1.getHeight() / 2f);
        gun1.addListener(clickListener(1));

        //Initialize button to change weapon to Revolver
        gun2 = new Button(new TextureRegionDrawable(new TextureRegion(gunSheet,0,128,128,128)));
        gun2.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        gun2.setPosition(Gdx.graphics.getWidth() / 2f - gun2.getWidth() / 2f, Gdx.graphics.getHeight() / 100f*40f - gun2.getHeight() / 2f);
        gun2.addListener(clickListener(2));

        //Initialize button to change weapon to rifle
        gun3 = new Button(new TextureRegionDrawable(new TextureRegion(gunSheet,0,256,128,128)));
        gun3.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        gun3.setPosition(Gdx.graphics.getWidth() / 3f * 2f, Gdx.graphics.getHeight() / 100f*40f - gun3.getHeight() / 2f);
        gun3.addListener(clickListener(3));

    }

    private void updateColors(Entity player){
        if(ps.getWeaponDamage(player) == (float)0.8){
            gun1Label.setColor(Color.GRAY);
            gun2Label.setColor(Color.GRAY);
            gun3Label.setColor(Color.GRAY);
        }
        else if(ps.getWeaponDamage(player) == (float)1.8){
            gun1Label.setColor(Color.GRAY);
            gun2Label.setColor(Color.GRAY);
            gun3Label.setColor(ps.getScore(player) >= 5000 ? Color.GREEN : Color.RED);
        }
        else if(ps.getWeaponDamage(player) == (float)2.4){
            gun1Label.setColor(Color.GRAY);
            gun2Label.setColor(ps.getScore(player) >= 2500 ? Color.GREEN : Color.RED);
            gun3Label.setColor(ps.getScore(player) >= 5000 ? Color.GREEN : Color.RED);
        }
        else{
            gun1Label.setColor(ps.getScore(player) >= 1000 ? Color.GREEN : Color.RED);
            gun2Label.setColor(ps.getScore(player) >= 2500 ? Color.GREEN : Color.RED);
            gun3Label.setColor(ps.getScore(player) >= 5000 ? Color.GREEN : Color.RED);
        }
    }

    private ClickListener clickListener(final int weapon){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos){
                game.playSound(click);
                if (weapon==1 && ps.getScore(currentPlayer) >= 1000 && ps.getWeaponDamage(currentPlayer) > 2.5){
                    gun1.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 3.3f);
                    gun2.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 6f);
                    gun3.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    ps.setWeaponTexture(currentPlayer,new TextureRegion(gunSheet,14,14,100,100));
                    currentlyPaying = 1000;
                    currentWeaponDamage = (float)2.4;

                }
                else if(weapon==2 && ps.getScore(currentPlayer) >= 2500 && ps.getWeaponDamage(currentPlayer) > 1.9) {
                    gun2.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 3.3f);
                    gun1.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    gun3.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    ps.setWeaponTexture(currentPlayer,new TextureRegion(gunSheet,14,142,100,100));
                    currentlyPaying = 2500;
                    currentWeaponDamage = (float)1.8;
                }
                else if (weapon==3 && ps.getScore(currentPlayer) >= 5000 && ps.getWeaponDamage(currentPlayer) > 0.9){
                    gun3.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 3.3f);
                    gun1.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    gun2.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    ps.setWeaponTexture(currentPlayer,new TextureRegion(gunSheet,14,270,100,100));
                    currentlyPaying = 5000;
                    currentWeaponDamage = (float)0.8;
                }
            }
        };
    }
    @Override
    //Disposes the stage and all textures.
    public void dispose(){
        stage.dispose();
        titleText.dispose();
        background.dispose();
        newGameB.dispose();
        quitB.dispose();
        nextPlayer.dispose();
        click.dispose();
    }
}

