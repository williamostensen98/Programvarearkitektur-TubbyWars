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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

import java.util.ArrayList;

public class HighScoreScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;

    //Textures for title of page
    private Texture titleText;

    private Texture background;
    //Textures for buttons
    private Texture menuScreenB;
    private Texture settingsB;

    //Buttons
    private Button menuScreenButton;
    private Button settingsButton;

    private Sound click;
    private Stage stage;

    private Table highscoreResults;


    public HighScoreScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        //background = Assets.getTexture(Assets.mainBackground);
        titleText = Assets.getTexture(Assets.highscoreTitle);
        menuScreenB = Assets.getTexture(Assets.menuScreenButton);
        settingsB = Assets.getTexture(Assets.settingSignButton);
        background = Assets.getTexture(Assets.highscoreBackground);

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
        stage.addActor(menuScreenButton);

        //Add everything that should only be visible in when playing game
        if (ControllerLogic.loggedIn) {
            stage.addActor(settingsButton);

            //TODO: Add who won the game!
            Label informationText = new Label("The winner is " + "username" +  ". Congratulation!", new Label.LabelStyle(new BitmapFont(), Color.PINK));
            informationText.setPosition(Gdx.graphics.getWidth() / 2f - informationText.getWidth() / 2, Gdx.graphics.getHeight() / 8f * 6f);
            stage.addActor(informationText);

            //TODO: Add score of player 1 and player 2
        }
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
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

    private void makeButtons() {
        //Initialiserer button to get to menuScreen
        menuScreenButton = new Button(new TextureRegionDrawable(new TextureRegion(menuScreenB)));
        menuScreenButton.setSize(100, 50);
        menuScreenButton.setPosition(Gdx.graphics.getWidth() / 6f - menuScreenButton.getWidth() / 2f , Gdx.graphics.getHeight() / 6f - menuScreenButton.getHeight() / 2f);

        menuScreenButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                game.setScreen(new MenuScreen(game, engine));
            }

        });

        //Initialize button to get to SettingsScreen
        settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsB)));
        settingsButton.setSize(50, 50);
        settingsButton.setPosition(Gdx.graphics.getWidth()*85f/90f - settingsButton.getWidth() / 2f , Gdx.graphics.getHeight()* 75f/90f - settingsButton.getHeight() / 2f);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                ControllerLogic.fromHighScoreScreen = true;
                game.setScreen(new SettingScreen(game, engine));
            }
        });
    }

    private void makeTable() {
        //Textfield style
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        //TODO: Use list from database insted, remember to split
        ArrayList<String> listepoint = new ArrayList<String>();
        listepoint.add("10000000");
        listepoint.add("800120");
        listepoint.add("800000");
        listepoint.add("100234");
        listepoint.add("102400");
        listepoint.add("102400");
        listepoint.add("102400");
        listepoint.add("102400");
        listepoint.add("102400");
        listepoint.add("102400");
        listepoint.add("102400");

        ArrayList<String> listename = new ArrayList<String>();
        listename.add("lise");
        listename.add("hanne");
        listename.add("aasne");
        listename.add("stargate1");
        listename.add("winner2");
        listename.add("person23");
        listename.add("person23");
        listename.add("hanne");
        listename.add("username2");
        listename.add("jennyalm");

        highscoreResults =  new Table(); // Table containing the buttons on the screen
        highscoreResults.setPosition(Gdx.graphics.getWidth()/2f + highscoreResults.getWidth(), Gdx.graphics.getHeight()/100f*45f);
        highscoreResults.center();

        for (int i = 0; i<listepoint.size(); i++) {
            TextField rank;
            TextField name;
            TextField score;

            if (i == 0) {
                rank = new TextField("Rank", style);
                name = new TextField("Name", style);
                score = new TextField("Points", style);
            }
            else {
                rank = new TextField(i + ". ", style);
                name = new TextField(listename.get(i - 1), style);
                score = new TextField(listepoint.get(i - 1), style);
            }

            highscoreResults.add(rank);
            highscoreResults.add(name);
            highscoreResults.add(score);
            highscoreResults.getCell(rank).height(20).width(60);
            highscoreResults.getCell(name).height(20).width(150);
            highscoreResults.getCell(score).height(20).width(130);

            highscoreResults.row();
        }
    }
}

