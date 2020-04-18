package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;

public class LoadingScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;

    private float progress;

    private Label loadingText;
    private Stage stage;


    public LoadingScreen(TubbyWars game, Engine engine){
        this.game = game;
        this.engine = engine;
        create();
    }

    @Override
    public void create(){
        loadingText = new Label("Loading...", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        loadingText.setSize(Gdx.graphics.getWidth(), 100);
        loadingText.setPosition(200,200);

        stage = new Stage(new ScreenViewport());
        stage.addActor(loadingText);
    }

    @Override
    public void update(float dt) {
        progress = MathUtils.lerp(progress, Assets.getProgress(), .1f);
        if (Assets.update() && progress >= Assets.getProgress() - 0.001f) {
            game.setScreen(new MenuScreen(game, engine));
        }
    }

    @Override
    public void show(){
        this.progress = 0f;
        Assets.load();

    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(187.0f/255.0f, 231.0f/255.0f, 255.0f/255.0f, 1.0f);
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

    }

}
