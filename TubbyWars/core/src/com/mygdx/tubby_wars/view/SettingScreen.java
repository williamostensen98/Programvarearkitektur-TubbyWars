package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.Assets;


public class SettingScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;

    private Label settingsText;
    private Stage stage;
    private Sprite play;
    private Texture playButtonText;
    private SpriteBatch batch;

    public SettingScreen(TubbyWars game, Engine engine){
        super();
        this.game = game;
        this.engine = engine;
        create();
    }

    @Override
    public void create() {
        // create the menuscreen here
        settingsText = new Label("SETTINGS:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //settingsText.setSize(200,200);
        settingsText.setPosition(Gdx.graphics.getWidth()/2,(Gdx.graphics.getHeight()/4)*3);
        //Virker ikke - Vet ikke hvordan vi skal få til å scale overskriften
        //settingsText.getData().setScale(2, 2);
        batch = new SpriteBatch();
        playButtonText = new Texture("play.png");
        play = new Sprite(playButtonText);

        stage = new Stage(new ScreenViewport());
        stage.addActor(settingsText);
        Gdx.input.setInputProcessor(stage); //Vet ikke: sets input to be handled by stage

        final Button playButton= new Button(new TextureRegionDrawable(new TextureRegion(playButtonText)), new TextureRegionDrawable(new TextureRegion(playButtonText)));
        playButton.setTransform(true);
        playButton.setSize(174,174);
        playButton.setOrigin(playButton.getWidth()/2,playButton.getHeight()/2);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                System.out.println("Button pressed");
            }

            //fires when button is pressed down
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                playButton.addAction(Actions.scaleTo(0.95f, 0.95f,0.1f));
                System.out.println("touch down fired");
                return super.touchDown(inputEvent, xpos, ypos,pointer,button);
            }


            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, xpos, ypos,pointer,button);
                playButton.addAction(Actions.scaleTo(1f, 1f,1f));
            }

        });

        stage.addActor(playButton);


    }

    @Override
    public void update(float dt) {
        handleinput();

    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(254.0f/255.0f, 127.0f/255.0f, 156.0f/255.0f, 1.0f);
        stage.draw();
    }

    @Override
    public void handleinput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            System.out.println("Enter ble trykket på");
        }


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

