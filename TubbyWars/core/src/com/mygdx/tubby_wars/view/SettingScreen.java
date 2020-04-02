package com.mygdx.tubby_wars.view;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
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
import com.mygdx.tubby_wars.model.MusicStateManager;


public class SettingScreen extends ScreenAdapter implements ScreenInterface {

    private TubbyWars game;
    private Engine engine;
    private Stage stage;
    private MusicStateManager musicStateManager;

    private Texture titleTexture;
    private Image title;
    private Label musicText;
    private Label soundsText;


    private Texture playButtonTexture;
    private Texture pauseButtonTexture;
    private boolean isMute = false;
    private boolean soundEffectsIsMute = false;

    private Texture resumeButtonTexture;
    private Texture backButtonTexture;

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
        musicText = new Label("Music:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        musicText.setPosition(pos2.x / 3, pos2.y * 2 / 3);

        soundsText = new Label("Sound effects:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        soundsText.setPosition(pos2.x / 3, pos2.y * 4 / 7);

        playButtonTexture = new Texture("soundOn.png");
        pauseButtonTexture = new Texture("soundOff.png");
        resumeButtonTexture = new Texture("play.png");
        backButtonTexture = new Texture("back.png");


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
        musicButton.setChecked(!game.musicStateManager.getMusicState());
        musicButton.setPosition(pos2.x*3/ 6, pos2.y * 2 / 3 - musicButton.getHeight() / 3);


        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (game.musicStateManager.getMusicState()==true) {
                    game.musicStateManager.stopMusic();
                    System.out.println("Music is muted");
                }
                else {
                    game.musicStateManager.playMusic();
                    System.out.println("Music is playing");
                }
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
        soundEffectButton.setPosition(pos2.x* 4/ 6, pos2.y * 4 / 7 - soundEffectButton.getHeight() / 3);

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


    //Initialiserer resumeButton
    /*
        Vet ikke om vi burde ha en resume-knapp eller ikke?
       Kan jo kanskje være greit om man går til settings midt i en runde for å skru av musikken for eksempel.

     */
    final Button resumeButton = new Button(new TextureRegionDrawable(new TextureRegion(resumeButtonTexture)), new TextureRegionDrawable(new TextureRegion(resumeButtonTexture)));
        resumeButton.setSize(50, 50);
        resumeButton.setPosition(pos2.x *10/ 6 , pos2.y/8);
        resumeButton.addListener(new ClickListener() {

        @Override
        public void clicked(InputEvent inputEvent, float xpos, float ypos) {
            game.setScreen(new GameScreen(game, engine));
        }

    });
        stage.addActor(resumeButton);

        //Initialiserer backButton
        final Button backButton = new Button(new TextureRegionDrawable(new TextureRegion(backButtonTexture)), new TextureRegionDrawable(new TextureRegion(backButtonTexture)));
        backButton.setSize(60, 60);
        backButton.setPosition(pos2.x / 6 , pos2.y/8);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.setScreen(new MenuScreen(game, engine));
            }
        });
        stage.addActor(backButton);
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

    }

    @Override
    public void render(float dt){
        update(dt);
        //stage.act(Gdx.graphics.getDeltaTime());
        draw();
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}

