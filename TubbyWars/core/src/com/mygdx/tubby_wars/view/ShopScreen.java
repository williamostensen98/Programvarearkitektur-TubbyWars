package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
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
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class ShopScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private List<Entity> players;
    private Entity currentPlayer;
    private PlayerSystem ps;

    private Stage stage;

    private Texture titleText;
    private Texture background;

    // Navigation buttons
    private Texture newGameB;
    //private Texture settingsB;
    private Texture quitB;
    private Texture nextPlayer;

    // Weapons buttons
    private Texture gun;
    private Texture rifle;
    private Texture revolver;

    private Sound click;

    //Buttons
    private Button newGameButton;
    //private Button settingsButton;
    private Button quitButton;
    private Button next;

    private Button gunButton;
    private Button revolverButton;
    private Button rifleButton;

    // LABELS
    private Label infoText;


    // TODO TIL JENNY FRA HÅKON! ENDRET LITT AV LOGIKKEN HER, MEN SLITER LITT MED STYLINGEN, SE UNDER FOR BESKRIVELSE AV HVORDAN TING FUNGERER
    // skal du referere til verdier i komponentene, som feks score eller senere cash
    // int currentPlayerMoney = pm.getMoney(currentPlayer);
    // har også laget en funksjon som er felles for alle våpenene så trenger ikke lage dobbelt opp. Var bra sånn det var, men siden vi må lage alt større
    // tenkte vi at å heller bare gjøre ting to ganger som nå var bedre.

    // har du spørsmål, ta gjerne kontakt per epost eller sms ;))))

    // TODO kunne kanskje trengt litt mer feedback, hvilket våpen er i bruk nå, hvilket har blitt valgt i shoppen, osv.

    public ShopScreen(TubbyWars game, Engine engine, List<Entity> players){
        super();
        this.game = game;
        this.engine = engine;
        this.players = players;

        ps = engine.getSystem(PlayerSystem.class);

        background = Assets.getTexture(Assets.shopBackground);
        titleText = Assets.getTexture(Assets.shopTitle); //Title text for shop
        newGameB = Assets.getTexture(Assets.newGameButton); // resume to game button
        quitB = Assets.getTexture(Assets.quitGameButton);
        nextPlayer = Assets.getTexture(Assets.continueGameButton);
        gun = Assets.getTexture(Assets.gunWeapon); // choose gun button
        rifle = Assets.getTexture(Assets.rifleWeapon); // choose rifle button
        revolver = Assets.getTexture(Assets.revolverWeapon); //choose revolver button
        //settingsB = Assets.getTexture(Assets.settingSignButton);

        click = game.getClickSound();

        // one-time operations
        create();
    }

    @Override
    public void create() {

        currentPlayer = players.get(0);

        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

       //Initialize title text image
        final Image title = new Image(titleText);
        title.setSize(Gdx.graphics.getWidth()/7f,Gdx.graphics.getHeight()/5f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);


        //player 1 choose weapon text
        infoText = new Label(ps.getUsername(players.get(0)) + "  turn to choose weapon:",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        infoText.setFontScale(1f,1f);
        infoText.setPosition(Gdx.graphics.getWidth() / 6f - infoText.getWidth()/2f, Gdx.graphics.getHeight() /100f*69f);

        makeButtons();

        //Add buttons to stage

        // player 1 stage
        stage.addActor(title);
        stage.addActor(infoText);

        if (ControllerLogic.roundCount != 0) {
                stage.addActor(quitButton);

            //TODO: Add who won the game! Add score for both players
            Label informationText = new Label("This round the winner is " + "username" +  "! Upgrade your weapons, and good luck in the next round!", new Label.LabelStyle(new BitmapFont(), Color.PINK));
            informationText.setPosition(Gdx.graphics.getWidth() / 2f - informationText.getWidth() / 2, Gdx.graphics.getHeight() / 8f * 6f);
            stage.addActor(informationText);
        }

        stage.addActor(newGameButton);
        //stage.addActor(settingsButton);

        stage.addActor(next);

        stage.addActor(gunButton);
        stage.addActor(rifleButton);
        stage.addActor(revolverButton);



    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
       // game.getBatch().begin();
       // game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
       // game.getBatch().end();

        //////Må endres til bakgrunn, men har dette til vi fikser den store bakgrunnen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(187.0f/255.0f, 231.0f/255.0f, 255.0f/255.0f, 1.0f);
        ////////////////////////
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
        newGameButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                game.setScreen(new PlayScreen(game, engine, players));
            }

        });

        next = new Button(new TextureRegionDrawable(new TextureRegion(nextPlayer)));
        next.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        next.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Add click effect
                game.playSound(click);
                game.setScreen(new PlayScreen(game, engine, players));
                currentPlayer = players.get(1);
                //player 2 chooses weapon text
                infoText.setText(ps.getUsername(players.get(1)) + "  turn to choose weapon:");
                stage.addActor(newGameButton);
                next.remove();
            }

        });

        //Initialiserer quit button, going back to settings
        quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitB)));
        quitButton.setSize(Gdx.graphics.getWidth() / 24f, Gdx.graphics.getHeight() / 13f);
        quitButton.setPosition(Gdx.graphics.getWidth() / 6f - quitButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - quitButton.getHeight() / 2f);

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.loggedIn = false; //Quits game
                game.setScreen(new MenuScreen(game, engine));
            }
        });

        ///// Player 1 weapon choices /////

        //Initialize button to change weapon to gun
        gunButton = new Button(new TextureRegionDrawable(new TextureRegion(gun)));
        gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        gunButton.setPosition(Gdx.graphics.getWidth() / 3f - gunButton.getWidth(), Gdx.graphics.getHeight() / 2f - gunButton.getHeight() / 2f);
        gunButton.addListener(clickListener(gun, (float) 1.2));

        //Initialize button to change weapon to Revolver
        revolverButton = new Button(new TextureRegionDrawable(new TextureRegion(revolver)));
        revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        revolverButton.setPosition(Gdx.graphics.getWidth() / 2f - revolverButton.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - revolverButton.getHeight() / 2f);
        revolverButton.addListener(clickListener(revolver, (float) 1.4));

        //Initialize button to change weapon to rifle
        rifleButton = new Button(new TextureRegionDrawable(new TextureRegion(rifle)));
        rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        rifleButton.setPosition(Gdx.graphics.getWidth() / 3f * 2f, Gdx.graphics.getHeight() / 2f - rifleButton.getHeight() / 2f);
        rifleButton.addListener(clickListener(rifle, (float) 1.6));

    }

    private ClickListener clickListener(final Texture weapon, final float weaponDamage){

        /// implementere
       /* if (weapon==rifle){
            rifleButton.setSize(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 2.5f);
            gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
            revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        }
        if (weapon==gun){
            gunButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
            revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
            rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        }
        if(weapon==revolver) {
            revolverButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
            gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
            rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        }*/

        return new ClickListener(){

            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos){
                game.playSound(click);

                ps.setWeaponDamage(currentPlayer,weaponDamage);
                ps.setWeaponTexture(currentPlayer, weapon);
                // må kanskje også sjekke om man har nok penger elns her, og trekke fra penger ved evt kjøp
            }
        };
    }
}

