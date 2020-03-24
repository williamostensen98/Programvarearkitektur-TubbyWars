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

public class SettingScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    private Label settingsText;
    private Stage stage;

    public SettingScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;
        create();
    }

    @Override
    public void create() {
        // create the menuscreen here
        settingsText = new Label("SETTINGS:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        settingsText.setSize(Gdx.graphics.getWidth(), 100);
        settingsText.setPosition(30,300);
        //Virker ikke - Vet ikke hvordan vi skal få til å scale overskriften
        //settingsText.getData().setScale(2, 2);

        stage = new Stage(new ScreenViewport());
        stage.addActor(settingsText);
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(254.0f/255.0f, 127.0f/255.0f, 156.0f/255.0f, 1.0f);
        stage.draw();
    }

    @Override
    public void handleinput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            System.out.println("Enter ble trykket på");
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

