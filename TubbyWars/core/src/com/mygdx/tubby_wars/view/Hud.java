package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
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

import java.util.List;


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
    private Label playerOneScoreLabel;
    private Label playerTwoScoreLabel;

    // ASHLEY
    private ComponentMapper<PlayerComponent> pm;
    private ImmutableArray players;

    // SCORE
    private int playerOneScore, playerTwoScore;


    // if switching to use componentmapper and entity for finding player score, add courseEntity next to the spritebatch
    public Hud(SpriteBatch sb, ImmutableArray players){

        this.players = players;
        pm = ComponentMapper.getFor(PlayerComponent.class);

        viewport = new FitViewport(TubbyWars.WIDTH, TubbyWars.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        playerOneScore = pm.get((Entity) players.get(0)).score;
        playerTwoScore = pm.get((Entity) players.get(1)).score;



        // if we want more or different labels, feel free to change.

        playerOneName = new Label(pm.get((Entity) players.get(0)).playerName, new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerTwoName = new Label(pm.get((Entity) players.get(1)).playerName, new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerOneScoreLabel = new Label(String.valueOf(playerOneScore), new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerTwoScoreLabel = new Label(String.valueOf(playerTwoScore), new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.add(playerOneName).expandX().padTop(10);
        table.add(playerTwoName).expandX().padTop(10);
        table.row();
        table.add(playerOneScoreLabel).expandX();
        table.add(playerTwoScoreLabel).expandX();

        stage.addActor(table);

    }


    public void update(float dt){
        // update score here, i would like to do that through componentmapper, but think we should wait until more of the project is complete.
        // - yours truly Håkon <3
        if(pm.get((Entity)players.get(0)).score != playerOneScore || pm.get((Entity)players.get(1)).score != playerTwoScore){
            playerOneScore = pm.get((Entity)players.get(0)).score;
            playerTwoScore = pm.get((Entity)players.get(1)).score;

            playerOneScoreLabel.setText(playerOneScore);
            playerTwoScoreLabel.setText(playerTwoScore);
        }
    }





    @Override
    public void dispose(){
        stage.dispose();
    }
}
