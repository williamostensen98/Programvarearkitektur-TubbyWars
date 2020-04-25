package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.ScreenFactory;
import com.mygdx.tubby_wars.model.Assets;

public class LoadingScreen implements Screen {

    private TubbyWars game;

    private float progress;

    private Stage stage;

    public String type;

    public LoadingScreen(TubbyWars game, Engine engine){
        this.game = game;
        Assets.load();
    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        this.progress = 0f;

        Label loadingText = new Label("Loading...", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        loadingText.setPosition(Gdx.graphics.getWidth()/2f - loadingText.getWidth()/2f,
                Gdx.graphics.getHeight()/2f);

        stage.addActor(loadingText);
    }

    @Override
    public void render(float dt){
        progress = MathUtils.lerp(progress, Assets.getProgress(), .1f);
        if (Assets.update() && progress >= Assets.getProgress() - 0.001f) {
            dispose();
            game.gsm.changeScreen("MENU");
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(187.0f/255.0f, 231.0f/255.0f, 255.0f/255.0f, 1.0f);
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
    public void dispose(){

    }

}
