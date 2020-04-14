package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;

public class ShopScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;
    private Texture texture;
    private Image title;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        // one-time operations
        create();
    }

    @Override
    public void create() {

        stage = new Stage(new ScreenViewport());
        //texture = new Texture("textures/shop.png");
        texture = Assets.getTexture(Assets.shopTitle);
        title = new Image(texture);
        title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2, Gdx.graphics.getHeight()/8*7);
        stage.addActor(title);
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(220.0f/255.0f, 220.0f/255.0f, 220.0f/255.0f, 1.0f);
        stage.draw();
    }

    @Override
    public void handleinput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new MenuScreen(game, engine));
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
