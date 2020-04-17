package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

public class UsernameScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    private Stage stage;

    //Initializing the textures
    //private Texture logo;
    private Texture background;
    private Texture textField1;
    private Texture textField2;
    private Texture playB;

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
    private Button gameButton;

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


    public UsernameScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;
        this.engine = engine;

        //Getting the right assets
        //logo = Assets.getTexture(Assets.usernameTitle);
        background = Assets.getTexture(Assets.characterBackground);
        textField1 = Assets.getTexture(Assets.textFieldBackground);
        textField2 = Assets.getTexture(Assets.textFieldBackground);
        playB = Assets.getTexture(Assets.playButton);

        //Sprites
        gulTubby = Assets.getTexture(Assets.gulTubby);
        gronnTubby = Assets.getTexture(Assets.gronnTubby);
        rodTubby = Assets.getTexture(Assets.rodTubby);
        lillaTubby = Assets.getTexture(Assets.lillaTubby);

        //Getting rigth sound
        this.click = game.getClickSound();

        // one-time operations
        create();
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

        stage.addActor(gameButton);
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
        game.getBatch().draw(background, 0, 0, TubbyWars.WIDTH, TubbyWars.HEIGHT); //Draws background photo
        game.getBatch().draw(textField1, Gdx.graphics.getWidth() / 14f * 2f, Gdx.graphics.getHeight() / 8f * 5f - textField1.getHeight() / 3f, 150, 20); //Draws logo
        game.getBatch().draw(textField2, Gdx.graphics.getWidth() / 20f * 13f, Gdx.graphics.getHeight() / 8f * 5f - textField2.getHeight() / 3f, 150, 20); //Draws logo
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
    private void makeButtons() {
        //Initializing sprites as buttons
        redTubby = new Button(new TextureRegionDrawable(new TextureRegion(rodTubby)));
        redTubby.setSize(50, 70);
        redTubby.setPosition(Gdx.graphics.getWidth() / 8f * 2f - redTubby.getWidth(), Gdx.graphics.getHeight() / 24f * 7f - redTubby.getHeight() / 2f);
        redTubby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds click effect
                game.playSound(click);

                //Chooses red as character for player 1
                ControllerLogic.character1 = "r";
            }
        });

        purpleTubby = new Button(new TextureRegionDrawable(new TextureRegion(lillaTubby)));
        purpleTubby.setSize(50, 70);
        purpleTubby.setPosition(Gdx.graphics.getWidth() / 8f * 3f - purpleTubby.getWidth(), Gdx.graphics.getHeight() / 24f * 7f - purpleTubby.getHeight() / 2f);
        purpleTubby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds click effect
                game.playSound(click);

                //Chooses purple as character for player 1
                ControllerLogic.character1 = "p";
            }
        });

        yellowTubby = new Button(new TextureRegionDrawable(new TextureRegion(gulTubby)));
        yellowTubby.setSize(50, 70);
        yellowTubby.setPosition(Gdx.graphics.getWidth() / 16f * 11f - yellowTubby.getWidth(), Gdx.graphics.getHeight() / 24f * 7f - yellowTubby.getHeight() / 2f);
        yellowTubby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds click effect
                game.playSound(click);

                //Chooses yellow as character for player 2
                ControllerLogic.character2 = "y";
            }
        });

        greenTubby = new Button(new TextureRegionDrawable(new TextureRegion(gronnTubby)));
        greenTubby.setSize(50, 70);
        greenTubby.setPosition(Gdx.graphics.getWidth() / 16f * 13f - greenTubby.getWidth(), Gdx.graphics.getHeight() / 24f * 7f - greenTubby.getHeight() / 2f);
        greenTubby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds click effect
                game.playSound(click);

                //Chooses green as character for player 2
                ControllerLogic.character2 = "g";
            }
        });

        //Initialiserer button to get GameScreen
        gameButton = new Button(new TextureRegionDrawable(new TextureRegion(playB)));
        gameButton.setSize(100, 50);
        gameButton.setPosition(Gdx.graphics.getWidth() / 2f - gameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 10f - gameButton.getHeight() / 2f);
        gameButton.addListener(new ClickListener() {
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
        user1Input.setPosition(Gdx.graphics.getWidth() / 50f * 11f - user1Input.getWidth() / 5f * 2f, Gdx.graphics.getHeight() / 8f * 5f - user1Input.getHeight() / 2);
        user1Input.setSize(150, 20);

        user2Input = new TextField("", style);
        user2Input.setPosition(Gdx.graphics.getWidth() / 100f * 73f - user2Input.getWidth() / 5f * 2f, Gdx.graphics.getHeight() / 8f * 5f - user1Input.getHeight() / 2);
        user2Input.setSize(150, 20);

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
                && !username2.contains(" ") && !username2.isEmpty() && !username1.equals(username2) && !ControllerLogic.character1.isEmpty()
                && !ControllerLogic.character2.isEmpty()) {

            //Saves usernames
            ControllerLogic.username1 = user1Input.getText().toLowerCase();
            ControllerLogic.username2 = user2Input.getText().toLowerCase();

            //TODO: Remove when connected to database
            System.out.println("Username 1: " + ControllerLogic.username1 + ", character: " + ControllerLogic.character1);
            System.out.println("Username 2: " + ControllerLogic.username2 + ", character: " + ControllerLogic.character2);

            //Sets loggedIn to true, so that Setting Screen changes.
            ControllerLogic.loggedIn = true;

            //Goes to gameScreen
            game.setScreen(new GameScreen(game, engine));
        } else {
            stage.addActor(informationText);
        }
    }
}