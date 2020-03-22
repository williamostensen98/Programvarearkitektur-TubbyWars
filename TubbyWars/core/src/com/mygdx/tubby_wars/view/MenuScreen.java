package com.mygdx.tubby_wars.view;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;


public class MenuScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    private Label menuText;
    private Stage stage;


    public MenuScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        // one-time operations
        create();
    }

    public void create(){
        // create the menuscreen here
        menuText = new Label("Welcome to TubbyWars, hit enter to start", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        menuText.setSize(Gdx.graphics.getWidth(), 100);
        menuText.setPosition(200,200);

        stage = new Stage(new ScreenViewport());
        stage.addActor(menuText);
    }


    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
