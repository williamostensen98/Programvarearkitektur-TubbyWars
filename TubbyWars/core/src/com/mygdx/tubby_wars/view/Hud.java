package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.components.CourseComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;


/**
 * NB!! HAR LAGT INN ET FORSLAG PÅ HVORDAN VI KAN ACCESSE COMPONENTS FOR Å SE HEALTH, SCORE ETC.
 * se utkommentert kode
 * */


public class Hud implements Disposable {

    // Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    // Scene2D widgets
    private Label playerOneName;
    private Label playerTwoName;
    private Label playerOneScore;
    private Label playerTwoScore;

/*
    private ComponentMapper<CourseComponent> cm;
    private ComponentMapper<PlayerComponent> pm;
    private Entity playerOne;
    private Entity playerTwo;
*/

    // if switching to use componentmapper and entity for finding player score, add courseEntity next to the spritebatch
    public Hud(SpriteBatch sb){


        viewport = new FitViewport(TubbyWars.WIDTH, TubbyWars.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);


        /*

        // GETS HOLD OF EACH COURSE
        cm = ComponentMapper.getFor(CourseComponent.class);
        // FOR EACH COURSE, THIS WILL GRAB THE CORRESPONDING PLAYERS
        pm = ComponentMapper.getFor(PlayerComponent.class);

        playerOne = cm.get(courseEntity).playerOne;
        playerTwo = cm.get(courseEntity).playerTwo;

        // when we want to access attributes in playerComponenet, such as score for instance
        // pm.get(playerOne).score


         */

        // if we want more or different labels, feel free to change.

        playerOneName = new Label("Player 1", new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerTwoName = new Label("Player 2", new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerOneScore = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerTwoScore = new Label("69", new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.add(playerOneName).expandX().padTop(10);
        table.add(playerTwoName).expandX().padTop(10);
        table.row();
        table.add(playerOneScore).expandX();
        table.add(playerTwoScore).expandX();

        stage.addActor(table);

    }


    public void update(float dt){
        // update score here, i would like to do that through componentmapper, but think we should wait until more of the project is complete.
        // - yours truly Håkon <3
    }





    @Override
    public void dispose(){
        stage.dispose();
    }
}
