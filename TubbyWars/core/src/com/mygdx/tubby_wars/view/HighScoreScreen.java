package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;

public class HighScoreScreen extends ScreenAdapter implements ScreenInterface{

    private TubbyWars game;
    private Engine engine;

    //Initializing the textures
    private Texture backB;
    private Texture background;

    //Initializing the background music
    private Music music;

    private Sprite sprite;
    private SpriteBatch sb;
    private Stage stage;


    Table table = new Table();

    public HighScoreScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;


        background = Assets.getTexture(Assets.mainBackground);
        backB = Assets.getTexture(Assets.backButton);

        this.music = game.getMusic();
        this.music.setVolume(0.3f);
        this.music.play();
        game.playMusic(music);

        // one-time operations
        create();
    }

    public void create(){

        stage = new Stage(new ScreenViewport());
        sb = new SpriteBatch();

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        TextField field = new TextField("", style);
        field.setText("Test");
        field.setWidth(150);

        Table menuTable = new Table(); // Table containing the buttons on the screen
        menuTable.add(field);
        menuTable.getCell(field).height(300).width(413);
        menuTable.row();
        menuTable.add(field);
        menuTable.getCell(field).height(300).width(413);
        menuTable.row();
        /*menuTable.add(setBtn).pad(10);

        menuTable.row();
        menuTable.add(tutBtn);
        menuTable.getCell(tutBtn).height(buttonHeight).width(413);
        menuTable.setFillParent(true);
        menuTable.moveBy(0,-240);
        menuTable.row();
        menuTable.add(signInBtn).pad(10);
        menuTable.getCell(signInBtn).height(buttonHeight).width(413);
        menuTable.setFillParent(true);*/

        //stage.addActor(menuTable);
        stage.addActor(field);

        Gdx.input.setInputProcessor(stage);

        //Initialiserer button to get GameScreen
        final Button menuButton = new Button(new TextureRegionDrawable(new TextureRegion(backB)));
        menuButton.setSize(60, 60);
        menuButton.setPosition(Gdx.graphics.getWidth() / 2 - menuButton.getWidth() / 2 , Gdx.graphics.getHeight() / 6 - menuButton.getHeight() / 2);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.setScreen(new MenuScreen(game, engine));
            }

        });

        stage.addActor(menuButton);
    }

    @Override
    public void update(float dt){
        // check for user input
        handleinput();
    }

    @Override
    public void draw(){
        sb.begin(); // Draw elements to Sprite Batch
        sb.draw(background, 0,0, TubbyWars.WIDTH, TubbyWars.HEIGHT); //Draws background photo
        sb.end();

        stage.draw();
    }

    @Override
    public void handleinput(){
    }

    @Override
    public void render(float dt){
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        draw();
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}