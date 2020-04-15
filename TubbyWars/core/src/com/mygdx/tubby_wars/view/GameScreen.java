package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.CourseSystem;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.World;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

import java.util.List;


/**
 * NB: DENNE HAR INGEN HENSIKT Å HA LENGER, MEN VENTER MED Å FJERNE TIL VI HAR FÅTT ORDEN PÅ ELEMENTER SOM KAN BRUKES HERIFRA I PLAYSCREEN. FEKS SETTINGS KNAPP
 * */

public class GameScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;
    private World world;

    private Texture settingsB;

    private Stage gameStage;

    private List<Entity> players;
    private PlayerSystem playerSystem;
    
    private Entity courseEntity;



    private int turnCounter = 0;
    private boolean loadedNewTurn = false;


    public GameScreen(TubbyWars game, Engine engine){
        this.game = game;
        this.engine = engine;
        this.world = new World(engine);

        settingsB = Assets.getTexture(Assets.settingsButton);

        // one-time operations that occur when creating a new game
        create();
    }

    @Override
    public void create() {
        // Create stage used by buttons, no buttons atm
        gameStage = new Stage(new ScreenViewport());
        // set stage able to handle input, e.g. buttons/input fields
        Gdx.input.setInputProcessor(gameStage);

        // create players and course
        players = world.createPlayers();
        courseEntity = world.createCourse();

        // adds player and course system (controller) to the engine
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new CourseSystem());
        engine.getSystem(CourseSystem.class).addPlayers(courseEntity, players);

        // creates the CourseView

        
        playerSystem = engine.getSystem(PlayerSystem.class);

        //Initialiserer button to get GameScreen
        final Button settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(60, 60);
        settingsButton.setPosition(Gdx.graphics.getWidth() - 100 - settingsButton.getWidth() / 2 , Gdx.graphics.getHeight()- 50 - settingsButton.getHeight() / 2);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.setScreen(new SettingScreen(game, engine));
            }

        });
        gameStage.addActor(settingsButton);
    }

    @Override
    public void update(float dt) {
        handleinput();
        engine.update(dt);
        gameStage.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);

        // draws CourseView, both players, course background, etc...


        // Draws buttons, have yet to implement them
        // gameStage.draw();
        gameStage.draw();
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
        super.dispose();
    }




}
