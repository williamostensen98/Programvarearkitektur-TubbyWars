package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;
import com.badlogic.gdx.audio.Sound;

public class ShopScreen extends ScreenAdapter implements ScreenInterface {

    private SpriteBatch sb;

    private TubbyWars game;
    private Engine engine;
    private Stage stage;
    private Texture texture;
    private Image title;

    private Texture menuB;
    private Texture newGameB;

    private Sound click;

    public ShopScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        menuB = Assets.getTexture(Assets.menuScreenButton);
        newGameB = Assets.getTexture(Assets.newGameButton);

        click = game.getClickSound();

        // one-time operations
        create();
    }

    @Override
    public void create() {
        sb = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        //texture = new Texture("textures/shop.png");
        texture = Assets.getTexture(Assets.shopTitle);
        title = new Image(texture);
        title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2, Gdx.graphics.getHeight()/8*7);
        stage.addActor(title);

        Gdx.input.setInputProcessor(stage);

        //Initialiserer button to get GameScreen
        final Button newGameButton = new Button(new TextureRegionDrawable(new TextureRegion(newGameB)));
        newGameButton.setSize(60, 60);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - newGameButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - newGameButton.getHeight() / 2);

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new GameScreen(game, engine));
            }

        });

        //Initialiserer button to get GameScreen
        final Button menuButton = new Button(new TextureRegionDrawable(new TextureRegion(menuB)));
        menuButton.setSize(60, 60);
        menuButton.setPosition(Gdx.graphics.getWidth() / 6 - menuButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - menuButton.getHeight() / 2);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new MenuScreen(game, engine));
            }

        });

        stage.addActor(newGameButton);
        stage.addActor(menuButton);
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
