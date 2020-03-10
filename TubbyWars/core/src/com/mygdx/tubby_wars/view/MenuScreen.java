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

public class MenuScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    private Label menuText;
    private Texture logo;
    private Texture background;
    private Texture startButton;
    private Texture highScoreButton;
    //private Button start;
    //private Button highscore;
    private Sprite sprite;
    private Stage stage;

    private SpriteBatch sb;

    public MenuScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        // one-time operations
        create();
    }

    public void create(){
        // create the menuscreen here
        logo = new Texture("Logo.png");
        background = new Texture("background.jpg");
        highScoreButton = new Texture("ButtonHighscore.png");
        startButton = new Texture("ButtonStart.png");

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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 1, 1,0);

        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(logo, 200, 270, 400,100);
        sb.draw(startButton, 200,50, 100,100);
        sb.draw(highScoreButton, 500,50, 100,100);
        sb.end();

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
