package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

import java.util.ArrayList;
import java.util.List;

public class HighscoreScreen implements Screen {

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
    private List<String> highScore;

    // USER DATA
    private int scorePlayerOne;
    private int scorePlayerTwo;
    private String namePlayerOne;
    private String namePlayerTwo;

    // ASHLEY TOOLS TO GATHER USER DATA
    private PlayerSystem ps;
    private ImmutableArray players;

    public HighscoreScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;

        titleText = Assets.getTexture(Assets.highscoreTitle);
        menuScreenB = Assets.getTexture(Assets.menuScreenButton);
        settingsB = Assets.getTexture(Assets.settingSignButton);
        background = Assets.getTexture(Assets.highscoreBackground);

        click = Assets.getSound(Assets.clickSound);
    }

    private void fetchUserData(){
        // Tools to help fetch user data from playerComponent through the help of playerSystem
        ps = engine.getSystem(PlayerSystem.class);
        players = engine.getEntities();

        // Sets the variables to equal the value in PlayerComponent
        scorePlayerOne = ps.getScore((Entity)players.get(0));
        scorePlayerTwo = ps.getScore((Entity)players.get(1));
        namePlayerOne = ps.getUsername((Entity)players.get(0));
        namePlayerTwo = ps.getUsername((Entity)players.get(1));

        System.out.println("Username: " + namePlayerOne + " and score: " + scorePlayerOne);
        System.out.println("Username: " + namePlayerTwo + " and score: " + scorePlayerTwo);

        // TODO: DETTE KAN DERE OGSÅ GJØRE ET ANNET STED, MEN GJORDE DET BARE HER NÅ. (ALTSÅ addHighscore())
        // Adds user data to database
        addHighscore(namePlayerOne, scorePlayerOne);
        addHighscore(namePlayerTwo, scorePlayerTwo);
    }

    @Override
    public void show() {
        if(ControllerLogic.loggedIn){
            fetchUserData();
        }

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title text image
        final Image title = new Image(titleText);
        title.setSize(Gdx.graphics.getWidth()/7f,  Gdx.graphics.getHeight()/5f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        makeButtons();
        makeTable();

        stage.addActor(title);
        stage.addActor(highscoreResults);
        stage.addActor(menuScreenButton);

        //Add everything that should only be visible in when playing game
        if (ControllerLogic.loggedIn) {
            stage.addActor(settingsButton);

            Label informationText = new Label(this.namePlayerOne + " got " + this.scorePlayerOne
                    + " points, and " + this.namePlayerTwo + " got " + this.scorePlayerTwo +
                    " points. The winner is " + winner() +  ". Congratulations! " +
                    "Did any of you get on the highscore board?", new Label.LabelStyle(new BitmapFont(), Color.PINK));
            informationText.setPosition(Gdx.graphics.getWidth() / 2f - informationText.getWidth() / 2, Gdx.graphics.getHeight() / 8f * 6f);
            stage.addActor(informationText);
        }
    }

    @Override
    public void render(float dt){
        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose(){
        titleText.dispose();
        background.dispose();
        menuScreenB.dispose();
        settingsB.dispose();
        click.dispose();
        stage.dispose();
    }

    private void makeButtons() {
        //Initialiserer button to get to menuScreen
        menuScreenButton = new Button(new TextureRegionDrawable(new TextureRegion(menuScreenB)));
        menuScreenButton.setSize(Gdx.graphics.getWidth()/12f,Gdx.graphics.getHeight()/10f);
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
        settingsButton.setSize(Gdx.graphics.getWidth()/24f   ,   Gdx.graphics.getHeight()/13f);
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

        //Adding data to highscore list from database
        this.highScore= this.game.backendConn.getTopTen();
        ArrayList<String> listpoint = new ArrayList<>();
        ArrayList<String> listname = new ArrayList<>();
        for (int i=0; i<highScore.size(); i++){
            String[] names=highScore.get(i).split(",");
            listname.add(names[0]);
            listpoint.add(names[1]);
        }

        highscoreResults =  new Table(); // Table containing the buttons on the screen
        highscoreResults.setPosition(Gdx.graphics.getWidth()/2f + highscoreResults.getWidth(), Gdx.graphics.getHeight()/100f*45f);
        highscoreResults.center();

        for (int i = 0; i<listpoint.size(); i++) {
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
                name = new TextField(listname.get(i - 1), style);
                score = new TextField(listpoint.get(i - 1), style);
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

    //posts username and score to database
    private void addHighscore(String name, int score){
                this.game.backendConn.addResult(name,score);
    }

    private String winner() {
        if (this.scorePlayerOne< this.scorePlayerTwo){
            return this.namePlayerTwo;
        }
        else if (this.scorePlayerOne> this.scorePlayerTwo){
            return this.namePlayerOne;
        }
        else{
            return "no one (tie)";
        }
    }

}