package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.ScreenFactory;
import com.mygdx.tubby_wars.model.Assets;
import com.badlogic.gdx.audio.Sound;

public class MenuScreen implements Screen {

    private TubbyWars game;

    //Initialize textures
    private Texture logo;
    private Texture background;
    private Texture playB;
    private Texture highScoreB;
    private Texture settingsB;

    private Button settingsButton;
    private Button highScoreButton;
    private Button gameButton;

    //Initialize  background music and sound effects
    private Music music;
    private Sound click;

    private Stage stage;
    private Engine engine;

    public MenuScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;

        background = Assets.getTexture(Assets.mainBackground);
        logo = Assets.getTexture(Assets.logo);
        playB = Assets.getTexture(Assets.gameScreenButton);
        highScoreB = Assets.getTexture(Assets.highScoreButton);
        settingsB = Assets.getTexture(Assets.settingScreenButton);

        music = Assets.getMusic(Assets.backgroundMusic);
        this.game.playMusic(music);

        click = Assets.getSound(Assets.clickSound);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title logo
        final Image title = new Image(logo);
        title.setSize(Gdx.graphics.getWidth()/4f,  Gdx.graphics.getHeight()/3f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/2f);

        makeButtons();

        stage.addActor(title);
        stage.addActor(gameButton);
        stage.addActor(settingsButton);
        stage.addActor(highScoreButton);
    }

    @Override
    public void render(float dt) {
        game.getBatch().begin(); // Draw elements to Sprite Batch
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

    @Override
    public void dispose() {

    }

    private void makeButtons() {
        //Initialize button to get GameScreen
        gameButton = new Button(new TextureRegionDrawable(new TextureRegion(playB)));
        gameButton.setSize( Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        gameButton.setPosition(Gdx.graphics.getWidth() / 2f - gameButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - gameButton.getHeight() / 2f);

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(ScreenFactory.getScreen("CREATE", game, engine));
            }
        });

        //Initialize button to get to SettingsScreen
        settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(  Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        settingsButton.setPosition(Gdx.graphics.getWidth()/4f - settingsButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - settingsButton.getHeight() / 2f);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);

                game.setScreen(ScreenFactory.getScreen("SETTINGS", game, engine));
            }
        });

        //Initialize button to get to HighScoreScreen
        highScoreButton = new Button(new TextureRegionDrawable(new TextureRegion(highScoreB)));
        highScoreButton.setSize( Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        highScoreButton.setPosition(Gdx.graphics.getWidth()/4f*3f - highScoreButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - highScoreButton.getHeight() / 2f);

        highScoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(ScreenFactory.getScreen("HIGHSCORE", game, engine));
            }
        });

    }
}