package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.CourseSystem;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.World;

import java.util.ArrayList;
import java.util.List;

public class CharacterCreationScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Stage stage;

    //Textures
    private Texture background;
    private Texture textField1;
    private Texture textField2;
    private Texture shopB;

    //Sprites
    private Texture gulTubby;
    private Texture gronnTubby;
    private Texture rodTubby;
    private Texture lillaTubby;

    //Initializing Clicking sound when pressing button
    private Sound click;

    //Initializing buttons
    private Button yellowTubby;
    private Button greenTubby;
    private Button purpleTubby;
    private Button redTubby;
    private Button shopButton;

    //Labels with text
    private Label user1Text;
    private Label user2Text;
    private Label informationText;

    // Choose sprite text
    private Label leftText;
    private Label rightText;

    //Initiializing TextFields for username input
    private TextField user1Input;
    private TextField user2Input;


    // ASHLEY COMPONENTS

    // the engine keeps track of the entities and manages the entity systems
    private Engine engine;
    // World is used to create players and course.
    private World ashleyWorld;

    private List<Entity> players;
    private Entity courseEntity;
    private PlayerSystem playerSystem;

    public CharacterCreationScreen(TubbyWars game) {
        super();
        this.game = game;

        //Getting the right assets
        //logo = Assets.getTexture(Assets.usernameTitle);
        background = Assets.getTexture(Assets.characterBackground);
        textField1 = Assets.getTexture(Assets.textFieldBackground);
        textField2 = Assets.getTexture(Assets.textFieldBackground);
        shopB = Assets.getTexture(Assets.gameScreenButton);

        //Sprites
        gulTubby = Assets.getTexture(Assets.gulTubby);
        gronnTubby = Assets.getTexture(Assets.gronnTubby);
        rodTubby = Assets.getTexture(Assets.rodTubby);
        lillaTubby = Assets.getTexture(Assets.lillaTubby);

        //Getting right sound
        this.click = game.getClickSound();

        // one-time operations
        setupAshley();
        create();
    }

    public void setupAshley(){
        engine = new Engine();
        ashleyWorld = new World(engine);

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

    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Initialize title logo
        // final Image usernameTile = new Image(logo);
        // usernameTile.setSize(150,75);
        //usernameTile.setPosition(Gdx.graphics.getWidth()/2f - usernameTile.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - usernameTile.getHeight()/2f);

        makeButtons();
        makeLabels();
        makeTextFields();

        //Add actors
        stage.addActor(user1Input);
        stage.addActor(user2Input);
        stage.addActor(user1Text);
        stage.addActor(user2Text);
        stage.addActor(leftText);
        stage.addActor(rightText);

        stage.addActor(shopButton);
        stage.addActor(yellowTubby);
        stage.addActor(greenTubby);
        stage.addActor(redTubby);
        stage.addActor(purpleTubby);
    }

    @Override
    public void update(float dt) {
        // check for user input
        handleinput();
    }

    @Override
    public void draw() {
        // Draw elements to Sprite Batch, textFields and Background
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().draw(textField1, Gdx.graphics.getWidth() / 14f * 2f, Gdx.graphics.getHeight() / 8f * 5f - textField1.getHeight() / 3f, Gdx.graphics.getWidth()/5f, Gdx.graphics.getHeight()/15f); //Draws logo
        game.getBatch().draw(textField2, Gdx.graphics.getWidth() / 20f * 13f, Gdx.graphics.getHeight() / 8f * 5f - textField2.getHeight() / 3f, Gdx.graphics.getWidth()/5f, Gdx.graphics.getHeight()/15f); //Draws logo
        game.getBatch().end();

        stage.draw();
    }

    @Override
    public void handleinput() {
    }

    @Override
    public void render(float dt) {
        update(dt);
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    //Helpingfunctions
    private ClickListener clickListener(final Entity playerEntity, final Texture tubby){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos){
                game.playSound(click);
                playerSystem.setTexture(playerEntity, tubby);
            }
        };
    }

    private void makeButtons() {

        //Initializing sprites as buttons
        redTubby = new Button(new TextureRegionDrawable(new TextureRegion(rodTubby)));
        redTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
        redTubby.setPosition(Gdx.graphics.getWidth() / 100f * 25f - redTubby.getWidth(), Gdx.graphics.getHeight() / 100f * 32f - redTubby.getHeight() / 2f);
        redTubby.addListener(clickListener(players.get(0), rodTubby));

        purpleTubby = new Button(new TextureRegionDrawable(new TextureRegion(lillaTubby)));
        purpleTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
        purpleTubby.setPosition(Gdx.graphics.getWidth() / 100f * 39f - purpleTubby.getWidth(), Gdx.graphics.getHeight() / 100f * 32f - purpleTubby.getHeight() / 2f);
        purpleTubby.addListener(clickListener(players.get(0), lillaTubby));

        yellowTubby = new Button(new TextureRegionDrawable(new TextureRegion(gulTubby)));
        yellowTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
        yellowTubby.setPosition(Gdx.graphics.getWidth() / 100f * 75f - yellowTubby.getWidth(), Gdx.graphics.getHeight() / 100f * 32f - yellowTubby.getHeight() / 2f);
        yellowTubby.addListener(clickListener(players.get(1), gulTubby));

        greenTubby = new Button(new TextureRegionDrawable(new TextureRegion(gronnTubby)));
        greenTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
        greenTubby.setPosition(Gdx.graphics.getWidth() / 100f * 89f - greenTubby.getWidth(), Gdx.graphics.getHeight() / 100f * 32f - greenTubby.getHeight() / 2f);
        greenTubby.addListener(clickListener(players.get(1), gronnTubby));

        //Initialiserer button to get GameScreen
        shopButton = new Button(new TextureRegionDrawable(new TextureRegion(shopB)));
        shopButton.setSize( Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        shopButton.setPosition(Gdx.graphics.getWidth() / 2f - shopButton.getWidth() / 2f, Gdx.graphics.getHeight() / 10f - shopButton.getHeight() / 2f);
        shopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds click effect
                game.playSound(click);
                //Cheks if players are ready to continue
                usernameCheck();
            }
        });
    }

    private void makeTextFields() {
        //Making style for TextField
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        //Placing textFields for username input
        user1Input = new TextField("", style);
        user1Input.setSize(Gdx.graphics.getWidth()/6f, Gdx.graphics.getHeight()/15f);
        user1Input.setPosition(Gdx.graphics.getWidth() / 50f * 11f - user1Input.getWidth()/2.7f, Gdx.graphics.getHeight() / 100f * 63f - user1Input.getHeight() / 2);


        user2Input = new TextField("", style);
        user2Input.setSize(Gdx.graphics.getWidth()/6f, Gdx.graphics.getHeight()/15f);
        user2Input.setPosition(Gdx.graphics.getWidth() / 100f * 73f - user2Input.getWidth() / 5f * 2f, Gdx.graphics.getHeight() / 100f * 63f - user2Input.getHeight() / 2);

    }

    private void makeLabels() {
        //Placing text
        user1Text = new Label("Player 1:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user1Text.setPosition(Gdx.graphics.getWidth() / 11f - user1Text.getWidth() / 2, Gdx.graphics.getHeight() / 8f * 5f - user1Text.getHeight() / 2);

        user2Text = new Label("Player 2:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user2Text.setPosition(Gdx.graphics.getWidth() / 5f * 3f - user2Text.getWidth() / 2, Gdx.graphics.getHeight() / 8f * 5f - user2Text.getHeight() / 2);

        informationText = new Label("Error: Write usernames without æ, ø, å or ' ', and they can not be identical. Also remember to choose a character",
                new Label.LabelStyle(new BitmapFont(), Color.PINK));
        informationText.setPosition(Gdx.graphics.getWidth() / 2f - informationText.getWidth() / 2, Gdx.graphics.getHeight() / 8f * 6f);

        //Info text for choosing character
        leftText = new Label("Choose character:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        leftText.setPosition(Gdx.graphics.getWidth() / 11f - leftText.getWidth() / 4, Gdx.graphics.getHeight() / 8f * 4f - leftText.getHeight() / 2);

        rightText = new Label("Choose character:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        rightText.setPosition(Gdx.graphics.getWidth() / 5f * 3f - rightText.getWidth() / 4, Gdx.graphics.getHeight() / 8f * 4f - rightText.getHeight() / 2);

    }

    //Checks if username is correctly written
    private void usernameCheck() {
        String username1 = user1Input.getText();
        String username2 = user2Input.getText();

        //Checks that usernames does not contain æøå or space
        if (!username1.isEmpty() && !username1.contains("æ") && !username1.contains("ø") && !username1.contains("å")
                && !username1.contains(" ") && !username1.contains("æ") && !username2.contains("ø") && !username2.contains("å")
                && !username2.contains(" ") && !username2.isEmpty() && !username1.equals(username2)
                && playerSystem.getTexture(players.get(0)) != null && playerSystem.getTexture(players.get(1)) != null) {

            //Saves usernames
            ControllerLogic.username1 = user1Input.getText().toLowerCase();
            ControllerLogic.username2 = user2Input.getText().toLowerCase();

            // saves username in playerComponent
            playerSystem.setUsername(players.get(0),username1);
            playerSystem.setUsername(players.get(1),username2);

            //TODO: Remove when connected to database
            System.out.println("Username 1: " + playerSystem.getUsername(players.get(0)) + ", character: " + playerSystem.getTexture(players.get(0)));
            System.out.println("Username 2: " + playerSystem.getUsername(players.get(1)) + ", character: " + playerSystem.getTexture(players.get(1)));

            //Sets loggedIn to true, so that Setting Screen changes.
            ControllerLogic.loggedIn = true;

            //Goes to gameScreen
            game.setScreen(new ShopScreen(game, engine, players));
        } else {
            stage.addActor(informationText);
        }
    }
}