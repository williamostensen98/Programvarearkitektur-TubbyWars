package com.mygdx.tubby_wars.view;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;


public class SettingScreen implements Screen {

    private TubbyWars game;
    private Stage stage;

    //Textures for title of page and the background
    private Texture title;
    private Texture background;

    //Textures for the buttons
    private Texture resumeGameB;
    private Texture quitGameB;
    private Texture menuScreenB;
    private Texture soundOnB; //Used for both music and sounds
    private Texture soundOffB; //Used for both music and sounds

    //Buttons
    private Button soundEffectButton;
    private Button musicButton;

    // Click effect
    private Sound click;

    public SettingScreen(TubbyWars game, Engine engine) {
        super();
        this.game = game;

        background = Assets.getTexture(Assets.settingsBackground);
        title = Assets.getTexture(Assets.settingsTitle);
        menuScreenB = Assets.getTexture(Assets.menuScreenButton);
        resumeGameB = Assets.getTexture(Assets.continueGameButton);
        quitGameB = Assets.getTexture(Assets.quitGameButton);
        soundOnB = Assets.getTexture(Assets.soundOnButton);
        soundOffB = Assets.getTexture(Assets.soundOffButton);
        click = Assets.getSound(Assets.clickSound);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        makeMusicButtons();

        //MAKE LOGO IMAGE
        final Image logo = new Image(title);
        logo.setSize(Gdx.graphics.getWidth()/7f,  Gdx.graphics.getHeight()/5f);
        logo.setPosition(Gdx.graphics.getWidth()/2f - logo.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - logo.getHeight()/2f);

        //MAKE BUTTONS
        Button resumeButton = makeButton(resumeGameB,84f,6f,"PLAY", "resume");
        Button quitButton = makeButton(quitGameB,17f,6f,"MENU", "quit");
        Button menuButton = makeButton(menuScreenB,17f,6f,"MENU","menu");

        //MAKE LABELS
        Label musicText = makeLabel("Music: ", 1);
        Label soundsText = makeLabel("Sound effects: ", 1.75f);

        if (ControllerLogic.loggedIn) {
            stage.addActor(quitButton);
            stage.addActor(resumeButton);
        }
        else {
            stage.addActor(menuButton);
        }

        // Add all objects to the stage
        stage.addActor(logo);
        stage.addActor(musicText);
        stage.addActor(soundsText);
        stage.addActor(musicButton);
        stage.addActor(soundEffectButton);
    }

    @Override
    public void render(float dt){
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);
        game.getBatch().begin(); // Draw elements to Sprite Batch
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
    public void dispose() {
        stage.dispose();
        title.dispose();
        background.dispose();
        resumeGameB.dispose();
        quitGameB.dispose();
        menuScreenB.dispose();
        soundOnB.dispose();
        soundOffB.dispose();
        click.dispose();
    }

    private Label makeLabel(String text, float xPos){
        Label l = new Label(text, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        l.setPosition((Gdx.graphics.getWidth() / 3f)*xPos + (musicButton.getWidth() - l.getWidth())/2f, Gdx.graphics.getHeight() / 1.85f);
        l.scaleBy(1.2f);
        return l;
    }

    private Button makeButton(Texture texture, float xPos, float yPos, final String nextScreen, final String type){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize( Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/7f);
        b.setPosition(Gdx.graphics.getWidth() /100f* xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / yPos - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                if(type.equalsIgnoreCase("quit")){
                    ControllerLogic.loggedIn=false;
                    ControllerLogic.isPlayersTurn = false;
                    ControllerLogic.roundCount = 0;
                }
                game.gsm.changeScreen(nextScreen);
            }
        });
        return b;
    }

    private void makeMusicButtons() {
        //Initialize musicButton
        musicButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOffB)));
        musicButton.setTransform(true); // when this is true we can automatically scale it with click
        musicButton.setSize(Gdx.graphics.getWidth()/15f,Gdx.graphics.getHeight()/5f);
        musicButton.setOrigin(Gdx.graphics.getWidth()/15f,Gdx.graphics.getHeight()/5f);
        musicButton.setChecked(game.musicStateManager.getMuteMusicState());
        musicButton.setPosition(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 3f);
        //Add click effect
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
                if (!game.musicStateManager.getMuteMusicState()) {
                    game.muteMusic(Assets.getMusic(Assets.backgroundMusic));
                } else {
                    game.unmuteMusic(Assets.getMusic(Assets.backgroundMusic));
                }
            }
            //Runs when the button is pressed down
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                musicButton.addAction(Actions.scaleTo(0.96f, 0.96f, 0.2f)); //Minimizes size when clicked
                return super.touchDown(inputEvent, 100, 100, pointer, button);
            }
            //Runs when the button is not pressed down
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100, pointer, button);
                musicButton.addAction(Actions.scaleTo(1f, 1f, 0.2f)); //reset size
            }
        });

        //Initialize soundEffectButton
        soundEffectButton = new Button(new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOnB)), new TextureRegionDrawable(new TextureRegion(soundOffB)));
        soundEffectButton.setTransform(true);
        soundEffectButton.setSize(Gdx.graphics.getWidth()/15f,Gdx.graphics.getHeight()/5f);
        soundEffectButton.setOrigin(Gdx.graphics.getWidth()/15f,Gdx.graphics.getHeight()/5f);
        soundEffectButton.setChecked(game.soundStateManager.getMuteSoundState());
        soundEffectButton.setPosition((Gdx.graphics.getWidth() / 3f)*1.75f, Gdx.graphics.getHeight() / 3f);
        //Add click effect
        soundEffectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (game.soundStateManager.getMuteSoundState()) {
                    game.soundStateManager.unmuteSound();
                    game.playSound(click);
                }
                else {
                    game.soundStateManager.muteSound();
                }
            }
            //Runs when the button is pressed down
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                soundEffectButton.addAction(Actions.scaleTo(0.96f, 0.96f, 0.2f));
                return super.touchDown(inputEvent, 100, 100, pointer, button);
            }
            //Runs when the button is not pressed down
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100, pointer, button);
                soundEffectButton.addAction(Actions.scaleTo(1f, 1f, 0.2f));
            }
        });
    }
}

