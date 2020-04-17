package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.CourseSystem;
import com.mygdx.tubby_wars.controller.InputProcessor;
import com.mygdx.tubby_wars.controller.Physics;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.B2WorldCreator;
import com.mygdx.tubby_wars.model.ControllerLogic;

import java.util.List;


public class PlayScreen implements Screen {


    public OrthographicCamera gameCam;
    public Viewport viewPort;
    public TubbyWars game;
    public World world;
    public Player player1, player2;

    //MAP
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Box2DDebugRenderer b2dr;

    // MAP PROPERTIES
    private int mapPixelWidth;
    private int mapWidth;
    private int tilePixelWidth;

    private TrajectoryActor trajectoryActor;
    public Physics physics;
    private Stage stage;

    // HUD
    private Hud hud;

    private InputMultiplexer inputMultiplexer;
    public static TextureAtlas atlas;

    public float position_player1, position_player2;



    // ASHLEY, MIGHT WANT TO MOVE THE CREATION OF THIS TO THE USERNAME SCREEN

    // the engine keeps track of the entities and manages the entity systems
    private Engine engine;
    // World.java in model, used to create players and course.
    private com.mygdx.tubby_wars.model.World ashleyWorld;

    private List<Entity> players;
    private Entity courseEntity;
    private PlayerSystem playerSystem;


    public PlayScreen(TubbyWars game) {
        this.game = game;

        // RUNS ALL THE ASHLEY STUFF, THIS COULD BE MOVED TO USERNAME SCREEN
        setupAshley();


        gameCam = new OrthographicCamera(TubbyWars.V_WIDTH, TubbyWars.V_HEIGHT);
        viewPort = new StretchViewport(TubbyWars.V_WIDTH, TubbyWars.V_HEIGHT, gameCam);
        viewPort.apply();
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);
        //gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

        gameCam.update();

        // INITIALIZES NEW WORLD AND STAGE
        world = new World(new Vector2(0, -9.81f), true);
        stage = new Stage();

        // INITIALIZES PHYSICS AND THE TRAJECTORYACTOR IS ADDED TO THE STAGE.
        physics = new Physics();
        trajectoryActor = new TrajectoryActor(game, physics);
        stage.addActor(trajectoryActor);

        // LOADS THE MAP
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map2.tmx");
        mapRenderer =  new OrthogonalTiledMapRenderer(map, 0.01f);
        b2dr = new Box2DDebugRenderer();

        // MAP PROPERTIES
        MapProperties properties = map.getProperties();
        mapWidth = properties.get("width", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;

        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        // ADDS THE PLAYERS
        player1 = new Player(world, game,viewPort.getWorldWidth() / 2 , 0.64f, false, players.get(0));
        player2 = new Player(world, game, mapPixelWidth/100f - viewPort.getWorldWidth() / 2 - 2, 0.64f, true, players.get(1));
        physics.setPlayer(player1);
        // LOADS THE PACK FILE WITH INTO AN ATLAS WHERE ALL THE CHARACTER SPRITES ARE

        hud = new Hud(game.batch, players);




    }

    public void setupAshley(){
        engine = new Engine();
        ashleyWorld = new com.mygdx.tubby_wars.model.World(engine);

        // ADDS SYSTEMS TO THE ENGINE
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new CourseSystem());

        // CREATE PLAYERS AND COURSE
        players = ashleyWorld.createPlayers();
        courseEntity = ashleyWorld.createCourse();

        // CONNECT PLAYERS TO THE COURSE, (NOT CRUCIAL ATM)
        engine.getSystem(CourseSystem.class).addPlayers(courseEntity, players);

        // if we want to use functions from playerSystem, use the following
        // playerSystem.thefunction(players.get(0)), 0 for player 1 and 1 for player 2
        playerSystem = engine.getSystem(PlayerSystem.class);
    }


    public void setGameCamPosition(){
        if(ControllerLogic.isPlayersTurn){
            gameCam.position.x = player2.b2Body.getPosition().x;
        }
        else{
            gameCam.position.x = player1.b2Body.getPosition().x;
        }
    }
    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void changeTurn(){
        if(ControllerLogic.isPlayersTurn){
            physics.setPlayer(player2);
        }
        else{
            physics.setPlayer(player1);
        }
    }

    /**
     * Called when this screen becomes the current screen for a
     */
    @Override
    public void show() {
        // TODO ADD B2WORLDCREATOR


        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputProcessor(physics));
        // TODO ADD THE STAGES TO THE MULTIPLEXER
        new B2WorldCreator(world, map);
        Gdx.input.setInputProcessor(inputMultiplexer);

        // SET PLAYER1's TURN.

        position_player2 = player2.b2Body.getPosition().x;
        position_player1 = player1.b2Body.getPosition().x;



    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        update(delta);
        //MAP RENDERING
        mapRenderer.render();
        mapRenderer.setView(gameCam);

        b2dr.render(world, gameCam.combined);

        // PLAYER RENDERING
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player1.draw(game.batch);
        player2.draw(game.batch);
        game.batch.end();


        // STAGE RENDERING
        game.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();



    }

    public void update(float dt) {
        world.step(1 / 60f, 6, 2);
        gameCam.update();
        player1.update(dt);
        player2.update(dt);
        hud.update(dt);
        if(ControllerLogic.isPlayersTurn){
            physics.setPlayer(player2);
            //System.out.println( player2.b2Body.getPosition());
        }
        else{
            physics.setPlayer(player1);
        }


        if(player1.isPlayersTurn()) {
            //gameCam.position.x = position_player1;

            if (player1.getBullet() != null && (player1.getBullet().b2Body.getPosition().x < mapPixelWidth / 100f - gameCam.viewportWidth / 2)) {
                gameCam.position.x = player1.getBullet().b2Body.getPosition().x;
            }
        }
        else if(player2.isPlayersTurn()){

            //gameCam.position.x = player2.b2Body.getPosition().x;
            //gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

            if ((player2.getBullet().b2Body.getPosition().x > gameCam.viewportWidth / 2)) {
                gameCam.position.x = player2.getBullet().b2Body.getPosition().x;
            }

        }





    }


    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
        gameCam.update();
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

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }

}
