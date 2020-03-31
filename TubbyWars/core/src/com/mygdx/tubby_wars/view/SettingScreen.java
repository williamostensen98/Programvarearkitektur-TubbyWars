package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;


public class SettingScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;

    private Texture titleTexture;
    private Image title;
    private Label musicText;
    private Label soundsText;


    private Texture playButtonTexture;
    private Texture pauseButtonTexture;
    private boolean isMute = false;
    private boolean soundEffectsIsMute = false;

    private Vector3 pos1;
    private Vector3 pos2;

    public SettingScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;
        this.engine = engine;
        create();
    }

    @Override
    public void create() {

        titleTexture = new Texture("settings.png");
        title = new Image(titleTexture);
        pos1 = new Vector3((Gdx.graphics.getWidth() - title.getWidth()) / 2, Gdx.graphics.getHeight() * 5 / 6, 0);
        title.setPosition(pos1.x, pos1.y);

        pos2 = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8 * 7, 0);
        musicText = new Label("Music:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        musicText.setPosition(pos2.x / 3, pos2.y * 2 / 3);
        soundsText = new Label("Sound effects:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        soundsText.setPosition(pos2.x / 3, pos2.y * 4 / 7);

        playButtonTexture = new Texture("soundOn.png");
        pauseButtonTexture = new Texture("soundOff.png");


        stage = new Stage(new ScreenViewport());
        stage.addActor(musicText);
        stage.addActor(soundsText);
        stage.addActor(title);


        Gdx.input.setInputProcessor(stage);

        //Initialiserer musicButton
        final Button musicButton = new Button(new TextureRegionDrawable(new TextureRegion(playButtonTexture)), new TextureRegionDrawable(new TextureRegion(playButtonTexture)), new TextureRegionDrawable(new TextureRegion(pauseButtonTexture)));
        musicButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        musicButton.setSize(50, 50);
        musicButton.setOrigin(50, 50);
        musicButton.setChecked(isMute);
        musicButton.setPosition(pos2.x / 3 + 50, pos2.y * 2 / 3 - musicButton.getHeight() / 3);


        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (isMute == false)
                    System.out.println("Music is muted");
                else {
                    System.out.println("Music is playing");
                }
                isMute = !isMute;
            }

            //Kjøres når knappen trykkes ned
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                musicButton.addAction(Actions.scaleTo(0.96f, 0.96f, 0.2f)); //Minker størrelsen på knappen når den trykkes
                return super.touchDown(inputEvent, 100, 100, pointer, button);
            }

            //Kjører når knappen slippes opp
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100, pointer, button);
                musicButton.addAction(Actions.scaleTo(1f, 1f, 0.2f)); //Setter størrelsen på knappen tilbake til original størrelse
            }
        });

        stage.addActor(musicButton);


        //Initialiserer soundEffectButton
        final Button soundEffectButton = new Button(new TextureRegionDrawable(new TextureRegion(playButtonTexture)), new TextureRegionDrawable(new TextureRegion(playButtonTexture)), new TextureRegionDrawable(new TextureRegion(pauseButtonTexture)));
        soundEffectButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        soundEffectButton.setSize(50, 50);
        soundEffectButton.setOrigin(50, 50);
        soundEffectButton.setChecked(soundEffectsIsMute);
        soundEffectButton.setPosition(pos2.x / 3 + 100, pos2.y * 4 / 7 - soundEffectButton.getHeight() / 3);


        soundEffectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (soundEffectsIsMute == false)
                    System.out.println("Soundeffects is off");
                else {
                    System.out.println("Soundeffects is on");
                }
                soundEffectsIsMute = !soundEffectsIsMute;
            }

            //Kjøres når knappen trykkes ned
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                soundEffectButton.addAction(Actions.scaleTo(0.96f, 0.96f, 0.2f)); //Minker størrelsen på knappen når den trykkes
                return super.touchDown(inputEvent, 100, 100, pointer, button);
            }

            //Kjører når knappen slippes opp
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100, pointer, button);
                soundEffectButton.addAction(Actions.scaleTo(1f, 1f, 0.2f)); //Setter størrelsen på knappen tilbake til original størrelse
            }
        });

        stage.addActor(soundEffectButton);
    }




    @Override
    public void update(float dt) {
        handleinput();
        stage.act(Gdx.graphics.getDeltaTime());
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
        stage.act(Gdx.graphics.getDeltaTime());
        draw();

    }

    @Override
    public void dispose(){
        super.dispose();
    }
}

