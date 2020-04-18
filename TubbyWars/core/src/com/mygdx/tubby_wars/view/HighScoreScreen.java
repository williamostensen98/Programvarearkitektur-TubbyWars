package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

public class HighScoreScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;

    //Textures for title of page
    private Texture titleText;

    //Textures for buttons
    private Texture backB;
    private Texture newGameB;
    private Texture quitGameB;

    //Buttons
    private Button quitGameButton;
    private Button newGameButton;
    private Button menuButton;

    private Sound click;
    private Stage stage;

    private Table highscoreResults;


    public HighScoreScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        //background = Assets.getTexture(Assets.mainBackground);
        titleText = Assets.getTexture(Assets.highscoreTitle);
        backB = Assets.getTexture(Assets.menuScreenButton);
        newGameB = Assets.getTexture(Assets.newGameButton);
        quitGameB = Assets.getTexture(Assets.quitGameButton);

        this.click = game.getClickSound();

        // one-time operations
        create();
    }

    public void create(){

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title text image
        final Image title = new Image(titleText);
        title.setSize(150,75);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        makeButtons();
        makeTable();

        stage.addActor(title);
        stage.addActor(highscoreResults);

        if (ControllerLogic.loggedIn = true) {
           stage.addActor(newGameButton);
           stage.addActor(quitGameButton);
        }
        else {
            stage.addActor(menuButton);
        }
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(187.0f/255.0f, 231.0f/255.0f, 255.0f/255.0f, 1.0f);

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

    private void makeButtons() {
        //Initialiserer quit button, going back to MenuScreen
        quitGameButton = new Button(new TextureRegionDrawable(new TextureRegion(quitGameB)), new TextureRegionDrawable(new TextureRegion(quitGameB)));
        quitGameButton.setSize(100, 50);
        quitGameButton.setPosition(Gdx.graphics.getWidth() / 6f - quitGameButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - quitGameButton.getHeight() / 2f);

        quitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.loggedIn = false; //Quits game
                game.setScreen(new MenuScreen(game, engine));
            }
        });

        //Initialiserer button to get to menuScreen
        menuButton = new Button(new TextureRegionDrawable(new TextureRegion(backB)));
        menuButton.setSize(100, 50);
        menuButton.setPosition(Gdx.graphics.getWidth() / 6f - menuButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - menuButton.getHeight() / 2f);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new MenuScreen(game, engine));
            }

        });

        //Initialiserer button to get GameScreen
        newGameButton = new Button(new TextureRegionDrawable(new TextureRegion(newGameB)));
        newGameButton.setSize(100, 50);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 6f*5f - newGameButton.getWidth() / 2f, Gdx.graphics.getHeight() / 6f - newGameButton.getHeight() / 2f);

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new MenuScreen(game, engine));
            }
        });
    }

    private void makeTable() {
        //Textfield style
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        highscoreResults =  new Table(); // Table containing the buttons on the screen
        highscoreResults.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/100f*60f);

        for (int i=1; i<11;i++){
            TextField tf = new TextField("Plass nr: "+i, style);
            highscoreResults.add(tf);
            highscoreResults.getCell(tf).height(20).width(100);
            highscoreResults.row();
        }
    }
}

