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
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

public class ShopScreen implements Screen {

    private TubbyWars game;
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
    private Label usernameText;
    private Label scoreText;
    private Label gun1Label;
    private Label gun2Label;
    private Label gun3Label;


    private int currentlyPaying;
    private float currentWeaponDamage;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
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

        //MAKE TITLE IMAGE
        final Image title = new Image(titleText);
        title.setSize(Gdx.graphics.getWidth()/7f,Gdx.graphics.getHeight()/5f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        int num = ControllerLogic.roundCount+1;
        makeButtons();

        // MAKE LABELS
        usernameText = makeLabel("USER: " + ps.getUsername((Entity)players.get(0)),50f,71f, Color.BLACK);
        scoreText = makeLabel("SCORE POINTS: " + ps.getScore((Entity)players.get(0)),50f,67f, Color.YELLOW);
        Label round = makeLabel("ROUND " + num + " OUT OF 5", 50f, 63f, Color.BLACK);
        Label infoText = makeLabel("When you get a high enough score you can exchange it for a new weapon that does more damage! " +
                " If you don't want to, press continue ", 50f, 55f, Color.BLACK);
        gun1Label = makeLabel("500 SCORE POINTS",23f, 29f, Color.WHITE);
        gun2Label = makeLabel("2500 SCORE POINTS",49f,29f, Color.WHITE);
        gun3Label = makeLabel("5000 SCORE POINTS",75f,29f, Color.WHITE);
        updateColors(currentPlayer);

        // MAKE WEAPON BUTTONS
        gun1 = makeWeaponButton(1, 33f,1f,0);
        gun2 = makeWeaponButton(2,50f,2f,128);
        gun3 = makeWeaponButton(3,75f,2f,256);

        // player 1 stage
        stage.addActor(title);
        stage.addActor(round);
        stage.addActor(scoreText);
        stage.addActor(usernameText);
        stage.addActor(infoText);

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

    private Label makeLabel(String text, float xPos,float yPos, Color c){
        Label l = new Label(text, new Label.LabelStyle(new BitmapFont(), c));
        l.setFontScale(1f,1f);
        l.setPosition(Gdx.graphics.getWidth() /100f* xPos - l.getWidth()/2, Gdx.graphics.getHeight() / 100f*yPos);
        return l;
    }

    private void setButtonSize(Button b, float x, float y){
        b.setSize(Gdx.graphics.getWidth()/x,Gdx.graphics.getHeight()/y);
    }

    private Button makeWeaponButton(int nr,float xPos, float yPos, int y){
        Button w = new Button(new TextureRegionDrawable(new TextureRegion(gunSheet,0,y,128,128)));
        w.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
        w.setPosition(Gdx.graphics.getWidth() /100f* xPos - w.getWidth() /yPos, Gdx.graphics.getHeight() / 100f*44f - w.getHeight() / 2f);
        w.addListener(clickListener(nr));
        return w;
    }
    
    private void makeButtons() {
        //Initialize button to get GameScreen
        newGameButton = new Button(new TextureRegionDrawable(new TextureRegion(newGameB)));
        setButtonSize(newGameButton,10f,7f);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f * 5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 10f - newGameButton.getHeight() / 2f);
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

        //Initialize continue button
        next = new Button(new TextureRegionDrawable(new TextureRegion(nextPlayer)));
        setButtonSize(next,10f,7f);
        next.setPosition(Gdx.graphics.getWidth() / 6f * 5f - next.getWidth() / 2f, Gdx.graphics.getHeight() / 10f - next.getHeight() / 2f);
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                setButtonSize(gun1,6f,5f);
                setButtonSize(gun2,6f,5f);
                setButtonSize(gun3,6f,5f);

                //Add click effect
                game.playSound(click);
                ps.setScore(currentPlayer,-currentlyPaying);
                currentlyPaying = 0;
                ps.setWeaponDamage(currentPlayer, currentWeaponDamage != 0 ? currentWeaponDamage : ps.getWeaponDamage(currentPlayer));
                currentWeaponDamage = 0;
                currentPlayer = (Entity) players.get(1);

                usernameText.setText("USER: " + ps.getUsername((Entity)players.get(1)));
                scoreText.setText("SCORE POINTS: " + ps.getScore((Entity)players.get(1)));
                stage.addActor(newGameButton);
                updateColors((Entity) players.get(1));
                next.remove();
            }
        });

        //Initialiserer quit button, going back to settings
        quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitB)));
        setButtonSize(quitButton,10f,7f);
        quitButton.setPosition(Gdx.graphics.getWidth() / 6f - quitButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f - quitButton.getHeight() / 2f);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.loggedIn = false; //Quits game
                ControllerLogic.isPlayersTurn = false;
                ControllerLogic.roundCount = 0;
                game.gsm.changeScreen("MENU");
            }
        });
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
            gun1Label.setColor(ps.getScore(player) >= 500 ? Color.GREEN : Color.RED);
            gun2Label.setColor(ps.getScore(player) >= 2500 ? Color.GREEN : Color.RED);
            gun3Label.setColor(ps.getScore(player) >= 5000 ? Color.GREEN : Color.RED);
        }
    }

    private ClickListener clickListener(final int weapon){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos){
                System.out.println(weapon + currentlyPaying);
                game.playSound(click);
                if (weapon==1 && ps.getScore(currentPlayer) >= 500 && ps.getWeaponDamage(currentPlayer) > 2.5){
                    gun1.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 3.3f);
                    gun2.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    gun3.setSize(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 5f);
                    ps.setWeaponTexture(currentPlayer,new TextureRegion(gunSheet,14,14,100,100));
                    currentlyPaying = 500;
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

