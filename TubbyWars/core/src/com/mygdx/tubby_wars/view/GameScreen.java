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

    private Stage gameStage;

    private List<Entity> players;
    private PlayerSystem playerSystem;
    
    private Entity courseEntity;



    private int turnCounter = 0;
    private boolean loadedNewTurn = false;

    private Texture highScoreB; //TODO: Aucomaticly get here when round is over
    private Texture settingsB;

    public GameScreen(TubbyWars game, Engine engine){
        this.game = game;
        this.engine = engine;
        this.world = new World(engine);

        highScoreB = Assets.getTexture(Assets.highScoreButton);
        settingsB = Assets.getTexture(Assets.pauseGameButton);

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

        //Initialize button to get to HighScoreScreen
        final Button highScoreButton = new Button(new TextureRegionDrawable(new TextureRegion(highScoreB)));
        highScoreButton.setSize(100, 39);
        highScoreButton.setPosition(Gdx.graphics.getWidth()/4f*3f - highScoreButton.getWidth() / 2f , Gdx.graphics.getHeight() / 10f*3f - highScoreButton.getHeight() / 2f);

        highScoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //game.playSound(click);
                game.setScreen(new HighScoreScreen(game, engine));
            }
        });

        //Initialize button to get to SettingsScreen
        final Button settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(50, 50);
        settingsButton.setPosition(Gdx.graphics.getWidth()*85f/90f - settingsButton.getWidth() / 2f , Gdx.graphics.getHeight()* 75f/90f - settingsButton.getHeight() / 2f);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //game.playSound(click);
                game.setScreen(new SettingScreen(game, engine));
            }
        });

        gameStage.addActor(settingsButton);
        gameStage.addActor(highScoreButton);
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
