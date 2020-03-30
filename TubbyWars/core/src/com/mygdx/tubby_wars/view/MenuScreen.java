package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.badlogic.gdx.audio.Music;
import com.mygdx.tubby_wars.model.Assets;

public class MenuScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    private Label menuText;

    private Texture logo;
    private Texture background;
    private Texture startButton;
    private Texture highScoreButton;
    private Texture settingsButton;

    private Music music;

    private Sprite sprite;
    private SpriteBatch sb;
    private Stage stage;

    public MenuScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.menuBackground);
        logo = Assets.getTexture(Assets.logo);

        this.music = game.getMusic();
        this.music.setVolume(0.3f);
        this.music.play();
        game.playMusic(music);

        // one-time operations
        create();
    }

    public void create(){
        menuText = new Label("Midlertidig; enter to start", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        menuText.setSize(Gdx.graphics.getWidth(), 100);
        menuText.setPosition(5,0);

        stage = new Stage(new ScreenViewport());
        stage.addActor(menuText);
        sb = new SpriteBatch();
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClearColor(0, 1, 1,0);

        sb.begin(); // Draw elements to Sprite Batch
        sb.draw(background, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT);
        //sb.draw(logo, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT);
        sb.end();

        //sb.begin();
        //sb.draw(background, 0,0);
        //sb.draw(logo, 200, 270, 400,100);
        //sb.draw(startButton, 200,50, 100,100);
        //sb.draw(highScoreButton, 500,50, 100,100);
        //sb.draw(settingsButton, 500,50, 100,100);
        //sb.end();

        stage.draw();
    }

    @Override
    public void handleinput(){
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new GameScreen(game, engine));
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
}