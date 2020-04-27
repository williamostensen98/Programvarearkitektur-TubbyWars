package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.InputProcessor;
import com.mygdx.tubby_wars.controller.MapLoader;
import com.mygdx.tubby_wars.controller.PhysicsSystem;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.B2WorldCreator;
import com.mygdx.tubby_wars.model.CollisionListener;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.PlayerModel;

public class PlayScreen implements Screen {
    private OrthographicCamera gameCam;
    private Viewport viewPort;
    public TubbyWars game;
    private World world;
    private PlayerModel player1, player2;

    //MAP
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    // MAP PROPERTIES
    private int mapPixelWidth;

    private Stage stage;
    private Stage settingsStage;

    // HUD
    private Hud hud;
    private InputMultiplexer inputMultiplexer;
    private float gameCamMaxPosition, gameCamMinPosition;
    private Texture settingsB;
    private Sound click;

    // ASHLEY
    private Engine engine;
    private PlayerSystem ps;
    private ImmutableArray players;
    private PhysicsSystem physicsSystem;
    private Entity physicsEntity;

    /***
     * Contains, updates and renders all of the game logic
     * @param game: Game application
     * @param engine: Ashley engine containing all entities, systems and components
     */
    public PlayScreen(TubbyWars game, Engine engine) {
        this.game = game;
        this.engine = engine;

        ps = engine.getSystem(PlayerSystem.class);
        players = engine.getEntities();
        physicsSystem = engine.getSystem(PhysicsSystem.class);
        physicsEntity = physicsSystem.getEntities().get(0);

        //Camera and viewport of the screen
        gameCam = new OrthographicCamera(TubbyWars.V_WIDTH, TubbyWars.V_HEIGHT);
        viewPort = new StretchViewport(TubbyWars.V_WIDTH, TubbyWars.V_HEIGHT, gameCam);
        viewPort.apply();
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);
        gameCam.update();

        //Button
        settingsB = Assets.getTexture(Assets.pauseGameButton);

        // INITIALIZES NEW WORLD AND STAGE
        world = new World(new Vector2(0, -9.81f), true);
        stage = new Stage();
        settingsStage = new Stage();

        // INITIALIZES PHYSICS AND THE TRAJECTORYACTOR IS ADDED TO THE STAGE.
        TrajectoryActor trajectoryActor = new TrajectoryActor(game, engine);
        stage.addActor(trajectoryActor);

        // LOADS THE MAP
        TmxMapLoader mapLoader = new TmxMapLoader();
        MapLoader loader  = new MapLoader(mapLoader);
        map = loader.getMap(ControllerLogic.roundCount);
        mapRenderer = new OrthogonalTiledMapRenderer(map, 0.01f);

        // MAP AND CAM/VIEW PROPERTIES
        MapProperties properties = map.getProperties();
        int mapWidth = properties.get("width", Integer.class);
        int tilePixelWidth = properties.get("tilewidth", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        gameCamMaxPosition = mapPixelWidth / 100f - gameCam.viewportWidth / 2;
        gameCamMinPosition = gameCam.viewportWidth / 2;

        // INITIALIZES PLAYERS
        player1 = new PlayerOne(world,viewPort.getWorldWidth() / 2  , 1.2f, (Entity) players.get(0), engine);
        player2 = new PlayerTwo(world, mapPixelWidth/100f - viewPort.getWorldWidth() / 2 , 1.2f, (Entity) players.get(1), engine);
        physicsSystem.setPlayer(physicsEntity, player1);

        // Contact listener
        world.setContactListener(new CollisionListener());
        hud = new Hud(game.batch, players);

        createSettingsButton();
        ControllerLogic.currentGame = this;

        click = Assets.getSound(Assets.clickSound);
    }

    //Called when this screen becomes the current screen
    @Override
    public void show() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(settingsStage);
        inputMultiplexer.addProcessor(new InputProcessor(physicsSystem));
        new B2WorldCreator(world, map);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    //Called when the screen should render itself.
    @Override
    public void render(float delta) {
        update(delta);
        //MAP RENDERING
        mapRenderer.render();
        mapRenderer.setView(gameCam);

        // PLAYER RENDERING
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player1.draw(game.batch);
        player2.draw(game.batch);
        game.batch.end();

        // STAGE RENDERING
        game.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        settingsStage.draw();

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

        // PROHIBITS PLAYERS FROM SHOOTING WHILE A BULLET IS ACTIVE
        if(gameCam.position.x == player1.b2Body.getPosition().x || gameCam.position.x == player2.b2Body.getPosition().x){
            if(inputMultiplexer.getProcessors().size == 1){
                inputMultiplexer.addProcessor(new InputProcessor(physicsSystem));
                Gdx.input.setInputProcessor(inputMultiplexer);
            }
        }
        else{
            inputMultiplexer.clear();
            inputMultiplexer.addProcessor(settingsStage);
            Gdx.input.setInputProcessor(inputMultiplexer);
        }

        // CHANGES TURNS IF THE SENTENCE IS FULFILLED
        if(ControllerLogic.isPlayersTurn && player2.getBullet() == null){
            ControllerLogic.isPlayersTurn = false;
        }
        else if(!ControllerLogic.isPlayersTurn && player1.getBullet() == null){
            ControllerLogic.isPlayersTurn = true;
        }

        // LOGIC FOR CAMERA FOLLOWING BALL, RESETTING BALL AND PLAYER AFTER TURN
        if(ControllerLogic.isPlayersTurn){
            physicsSystem.setPlayer(physicsEntity, player2);

            if(bulletOutOfBounds(player2.getBullet())){
                player2.getBullet().destroyBullet();
            }
            else if (checkBulletPosition(player2)) {
                gameCam.position.x = player2.getBullet().b2Body.getPosition().x ;
            }
            else if (checkCameraPosition(player2) ) {
                gameCam.position.x = Math.min(player2.b2Body.getPosition().x, gameCamMaxPosition);
            }
            else if(player2.b2Body.getPosition().x != player2.getPosX()){
                player2.setRedefine();
            }
        }
        else{
            physicsSystem.setPlayer(physicsEntity, player1);

            if(bulletOutOfBounds(player1.getBullet())){
                player1.getBullet().destroyBullet();
            }
            else if (checkBulletPosition(player1)) {
                gameCam.position.x = player1.getBullet().b2Body.getPosition().x;
            }
            else if (checkCameraPosition(player1)) {
                gameCam.position.x = Math.max(player1.b2Body.getPosition().x, gameCamMinPosition);
            }

            else if(player1.b2Body.getPosition().x != player1.getPosX()){
                player1.setRedefine();
            }
        }

        // RESETS PARAMETERS AFTER A ROUND - SENDS TO HIGHSCORE SCREEN IF GAME IS OVER
        if(isRoundOver()){
            if (ControllerLogic.roundCount == 5) {
                game.gsm.changeScreen("HIGHSCORE");
                ControllerLogic.isPlayersTurn = false;
                ControllerLogic.roundCount = 0;
            }
            else {
                ps.setHealth((Entity)players.get(0),150);
                ps.setHealth((Entity)players.get(1),150);
                setTurn();
                game.gsm.changeScreen("SHOP");
            }
        }
    }

    private void setTurn(){
        ps.setScore(ControllerLogic.isPlayersTurn ? (Entity)players.get(1) : (Entity)players.get(0), 500 * ControllerLogic.roundCount);
        float score1 = ps.getScore((Entity)players.get(0));
        float score2 = ps.getScore((Entity)players.get(1));
        ControllerLogic.isPlayersTurn = score1 > score2;

    }
    private boolean isRoundOver(){
        return engine.getSystem(PlayerSystem.class).getHealth((Entity) players.get(0)) < 0
                || engine.getSystem(PlayerSystem.class).getHealth((Entity) players.get(1)) < 0;
    }

    private void createSettingsButton(){
        //Initialize button to get to SettingsScreen
        final Button settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));

        settingsButton.setSize(Gdx.graphics.getWidth()/25f, Gdx.graphics.getWidth()/25f);
        settingsButton.setPosition(Gdx.graphics.getWidth() - (settingsButton.getWidth()*2f) , Gdx.graphics.getHeight() - (settingsButton.getWidth()*2f));

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.gsm.changeScreen("SETTINGS");
            }
        });
        settingsStage.addActor(settingsButton);
    }

    // IF BULLET POS IS BETWEEN THE MAP VIEWPORT EDGES THE CAMERA SHOULD FOLLOW
    private boolean checkBulletPosition(PlayerModel player){
        return (player.getBullet() != null &&
                player.getBullet().b2Body.getPosition().x <= gameCamMaxPosition) &&
                player.getBullet().b2Body.getPosition().x >= gameCamMinPosition;
    }

    // IF BULLET STOPS IN THE MIDDLE OF THE VIEWPORT EDGES THE POSITION OF CAMERA SHOULD BE RESET
    private boolean checkCameraPosition(PlayerModel player){
        return gameCam.position.x >= player1.b2Body.getPosition().x &&
                gameCam.position.x <= player2.b2Body.getPosition().x &&
                player.getBullet() == null;
    }

    // IF BULLET FALLS OUT OF MAP IT SHOULD BE RESET
    private boolean bulletOutOfBounds(Bullet bullet){
        if(bullet != null && (bullet.b2Body.getPosition().x < 0 || bullet.b2Body.getPosition().x > mapPixelWidth / 100f)){
            return true;
        }
        else return bullet != null && bullet.b2Body.getPosition().y < 0;
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

    //Called when this screen should release all resources.
    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        hud.dispose();
    }
}
