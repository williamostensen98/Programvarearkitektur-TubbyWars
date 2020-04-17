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

public class UsernameScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;

    private Stage stage;

    //Initializing the textures
    private Texture logo;
    private Texture background;
    private Texture textField1;
    private Texture textField2;
    private Texture playB;


    //Initializing Clicking sound when pressing button
    private Sound click;

    //Labels with text
    private Label user1Text;
    private Label user2Text;
    private Label informationText;

    //Initiializing TextFields for username input
    private TextField user1Input;
    private TextField user2Input;

    public UsernameScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        //Getting the right assets
        background = Assets.getTexture(Assets.mainBackground);
        logo = Assets.getTexture(Assets.usernameTitle);
        textField1 = Assets.getTexture(Assets.textFieldBackground);
        textField2 = Assets.getTexture(Assets.textFieldBackground);
        playB = Assets.getTexture(Assets.playButton);

        //Getting rigth sound
        this.click = game.getClickSound();

        // one-time operations
        create();
    }

    public void create(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Making style for TextField
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;
        //TODO: Add boxes behind TextField

        //Placing textFields for username input TODO: Place better
        user1Input = new TextField("", style);
        user1Input.setPosition(Gdx.graphics.getWidth() / 3f , Gdx.graphics.getHeight() / 3f);
        user1Input.setSize(300, 100);
        stage.addActor(user1Input);

        user2Input = new TextField("", style);
        user2Input.setPosition(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 4f);
        user2Input.setSize(300, 100);
        stage.addActor(user2Input);

        //Placing text
        user1Text = new Label("Player 1 username:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user1Text.setPosition(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight()/3f);

        user2Text  = new Label("Player 2 username:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user2Text .setPosition(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 4f);

        informationText = new Label("Write usernames without æ, ø, å or ' ', then press the button",
                new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        informationText .setPosition(Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() / 2f);

        stage.addActor(user1Text);
        stage.addActor(user2Text);

        //Initialiserer button to get GameScreen
        final Button gameButton = new Button(new TextureRegionDrawable(new TextureRegion(playB)));
        gameButton.setSize(60, 60);
        gameButton.setPosition(Gdx.graphics.getWidth() / 2f - gameButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - gameButton.getHeight() / 2f);

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds sound
                game.playSound(click);

                usernameCheck();
            }

        });

        stage.addActor(gameButton);
    }

    //Checks if username is correctly written
    private void usernameCheck() {
        String username1 = user1Input.getText();
        String username2 = user2Input.getText();

        //Checks that usernames does not contain æøå or space
        if (!username1.isEmpty() && !username1.contains("æ") && !username1.contains("ø") && !username1.contains("å")
                && !username1.contains(" ") && !username1.contains("æ") && !username2.contains("ø") && !username2.contains("å")
                && !username2.contains(" ") && !username2.isEmpty()) {

            //Saves usernames
            ControllerLogic.username1 = user1Input.getText().toLowerCase();
            ControllerLogic.username2 = user2Input.getText().toLowerCase();

            //TODO: Remove when connected to database
            System.out.println("Username 1:" + ControllerLogic.username1);
            System.out.println("Username 2:" + ControllerLogic.username2);

            //Sets loggedIn to true, so that Setting Screen changes.
            ControllerLogic.loggedIn = true;

            //Goes to gameScreen
            game.setScreen(new GameScreen(game, engine));
        }
        else {
            stage.addActor(informationText);
        }
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        game.getBatch().begin(); // Draw elements to Sprite Batch
        game.getBatch().draw(background, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT); //Draws background photo
        game.getBatch().draw(logo, Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getHeight()/2f, 200,50); //Draws logo
        game.getBatch().draw(textField1, Gdx.graphics.getWidth() / 3f , Gdx.graphics.getHeight() / 3f, 300,100); //Draws logo
        game.getBatch().draw(textField2, Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 4f, 300,100); //Draws logo
        game.getBatch().end();

        stage.draw();
    }

    @Override
    public void handleinput(){
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