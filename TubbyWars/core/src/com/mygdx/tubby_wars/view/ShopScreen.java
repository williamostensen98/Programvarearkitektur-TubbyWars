package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;

public class ShopScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Vector3 pos;

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

        batch = new SpriteBatch();
        texture = new Texture("shop.png");
        sprite = new Sprite(texture);
        pos = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2, 0);
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
        batch.begin();
        batch.draw(sprite,
                pos.x - sprite.getWidth() / 2,
                pos.y - sprite.getHeight() / 2);
        batch.end();
        update(dt);
        draw();
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
