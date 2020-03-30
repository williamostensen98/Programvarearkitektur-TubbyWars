package com.mygdx.tubby_wars.model;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.components.CourseComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

import java.util.ArrayList;
import java.util.List;

public class World {

    private Engine engine;

    public World(Engine engine){
        this.engine = engine;
    }

    /*
    public Entity createMap(){


        return
    }
     */

    public Entity createCourse(){

        Entity courseEntity = new Entity();

        CourseComponent cc = new CourseComponent();

        courseEntity.add(cc);

        engine.addEntity(courseEntity);

        return courseEntity;
    }


    public List<Entity> createPlayers(){

        List<Entity> entities = new ArrayList<Entity>();

        // creation of player 1, both component and system
        Entity playerOneEntity = new Entity();
        PlayerComponent playerOneComponent = new PlayerComponent();
        playerOneEntity.add(playerOneComponent);

        // connect player 1 to the game engine
        engine.addEntity(playerOneEntity);
        engine.addSystem(new PlayerSystem());
        engine.getSystem(PlayerSystem.class).setUpPosition(this, playerOneEntity, 50, 50);
        engine.getSystem(PlayerSystem.class).setIsYourTurn(playerOneEntity, true);
        engine.getSystem(PlayerSystem.class).setInitialAimArrow(playerOneEntity, 200, 150);
        engine.getSystem(PlayerSystem.class).setHealth(playerOneEntity, 100);

        // creation of player 2, both component and system
        Entity playerTwoEntity = new Entity();
        PlayerComponent playerTwoComponent = new PlayerComponent();
        playerTwoEntity.add(playerTwoComponent);

        // connect player 2 to the game engine
        engine.addEntity(playerTwoEntity);
        engine.addSystem(new PlayerSystem());
        engine.getSystem(PlayerSystem.class).setUpPosition(this, playerTwoEntity, TubbyWars.WIDTH - 100, 50);
        engine.getSystem(PlayerSystem.class).setInitialAimArrow(playerTwoEntity, TubbyWars.WIDTH - 200, 150);
        engine.getSystem(PlayerSystem.class).setHealth(playerTwoEntity, 100);

        // adds both entities into the same list and return it.
        entities.add(playerOneEntity);
        entities.add(playerTwoEntity);

        return entities;
    }

}
