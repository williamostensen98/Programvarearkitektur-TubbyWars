package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.badlogic.gdx.assets.AssetManager;

public class UsernameScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;

    private Stage stage;

    //Initializing the textures
    private Texture logo;
    private Texture background;
    private Texture playB;

    private Sound click;

    //private Image title;
    private Label user1Text;
    private Label user2Text;

    private TextField user1Input;
    private TextField user2Input;

    //Skin for text input fiels
    //private Skin skin;

    private Vector3 position;

    public UsernameScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        background = Assets.getTexture(Assets.mainBackground);
        logo = Assets.getTexture(Assets.usernameTitle);
        playB = Assets.getTexture(Assets.playButton);

        this.click = game.getClickSound();

        //skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin = new Skin("uiskin.json");

        // one-time operations
        create();
    }

    //Tekstfelt som tar inn brukernavn, ett ord, String, ikke æøå eller andre tegn

    public void create(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        //TODO: Add background to style
        //Sprite sprite = new Sprite();
        //sprite.setColor(Color.WHITE);
        //style.background = new SpriteDrawable(sprite);

        user1Input = new TextField("", style);
        user1Input.setPosition(50 , 50);
        user1Input.setSize(300, 100);
        stage.addActor(user1Input);

        user2Input = new TextField("", style);
        user2Input.setPosition(100, 50);
        user2Input.setSize(300, 100);
        stage.addActor(user2Input);

        position = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8 * 7, 0);
        user1Text = new Label("Player 1 username:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user1Text.setPosition(position.x / 6, position.y * 2 / 3);

        user2Text  = new Label("Player 2 username:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user2Text .setPosition(position.x / 8, position.y * 4 / 7);

        stage.addActor(user1Text);
        stage.addActor(user2Text);

        //Initialiserer button to get GameScreen
        final Button gameButton = new Button(new TextureRegionDrawable(new TextureRegion(playB)));
        gameButton.setSize(60, 60);
        gameButton.setPosition(Gdx.graphics.getWidth() / 2 - gameButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - gameButton.getHeight() / 2);

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds sound
                game.playSound(click);

                if (!user1Input.getText().isEmpty() && !user2Input.getText().isEmpty()) { //TODO: add more requierments (?)
                    //Saves usernames
                    ControllerLogic.username1 = user1Input.getText().toLowerCase();
                    ControllerLogic.username2 = user2Input.getText().toLowerCase();
                    System.out.println("Username 1:" + ControllerLogic.username1);
                    System.out.println("Username 2:" + ControllerLogic.username2);

                    //Sets loggedIn to true, so that Setting Screen changes.
                    ControllerLogic.loggedIn = true;

                    //Goes to game
                    game.setScreen(new GameScreen(game, engine));
                }
                else {
                    System.out.println("Write usernames");
                }
            }

        });

        stage.addActor(gameButton);
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
        game.getBatch().draw(logo, Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2, 200,50); //Draws logo
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