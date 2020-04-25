package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.CourseSystem;
import com.mygdx.tubby_wars.controller.PhysicsSystem;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.controller.ScreenFactory;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.World;
import com.badlogic.gdx.Screen;

import java.util.List;

public class CharacterCreationScreen implements Screen {

    private TubbyWars game;
    private Stage stage;

    //Textures
    private Texture background;
    private Texture textField;
    private Texture nextButtonTexture;

    //Sprites
    private Texture logo;
    private Texture yellowT;
    private Texture greenT;
    private Texture redT;
    private Texture purpleT;

    //Initializing Clicking sound when pressing button
    private Sound click;

    //Initializing buttons
    private Button yellowTubby;
    private Button greenTubby;
    private Button purpleTubby;
    private Button redTubby;

    //Labels
    private Label informationText;

    //Initializing TextFields for username input
    private TextField user1Input;
    private TextField user2Input;

    private List<Entity> players;
    private PlayerSystem playerSystem;

    public CharacterCreationScreen(TubbyWars game) {
        super();
        this.game = game;

        //ASSETS
        logo = Assets.getTexture(Assets.characterTitle);
        background = Assets.getTexture(Assets.characterBackground);
        textField = Assets.getTexture(Assets.textFieldBackground);
        nextButtonTexture = Assets.getTexture(Assets.continueGameButton);
        yellowT = Assets.getTexture(Assets.gulTubby);
        greenT = Assets.getTexture(Assets.gronnTubby);
        redT = Assets.getTexture(Assets.rodTubby);
        purpleT = Assets.getTexture(Assets.lillaTubby);
        click = Assets.getSound(Assets.clickSound);

        // setup the ashley entity system
        setupAshley();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialize title logo
        final Image title = new Image(logo);
        title.setSize(Gdx.graphics.getWidth()/7f,  Gdx.graphics.getHeight()/5f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/8f*7f - title.getHeight()/2f);

        // BUTTONS
        redTubby = makeTubbyButton(redT, players.get(0),1);
        purpleTubby = makeTubbyButton(purpleT, players.get(0),2.5f);
        yellowTubby = makeTubbyButton(yellowT, players.get(1),5);
        greenTubby = makeTubbyButton(greenT, players.get(1),6.5f);
        Button nextButton = makeNextButton();

        // INPUT FIELD
        user1Input = makeInputField(1.15f);
        user2Input = makeInputField(4.9f);

        // LABELS
        String s = "Error: Write usernames without æ, ø, å or ' ', not identical nor contain more than 15 characters. Also remember to choose a character";
        informationText = makeLabel(s, Color.RED,15f,1.38f);
        Label user1Text = makeLabel("Player 1:",Color.BLACK,14f,1.6f);
        Label user2Text = makeLabel("Player 2:", Color.BLACK, 1.85f,1.6f);
        Label leftText = makeLabel("Choose character:",Color.BLACK, 14f,1.9f);
        Label rightText = makeLabel("Choose character:",Color.BLACK, 1.85f,1.9f);

        // TODO HVIS DET GÅR FIKS DISSE PÅ EN BEDRE MÅTE
        //Add actors
        stage.addActor(title);
        stage.addActor(user1Input);
        stage.addActor(user2Input);

        stage.addActor(user1Text);
        stage.addActor(user2Text);

        stage.addActor(leftText);
        stage.addActor(rightText);

        stage.addActor(nextButton);
        stage.addActor(yellowTubby);
        stage.addActor(greenTubby);
        stage.addActor(redTubby);
        stage.addActor(purpleTubby);
    }

    @Override
    public void render(float dt) {
        // Draw elements to Sprite Batch, textFields and Background
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().end();
        stage.draw();
    }

    private Button makeNextButton() {
        Button n = new Button(new TextureRegionDrawable(new TextureRegion(nextButtonTexture)));
        n.setSize( Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/7f);
        n.setPosition(Gdx.graphics.getWidth() / 2f - n.getWidth() / 2f, Gdx.graphics.getHeight() / 10f - n.getHeight() / 2f);
        n.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //Adds click effect
                game.playSound(click);
                //Cheks if players are ready to continue
                usernameCheck();
            }
        });
        return n;
    }

    private Button makeTubbyButton(Texture texture, Entity playerEntity, float width){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
        b.setPosition((Gdx.graphics.getWidth() / 8f) * width ,Gdx.graphics.getHeight() / 5f);
        b.addListener(clickListener(playerEntity,texture));
        return b;
    }

    private TextField makeInputField(float xPos){
        SpriteDrawable s = new SpriteDrawable(new Sprite(textField));
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(new BitmapFont(),Color.BLACK,null,null,s);

        TextField tf = new TextField("", style);
        tf.setSize(Gdx.graphics.getWidth()/6f, Gdx.graphics.getHeight()/15f);
        tf.setPosition((Gdx.graphics.getWidth() / 8f) * xPos,(Gdx.graphics.getHeight() / 1.6f) - (tf.getHeight()/4f));
        tf.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                game.playSound(click);
            }
        });
        return tf;
    }

    private Label makeLabel(String text, Color color, float xPos, float yPos){
        Label l = new Label(text, new Label.LabelStyle(new BitmapFont(), color));
        l.setPosition(Gdx.graphics.getWidth() / xPos,Gdx.graphics.getHeight() / yPos);
        l.setFontScale(1.3f);
        return l;
    }

    private ClickListener clickListener(final Entity playerEntity, final Texture tubby){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos){
                game.playSound(click);
                playerSystem.setTexture(playerEntity, tubby);

                if(tubby==redT){
                    redTubby.setSize(Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/3.3f);
                    purpleTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
                }
                if(tubby==purpleT){
                    purpleTubby.setSize(Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/3.3f);
                    redTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
                }
                if(tubby==yellowT){
                    yellowTubby.setSize(Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/3.3f);
                    greenTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
                }
                if(tubby==greenT){
                    greenTubby.setSize(Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/3.3f);
                    yellowTubby.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/5f);
                }
            }
        };
    }


    private void setupAshley(){
        Engine engine = new Engine();
        World ashleyWorld = new World(engine);


        // ADDS SYSTEMS TO THE ENGINE
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new CourseSystem());
        engine.addSystem(new PhysicsSystem());

        // CREATE PLAYERS AND COURSE
        players = ashleyWorld.createPlayers();
        Entity courseEntity = ashleyWorld.createCourse();
        ashleyWorld.createPhysics();

        // CONNECT PLAYERS TO THE COURSE
        engine.getSystem(CourseSystem.class).addPlayers(courseEntity, players);

        playerSystem = engine.getSystem(PlayerSystem.class);
        game.screenFactory.setEngine(engine);
    }

    //Checks if username is correctly written
    private void usernameCheck() {
        String username1 = user1Input.getText();
        String username2 = user2Input.getText();

        //Checks that usernames does not contain æøå or space
        if (!username1.isEmpty() && !username1.contains("æ") && !username1.contains("ø") && !username1.contains("å")
                && !username1.contains(" ") && !username2.contains("æ") && !username2.contains("ø") && !username2.contains("å")
                && !username2.contains(" ") && !username2.isEmpty() && !username1.equals(username2)
                && playerSystem.getTexture(players.get(0)) != null && playerSystem.getTexture(players.get(1)) != null
                && username1.length() < 15 && username2.length() < 15) {

            // saves username in playerComponent
            playerSystem.setUsername(players.get(0),username1);
            playerSystem.setUsername(players.get(1),username2);

            //Sets loggedIn to true, so that Setting Screen changes.
            ControllerLogic.loggedIn = true;

            //Goes to gameScreen
            game.gsm.changeScreen("SHOP");
        } else {
            stage.addActor(informationText);
        }
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

    }
}