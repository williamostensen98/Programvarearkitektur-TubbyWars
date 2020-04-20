package com.mygdx.tubby_wars.model;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.controller.WeaponSystem;
import com.mygdx.tubby_wars.model.components.CourseComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;
import com.mygdx.tubby_wars.model.components.WeaponComponent;
import com.mygdx.tubby_wars.view.Player;

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

        // creation of player 1
        Entity playerOneEntity = new Entity();
        PlayerComponent playerOneComponent = new PlayerComponent();
        playerOneEntity.add(playerOneComponent);

        // connect player 1 to the game engine and set initial variables
        engine.addEntity(playerOneEntity);
        engine.getSystem(PlayerSystem.class).setHealth(playerOneEntity, 150);
        engine.getSystem(PlayerSystem.class).setUsername(playerOneEntity,"FUCK");
        engine.getSystem(PlayerSystem.class).setScore(playerOneEntity,0);
        engine.getSystem(PlayerSystem.class).setWeaponTexture(playerOneEntity, Assets.getTexture(Assets.gunWeapon));


        // creation of player 2
        Entity playerTwoEntity = new Entity();
        PlayerComponent playerTwoComponent = new PlayerComponent();
        playerTwoEntity.add(playerTwoComponent);

        // connect player 2 to the game engine, and set initial variables
        engine.addEntity(playerTwoEntity);
        engine.getSystem(PlayerSystem.class).setHealth(playerTwoEntity, 100);
        engine.getSystem(PlayerSystem.class).setUsername(playerTwoEntity,"CORONA");
        engine.getSystem(PlayerSystem.class).setScore(playerTwoEntity,0);
        engine.getSystem(PlayerSystem.class).setWeaponTexture(playerTwoEntity, Assets.getTexture(Assets.rifleWeapon));


        entities.add(playerOneEntity);
        entities.add(playerTwoEntity);

        return entities;
    }

}
