package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.CourseSystem;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.World;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

import java.util.List;

public class GameScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;
    private World world;

    private Stage gameStage;

    private List<Entity> players;
    private PlayerSystem playerSystem;
    
    private Entity courseEntity;

    private CourseView cv;

    private int turnCounter = 0;
    private boolean loadedNewTurn = false;


    public GameScreen(TubbyWars game, Engine engine){
        this.game = game;
        this.engine = engine;
        this.world = new World(engine);

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
        cv = new CourseView(game, courseEntity);
        
        playerSystem = engine.getSystem(PlayerSystem.class);
        

    }

    @Override
    public void update(float dt) {
        checkEndTurn();
        handleinput();
        engine.update(dt);
        gameStage.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);

        // draws CourseView, both players, course background, etc...
        cv.draw();

        // Draws buttons, have yet to implement them
        // gameStage.draw();
    }

    @Override
    public void handleinput() {
        // create a new MenuScreen and set the screen to that, Should perhaps not create a new one, but use the previous
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MenuScreen(game, engine));
        }

        // quick test of going to the next player, gets removed later
        if(Gdx.input.isKeyPressed(Input.Keys.N)){
            endTurn(courseEntity);
        }

        // Send updates about how to draw the aim arrow
        if(Gdx.input.isTouched()){
            if(playerSystem.getIsYourTurn(players.get(0))){
                playerSystem.setAimArrow(players.get(0), Gdx.input.getDeltaX() * -1, Gdx.input.getDeltaY());
            }
            if(playerSystem.getIsYourTurn(players.get(1))){
                playerSystem.setAimArrow(players.get(1), Gdx.input.getDeltaX() * -1, Gdx.input.getDeltaY());
            }
        }

        // shoot
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){

            // if player one's turn and player one has not shot yet
            if(playerSystem.getIsYourTurn(players.get(0)) && !playerSystem.getHasFired(players.get(0))){
                playerSystem.setInitBulletPos(players.get(0), 100, 75);
                playerSystem.setHasFired(players.get(0),true);
                playerSystem.setBulletPos(players.get(0));

            }

            // if player two's turn and player two has not shot yet
            if(playerSystem.getIsYourTurn(players.get(1)) && !playerSystem.getHasFired(players.get(1))){
                playerSystem.setInitBulletPos(players.get(1), TubbyWars.WIDTH - 100, 75);
                playerSystem.setHasFired(players.get(1),true);
                playerSystem.setBulletPos(players.get(1));

            }
        }

        // if player one's turn and player one has fired
        if(playerSystem.getIsYourTurn(players.get(0)) && playerSystem.getHasFired(players.get(0))){
            playerSystem.setBulletPos(players.get(0));


        }

        // if player two's turn and player one has fired
        if(playerSystem.getIsYourTurn(players.get(1)) && playerSystem.getHasFired(players.get(1))){
            playerSystem.setBulletPos(players.get(1));

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

    public void endTurn(Entity courseEntity){
        if(playerSystem.getIsYourTurn(players.get(0))){
            playerSystem.setIsYourTurn(players.get(0), false);
            playerSystem.setIsYourTurn(players.get(1), true);
        }
        else{
            playerSystem.setIsYourTurn(players.get(1), false);
            playerSystem.setIsYourTurn(players.get(0), true);
        }
        turnCounter = 0;
    }

    public void checkEndTurn(){
        if(playerSystem.getShotCounter(players.get(0)) == 3 && playerSystem.getIsYourTurn(players.get(0))){
            playerSystem.setShotCounter(players.get(0));
            endTurn(courseEntity);
        }
        else if(playerSystem.getShotCounter(players.get(1)) == 3 && playerSystem.getIsYourTurn(players.get(1))){
            playerSystem.setShotCounter(players.get(1));
            endTurn(courseEntity);
        }
    }
}
