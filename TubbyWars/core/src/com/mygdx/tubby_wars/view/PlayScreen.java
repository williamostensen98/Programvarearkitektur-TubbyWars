package com.mygdx.tubby_wars.view;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.InputProcessor;
import com.mygdx.tubby_wars.controller.Physics;
import com.mygdx.tubby_wars.model.B2WorldCreator;
import com.mygdx.tubby_wars.model.ControllerLogic;


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
    private Physics physics;
    private Stage stage;

    // HUD
    private Hud hud;

    private InputMultiplexer inputMultiplexer;
    public static TextureAtlas atlas;

    public PlayScreen(TubbyWars game) {
        this.game = game;
        gameCam = new OrthographicCamera(TubbyWars.V_WIDTH, TubbyWars.V_HEIGHT);
        viewPort = new FitViewport(TubbyWars.V_WIDTH, TubbyWars.V_HEIGHT);
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);
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
        map = mapLoader.load("map.tmx");
        mapRenderer =  new OrthogonalTiledMapRenderer(map, 0.01f);
        b2dr = new Box2DDebugRenderer();

        // MAP PROPERTIES
        MapProperties properties = map.getProperties();
        mapWidth = properties.get("width", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;

        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        // ADDS THE PLAYERS
        player1 = new Player(world, game, 2, 0.64f, false);
        player2 = new Player(world, game, viewPort.getWorldWidth() - 2, 0.64f, true);
        physics.setPlayer(player1);
        // LOADS THE PACK FILE WITH INTO AN ATLAS WHERE ALL THE CHARACTER SPRITES ARE

        hud = new Hud(game.batch);

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

        player1.healthbar.draw();
        player2.healthbar.draw();


    }

    public void update(float dt){
        world.step(1/60f, 6, 2);
        gameCam.update();
        player1.update(dt);
        player2.update(dt);
        hud.update(dt);

        if(ControllerLogic.isPlayersTurn){
            physics.setPlayer(player2);
        }
        else {
            physics.setPlayer(player1);
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
