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

    //Initialize  background music and sound effects
    private Sound click;
    public String type;

    private Stage stage;

    public MenuScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;

        background = Assets.getTexture(Assets.mainBackground);
        logo = Assets.getTexture(Assets.logo);
        playB = Assets.getTexture(Assets.gameScreenButton);
        highScoreB = Assets.getTexture(Assets.highScoreButton);
        settingsB = Assets.getTexture(Assets.settingScreenButton);

        Music music = Assets.getMusic(Assets.backgroundMusic);
        this.game.playMusic(music);

        click = Assets.getSound(Assets.clickSound);

        type = "MENU";
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title logo
        final Image title = new Image(logo);
        title.setSize(Gdx.graphics.getWidth()/4f,  Gdx.graphics.getHeight()/3f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/2f);


        Button settingsButton = makeButton(settingsB,4f,"SETTINGS");
        Button gameButton = makeButton(playB,2f,"CREATE");
        Button highScoreButton = makeButton(highScoreB,1.33f,"HIGHSCORE");

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

    private Button makeButton(Texture texture, float xPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.gsm.changeScreen(nextScreen);
            }
        });
        return b;
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


}