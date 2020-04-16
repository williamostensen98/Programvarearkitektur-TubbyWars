package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.badlogic.gdx.audio.Music;
import com.mygdx.tubby_wars.model.Assets;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MenuScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    //Initializing the textures
    private Texture logo;
    private Texture background;
    private Texture playB;
    private Texture highScoreB;
    private Texture settingsB;

    //Initiializing the background music
    private Music music;

    private Sprite sprite;
    private SpriteBatch sb;
    private Stage stage;

    public MenuScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.mainBackground);
        logo = Assets.getTexture(Assets.logo);
        playB = Assets.getTexture(Assets.playButton);
        highScoreB = Assets.getTexture(Assets.highScoreButton);
        settingsB = Assets.getTexture(Assets.settingsButton);

        this.music = game.getMusic();
        this.music.setVolume(0.3f);
        game.playMusic(music);

        // one-time operations
        create();
    }

    public void create(){
        stage = new Stage(new ScreenViewport());
        sb = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);

        //Initialiserer button to get GameScreen
        final Button gameButton = new Button(new TextureRegionDrawable(new TextureRegion(playB)));
       // gameButton.setSize(60, 60);
        gameButton.setPosition(Gdx.graphics.getWidth() / 2 - gameButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - gameButton.getHeight() / 2);

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.setScreen(new GameScreen(game, engine));
            }

        });

        //Initialiserer button to get to SettingsScreen
        final Button settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        //settingsButton.setSize(60, 60);
        settingsButton.setPosition(Gdx.graphics.getWidth()/4 - settingsButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - settingsButton.getHeight() / 2);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.setScreen(new SettingScreen(game, engine));
            }
        });

        //Initialiserer button to get to HighScoreScreen
        final Button highScoreButton = new Button(new TextureRegionDrawable(new TextureRegion(highScoreB)));
        //highScoreButton.setSize(60, 60);
        highScoreButton.setPosition(Gdx.graphics.getWidth() - 210 - highScoreButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - highScoreButton.getHeight() / 2);

        highScoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.setScreen(new HighScoreScreen(game, engine));
            }
        });

        stage.addActor(gameButton);
        stage.addActor(settingsButton);
        stage.addActor(highScoreButton);
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        sb.begin(); // Draw elements to Sprite Batch
        sb.draw(background, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT); //Draws background photo
        sb.draw(logo, Gdx.graphics.getWidth()/2 - logo.getWidth()/2,
                Gdx.graphics.getHeight()/2, 300,150); //Draws logo
        sb.end();

        stage.draw();
    }

    @Override
    public void handleinput(){
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