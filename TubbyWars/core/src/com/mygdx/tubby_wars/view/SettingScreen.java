package com.mygdx.tubby_wars.view;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.ScreenFactory;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;


public class SettingScreen implements Screen {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;

    //Textures for title of page and the background
    private Texture title;
    private Texture background;

    //Textures for the buttons
    private Texture resumeGameB;
    private Texture quitGameB;
    private Texture menuScreenB;
    private Texture soundOnB; //Used for both music and sounds
    private Texture soundOffB; //Used for both music and sounds

    //Buttons
    private Button menuButton;
    private Button quitButton;
    private Button resumeButton;
    private Button soundEffectButton;
    private Button musicButton;

    // Click effect
    private Sound click;

    public SettingScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.settingsBackground);
        title = Assets.getTexture(Assets.settingsTitle);
        menuScreenB = Assets.getTexture(Assets.menuScreenButton);
        resumeGameB = Assets.getTexture(Assets.continueGameButton);
        quitGameB = Assets.getTexture(Assets.quitGameButton);
        soundOnB = Assets.getTexture(Assets.soundOnButton);
        soundOffB = Assets.getTexture(Assets.soundOffButton);

        click = Assets.getSound(Assets.clickSound);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title image, logo
        final Image logo = new Image(title);
        logo.setSize(Gdx.graphics.getWidth()/7f,  Gdx.graphics.getHeight()/5f);
        logo.setPosition(Gdx.graphics.getWidth()/2f - logo.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - logo.getHeight()/2f);

        //Initialize text labels, musicText and soundsText
        final Label musicText = new Label("Music:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        musicText.setPosition(Gdx.graphics.getWidth() / 8f*2f, Gdx.graphics.getHeight() / 9f*5f);

        final Label soundsText = new Label("Sound effects:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        soundsText.setPosition(Gdx.graphics.getWidth() / 8f*2f, Gdx.graphics.getHeight() / 9f*3f);

        makeButtons();

        if (ControllerLogic.loggedIn) {
            stage.addActor(quitButton);
            stage.addActor(resumeButton);
        }
        else {
            stage.addActor(menuButton);
        }

        // Add all objects to the stage
        stage.addActor(logo);
        stage.addActor(musicText);
        stage.addActor(soundsText);
        stage.addActor(musicButton);
        stage.addActor(soundEffectButton);
    }

    @Override
    public void render(float dt){
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);
        game.getBatch().begin(); // Draw elements to Sprite Batch
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        //game.getBatch().draw(title,Gdx.graphics.getWidth()/2 - 200,Gdx.graphics.getHeight()/2,400,100); //Draws logo
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

    @Override
    public void dispose() {
        stage.dispose();
        title.dispose();
        background.dispose();
        resumeGameB.dispose();
        quitGameB.dispose();
        menuScreenB.dispose();
        soundOnB.dispose();
        soundOffB.dispose();
        click.dispose();
    }

    private void makeButtons() {

        //Initialize musicButton
        musicButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOffB)));
        musicButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        musicButton.setSize(Gdx.graphics.getWidth()/24f,Gdx.graphics.getHeight()/11f);
        musicButton.setOrigin(Gdx.graphics.getWidth()/24f,Gdx.graphics.getHeight()/11f);
        musicButton.setChecked(game.musicStateManager.getMuteMusicState());
        musicButton.setPosition(Gdx.graphics.getWidth() / 100f*30f, Gdx.graphics.getHeight() / 9f*5f- musicButton.getHeight() / 3f);
        //Add click effect
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                if (!game.musicStateManager.getMuteMusicState()) {
                    game.muteMusic(Assets.getMusic(Assets.backgroundMusic));
                } else {
                    game.unmuteMusic(Assets.getMusic(Assets.backgroundMusic));
                }
            }
            //Runs when the button is pressed down
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                musicButton.addAction(Actions.scaleTo(0.96f, 0.96f, 0.2f)); //Minker størrelsen på knappen når den trykkes
                return super.touchDown(inputEvent, 100, 100, pointer, button);
            }
            //Runs when the button is not pressed down
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100, pointer, button);
                musicButton.addAction(Actions.scaleTo(1f, 1f, 0.2f)); //Setter størrelsen på knappen tilbake til original størrelse
            }
        });

        //Initialize soundEffectButton
        soundEffectButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOffB)));
        soundEffectButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        soundEffectButton.setSize(Gdx.graphics.getWidth()/24f,Gdx.graphics.getHeight()/11f);
        soundEffectButton.setOrigin(Gdx.graphics.getWidth()/24f,Gdx.graphics.getHeight()/11f);
        soundEffectButton.setChecked(game.soundStateManager.getMuteSoundState());
        soundEffectButton.setPosition(Gdx.graphics.getWidth() / 100f*34f, Gdx.graphics.getHeight() / 9f*3f - soundEffectButton.getHeight() / 3f);
        //Add click effect
        soundEffectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (game.soundStateManager.getMuteSoundState()) {
                    game.soundStateManager.unmuteSound();
                    game.playSound(click);
                }
                else {
                    game.soundStateManager.muteSound();
                }
            }
            //Runs when the button is pressed down
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                soundEffectButton.addAction(Actions.scaleTo(0.96f, 0.96f, 0.2f)); //Minker størrelsen på knappen når den trykkes
                return super.touchDown(inputEvent, 100, 100, pointer, button);
            }
            //Runs when the button is not pressed down
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100, pointer, button);
                soundEffectButton.addAction(Actions.scaleTo(1f, 1f, 0.2f)); //Setter størrelsen på knappen tilbake til original størrelse
            }
        });

        //Initialiserer resumeButton
        resumeButton = new Button(new TextureRegionDrawable(new TextureRegion(resumeGameB)));
        resumeButton.setSize(100, 50);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 6f*5f - resumeButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - resumeButton.getHeight() / 2f);
        //Add click effect
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                //dispose();
                if (ControllerLogic.fromHighScoreScreen) {
                    game.setScreen(ScreenFactory.getScreen("HIGHSCORE",game, engine));
                    ControllerLogic.fromHighScoreScreen = false;
                }
                else {
                    game.setScreen(ControllerLogic.currentGame);
                }
            }
        });

        //Initialiserer quit button, going back to settings
        quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitGameB)));
        quitButton.setSize(100, 50);
        quitButton.setPosition(Gdx.graphics.getWidth() / 6f - quitButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - quitButton.getHeight() / 2f);

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.loggedIn = false; //Quits game
                ControllerLogic.roundCount = 0;
                game.setScreen(ScreenFactory.getScreen("MENU",game, engine));
                // TODO HER MÅ VI NULLSTILLE ALLE VARIABLER I CONTROLLERLOGIC
            }
        });

        //Initialiserer quit button, going back to menu
        menuButton = new Button(new TextureRegionDrawable(new TextureRegion(menuScreenB)));
        menuButton.setSize(Gdx.graphics.getWidth()/12f   ,     Gdx.graphics.getHeight()/10f);
        menuButton.setPosition(Gdx.graphics.getWidth() / 6f - menuButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - menuButton.getHeight() / 2f);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //dispose();
                game.playSound(click);
                game.setScreen(ScreenFactory.getScreen("MENU",game, engine));
            }
        });
    }
}

