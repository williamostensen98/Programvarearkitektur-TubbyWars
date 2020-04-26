package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.ComponentMapper;
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
import com.mygdx.tubby_wars.model.components.PlayerComponent;

public class Hud implements Disposable {

    // Scene2D.ui Stage and its own Viewport for HUD
    Stage stage;

    // Scene2D widgets
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

        Viewport viewport = new FitViewport(TubbyWars.WIDTH, TubbyWars.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        playerOneScore = pm.get((Entity) players.get(0)).score;
        playerTwoScore = pm.get((Entity) players.get(1)).score;

        Label playerOneName = new Label(pm.get((Entity) players.get(0)).playerName, new Label.LabelStyle(new BitmapFont(), Color.RED));
        Label playerTwoName = new Label(pm.get((Entity) players.get(1)).playerName, new Label.LabelStyle(new BitmapFont(), Color.RED));
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
