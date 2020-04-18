package com.mygdx.tubby_wars.view;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;


public class SettingScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;

    //Textures for title of page and the background
    private Texture title;
    private Texture background;

    //Textures for the buttons
    private Texture resumeGameB;
    private Texture quitGameB;
    private Texture menuGameB;
    private Texture soundOnB; //Used for both music and sounds
    private Texture soundOffB; //Used for both music and sounds

    // Click effect
    private Sound click;

    public SettingScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.settingsBackground);
        title = Assets.getTexture(Assets.settingsTitle);
        resumeGameB = Assets.getTexture(Assets.resumeGameButton);
        quitGameB = Assets.getTexture(Assets.quitGameButton);
        menuGameB = Assets.getTexture(Assets.menuScreenButton);
        soundOnB = Assets.getTexture(Assets.soundOnButton);
        soundOffB = Assets.getTexture(Assets.soundOffButton);

        click = game.getClickSound();

        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title image, logo
        final Image logo = new Image(title);
        logo.setSize(150,75);
        logo.setPosition(Gdx.graphics.getWidth()/2f - logo.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - logo.getHeight()/2f);

        //Initialize text labels, musicText and soundsText
        final Label musicText = new Label("Music:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        musicText.setPosition(Gdx.graphics.getWidth() / 8f*2f, Gdx.graphics.getHeight() / 9f*5f);

        final Label soundsText = new Label("Sound effects:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        soundsText.setPosition(Gdx.graphics.getWidth() / 8f*2f, Gdx.graphics.getHeight() / 9f*3f);


        //Initialize musicButton
        final Button musicButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOffB)));
        musicButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        musicButton.setSize(50, 50);
        musicButton.setOrigin(50, 50);
        musicButton.setChecked(game.musicStateManager.getMuteMusicState());
        musicButton.setPosition(Gdx.graphics.getWidth() / 13f*4f, Gdx.graphics.getHeight() / 9f*5f- musicButton.getHeight() / 3f);
        //Add click effect
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                if (!game.musicStateManager.getMuteMusicState()) {
                    game.muteMusic(game.getBackgroundMusic());
                } else {
                    game.unmuteMusic(game.getBackgroundMusic());
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
        final Button soundEffectButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOffB)));
        soundEffectButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        soundEffectButton.setSize(50, 50);
        soundEffectButton.setOrigin(50, 50);
        soundEffectButton.setChecked(game.soundStateManager.getMuteSoundState());
        soundEffectButton.setPosition(Gdx.graphics.getWidth() / 8f*3f, Gdx.graphics.getHeight() / 9f*3f - soundEffectButton.getHeight() / 3f);
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
        final Button resumeButton = new Button(new TextureRegionDrawable(new TextureRegion(resumeGameB)), new TextureRegionDrawable(new TextureRegion(resumeGameB)));
        resumeButton.setSize(100, 50);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 6f*5f - resumeButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - resumeButton.getHeight() / 2f);
        //Add click effect
        resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                    game.playSound(click);
                    game.setScreen(new GameScreen(game, engine));
                }
        });

        //Initialiserer quit button, going back to settings
        final Button quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitGameB)), new TextureRegionDrawable(new TextureRegion(quitGameB)));
        quitButton.setSize(100, 50);
        quitButton.setPosition(Gdx.graphics.getWidth() / 6f - quitButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - quitButton.getHeight() / 2f);

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.loggedIn = false; //Quits game
                game.setScreen(new MenuScreen(game, engine));
            }
        });

        //Initialiserer quit button, going back to settings
        final Button menuButton = new Button(new TextureRegionDrawable(new TextureRegion(menuGameB)), new TextureRegionDrawable(new TextureRegion(menuGameB)));
        menuButton.setSize(100, 50);
        menuButton.setPosition(Gdx.graphics.getWidth() / 6f - menuButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - menuButton.getHeight() / 2f);


        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new MenuScreen(game, engine));
            }
        });

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
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        game.getBatch().begin(); // Draw elements to Sprite Batch
        game.getBatch().draw(background, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT); //Draws background photo
        //game.getBatch().draw(title,Gdx.graphics.getWidth()/2 - 200,Gdx.graphics.getHeight()/2,400,100); //Draws logo
        game.getBatch().end();

        stage.draw();
    }

    @Override
    public void handleinput() {

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

