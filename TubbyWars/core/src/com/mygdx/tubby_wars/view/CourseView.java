package com.mygdx.tubby_wars.view;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.components.CourseComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;


public class CourseView {

    private TubbyWars game;
    private Entity courseEntity;

    private ComponentMapper<CourseComponent> cm;
    private ComponentMapper<PlayerComponent> pm;

    private Entity playerOne;
    private Entity playerTwo;

    private ShapeRenderer shapeRenderer;

    public CourseView(TubbyWars game, Entity courseEntity){
        this.game = game;
        this.courseEntity = courseEntity;

        cm = ComponentMapper.getFor(CourseComponent.class);
        pm = ComponentMapper.getFor(PlayerComponent.class);

        playerOne = cm.get(courseEntity).playerOne;
        playerTwo = cm.get(courseEntity).playerTwo;

        shapeRenderer = new ShapeRenderer();
    }


    public void draw(){
        playerOne = cm.get(courseEntity).playerOne;
        playerTwo = cm.get(courseEntity).playerTwo;

        // draw player one
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(pm.get(playerOne).position.x, pm.get(playerOne).position.y, 50,50);
        shapeRenderer.end();

        // draw player two
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(pm.get(playerTwo).position.x, pm.get(playerTwo).position.y, 50,50);
        shapeRenderer.end();

        if(pm.get(playerOne).isYourTurn){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.line(pm.get(playerOne).position.x + 50, pm.get(playerOne).position.y + 25, pm.get(playerOne).aimArrow.x, pm.get(playerOne).aimArrow.y);
            shapeRenderer.end();
        }
        else if(pm.get(playerTwo).isYourTurn){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.line(pm.get(playerTwo).position.x, pm.get(playerTwo).position.y + 25, pm.get(playerTwo).aimArrow.x, pm.get(playerTwo).aimArrow.y);
            shapeRenderer.end();
        }
        else{
            // vet ikke helt hva som skal komme her enda... sikkert noe smart Hei
        }

        if(pm.get(playerOne).isYourTurn && pm.get(playerOne).hasFired && (pm.get(playerOne).bulletPos.y >= 0)){
            // draw bullet for player one
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(pm.get(playerOne).bulletPos.x, pm.get(playerOne).bulletPos.y, 20);
            shapeRenderer.end();
        }

        else if(pm.get(playerTwo).isYourTurn && pm.get(playerTwo).hasFired && (pm.get(playerTwo).bulletPos.y >= 0)){
            // draw bullet for player two
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(pm.get(playerTwo).bulletPos.x, pm.get(playerTwo).bulletPos.y, 20);
            shapeRenderer.end();
        }

        else{
            // vet ikke hva som skal være her enda, mulig vi endrer til to if'er
        }
    }
}
