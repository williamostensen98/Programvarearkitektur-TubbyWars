package com.mygdx.tubby_wars.view;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
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


public class SettingScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;
    private SpriteBatch sb;

    //Textures for title of page and the background
    private Texture title;
    private Texture background;

    //Textures for the buttons
    private Texture resumeGame;
    private Texture quitGame;
    private Texture soundOn; //Used for both music and sounds
    private Texture soundOff; //Used for both music and sounds

    // Click effect
    private Sound click;

    public SettingScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.settingsBackground);
        title = Assets.getTexture(Assets.settingsTitle);
        resumeGame = Assets.getTexture(Assets.resumeGameButton);
        quitGame = Assets.getTexture(Assets.quitGameButton);
        soundOn = Assets.getTexture(Assets.soundOnButton);
        soundOff = Assets.getTexture(Assets.soundOffButton);

        click = game.getClickSound();

        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        sb = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);

        //Initialize title image, logo

        final Image logo = new Image(title);
        logo.setSize(150,75);
        logo.setPosition(Gdx.graphics.getWidth()/2 - logo.getWidth()/2, Gdx.graphics.getHeight()/8*7 - logo.getHeight()/2);


        //Initialize text labels, musicText and soundsText

        final Label musicText = new Label("Music:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        musicText.setPosition(Gdx.graphics.getWidth() / 8*2, Gdx.graphics.getHeight() / 9*5);

        final Label soundsText = new Label("Sound effects:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        soundsText.setPosition(Gdx.graphics.getWidth() / 8*2, Gdx.graphics.getHeight() / 9*3);


        //Initialize musicButton

        final Button musicButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOn)), new TextureRegionDrawable(new TextureRegion(soundOn)), new TextureRegionDrawable(new TextureRegion(soundOff)));
        musicButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        musicButton.setSize(50, 50);
        musicButton.setOrigin(50, 50);
        musicButton.setChecked(game.musicStateManager.getMuteMusicState());
        musicButton.setPosition(Gdx.graphics.getWidth() / 13*4, Gdx.graphics.getHeight() / 9*5- musicButton.getHeight() / 3);
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

        final Button soundEffectButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOn)), new TextureRegionDrawable(new TextureRegion(soundOn)), new TextureRegionDrawable(new TextureRegion(soundOff)));
        soundEffectButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        soundEffectButton.setSize(50, 50);
        soundEffectButton.setOrigin(50, 50);
        soundEffectButton.setChecked(game.soundStateManager.getMuteSoundState());
        soundEffectButton.setPosition(Gdx.graphics.getWidth() / 8*3, Gdx.graphics.getHeight() / 9*3 - soundEffectButton.getHeight() / 3);
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


        //Initialize resumeButton TODO: Denne er ikke med i modellen vår i Achitecture dokumentet

        final Button resumeButton = new Button(new TextureRegionDrawable(new TextureRegion(resumeGame)), new TextureRegionDrawable(new TextureRegion(resumeGame)));
        resumeButton.setSize(100, 50);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 6*5 - resumeButton.getWidth() / 2, Gdx.graphics.getHeight() / 6 - resumeButton.getHeight() / 2);
        //Add click effect
        resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                    game.playSound(click);
                    game.setScreen(new GameScreen(game, engine));
                }
        });


        //Initialize backButton, going back to menu

        final Button backButton = new Button(new TextureRegionDrawable(new TextureRegion(quitGame)), new TextureRegionDrawable(new TextureRegion(quitGame)));
        backButton.setSize(100, 50);
        backButton.setPosition(Gdx.graphics.getWidth() / 6 - backButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - backButton.getHeight() / 2);
        //Add click effect
        backButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                    game.playSound(click);
                    game.setScreen(new MenuScreen(game, engine));
                }
        });

        // Add all objects to the stage
        stage.addActor(logo);
        stage.addActor(musicText);
        stage.addActor(soundsText);
        stage.addActor(musicButton);
        stage.addActor(soundEffectButton);
        stage.addActor(resumeButton);
        stage.addActor(backButton);
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        sb.begin(); // Draw elements to Sprite Batch
        sb.draw( background, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT); //Draws background photo
        sb.end();
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

