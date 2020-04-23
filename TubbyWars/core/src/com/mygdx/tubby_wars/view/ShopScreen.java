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



    //private List<Entity> players;
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

    // Weapons buttons
    private Texture gun;
    private Texture rifle;
    private Texture revolver;

    private Sound click;

    //Buttons
    private Button newGameButton;
    private Button quitButton;
    private Button next;

    private Button gunButton;
    private Button revolverButton;
    private Button rifleButton;

    // LABELS
    private Label infoText;
    private Label scoreText;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        ps = engine.getSystem(PlayerSystem.class);
        this.players = ps.getEntities();


        background = Assets.getTexture(Assets.shopBackground);
        titleText = Assets.getTexture(Assets.shopTitle); //Title text for shop
        newGameB = Assets.getTexture(Assets.newGameButton); // resume to game button
        quitB = Assets.getTexture(Assets.quitGameButton);
        nextPlayer = Assets.getTexture(Assets.continueGameButton);
        gun = Assets.getTexture(Assets.gunWeapon); // choose gun button
        rifle = Assets.getTexture(Assets.rifleWeapon); // choose rifle button
        revolver = Assets.getTexture(Assets.revolverWeapon); //choose revolver button

        click = Assets.getSound(Assets.clickSound);
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
        scoreText = new Label(ps.getUsername((Entity)players.get(0)) + " earned " + ps.getScore((Entity)players.get(0)) + " points this round!",new Label.LabelStyle(new BitmapFont(), Color.PINK));
        scoreText.setFontScale(1f,1f);
        scoreText.setPosition(Gdx.graphics.getWidth() / 2f - scoreText.getWidth()/2f, Gdx.graphics.getHeight() /100f*69f);

        //player 1 choose weapon text
        infoText = new Label(ps.getUsername((Entity)players.get(0)) + " turn to choose weapon:",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        infoText.setFontScale(1f,1f);
        infoText.setPosition(Gdx.graphics.getWidth() / 6f - infoText.getWidth()/2f, Gdx.graphics.getHeight() /100f*55f);

        makeButtons();

        //Add buttons to stage

        // player 1 stage
        stage.addActor(title);
        stage.addActor(infoText);

        if (ControllerLogic.roundCount != 0) {
            stage.addActor(quitButton);
            stage.addActor(scoreText);
        }

        stage.addActor(next);
        stage.addActor(gunButton);
        stage.addActor(rifleButton);
        stage.addActor(revolverButton);
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
                //dispose();
                ControllerLogic.roundCount ++;
                game.setScreen(ScreenFactory.getScreen("PLAY",game, engine));
            }
        });

        next = new Button(new TextureRegionDrawable(new TextureRegion(nextPlayer)));
        next.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        next.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
                revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
                rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                //Add click effect
                game.playSound(click);
                currentPlayer = (Entity) players.get(1);
                //player 2 chooses weapon text
                scoreText.setText(ps.getUsername((Entity) players.get(1)) + " earned " + ps.getScore((Entity)players.get(1)) + " points this round!");
                infoText.setText(ps.getUsername((Entity) players.get(1)) + "  turn to choose weapon:");
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
                //dispose();
                game.setScreen(ScreenFactory.getScreen("MENU",game, engine));
            }
        });

        ///// Player 1 weapon choices /////

        //Initialize button to change weapon to gun
        gunButton = new Button(new TextureRegionDrawable(new TextureRegion(gun)));
        gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        gunButton.setPosition(Gdx.graphics.getWidth() / 3f - gunButton.getWidth(), Gdx.graphics.getHeight() / 100f*40f - gunButton.getHeight() / 2f);
        gunButton.addListener(clickListener(gun, (float) 1));

        //Initialize button to change weapon to Revolver
        revolverButton = new Button(new TextureRegionDrawable(new TextureRegion(revolver)));
        revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
        revolverButton.setPosition(Gdx.graphics.getWidth() / 2f - revolverButton.getWidth() / 2f, Gdx.graphics.getHeight() / 100f*40f - revolverButton.getHeight() / 2f);
        revolverButton.addListener(clickListener(revolver, (float) 1.5));

        //Initialize button to change weapon to rifle
        rifleButton = new Button(new TextureRegionDrawable(new TextureRegion(rifle)));
        rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        rifleButton.setPosition(Gdx.graphics.getWidth() / 3f * 2f, Gdx.graphics.getHeight() / 100f*40f - rifleButton.getHeight() / 2f);
        rifleButton.addListener(clickListener(rifle, (float) 2));

    }

    private ClickListener clickListener(final Texture weapon, final float weaponDamage){
        return new ClickListener(){

            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos){
                game.playSound(click);

                ps.setWeaponDamage(currentPlayer,weaponDamage);
                ps.setWeaponTexture(currentPlayer, weapon);
                // TODO: må kanskje også sjekke om man har nok penger elns her, og trekke fra penger ved evt kjøp

                if (weapon==rifle){
                    rifleButton.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 3.3f);
                    gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
                    revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
                }
                if (weapon==gun){
                    gunButton.setSize(Gdx.graphics.getWidth() / 8f, Gdx.graphics.getHeight() / 6.6f);
                    revolverButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
                    rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                }
                if(weapon==revolver) {
                    revolverButton.setSize(Gdx.graphics.getWidth() / 8f, Gdx.graphics.getHeight() / 6.6f);
                    gunButton.setSize(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f);
                    rifleButton.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
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
        gun.dispose();
        rifle.dispose();
        revolver.dispose();
        click.dispose();
    }
}

