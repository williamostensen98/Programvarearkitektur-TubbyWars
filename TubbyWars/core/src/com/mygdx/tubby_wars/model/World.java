package com.mygdx.tubby_wars.model;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.components.CourseComponent;
import com.mygdx.tubby_wars.model.components.PhysicsComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

import java.util.ArrayList;
import java.util.List;

public class World {

    private Engine engine;

    public World(Engine engine){
        this.engine = engine;
    }

    public Entity createCourse(){
        Entity courseEntity = new Entity();
        CourseComponent cc = new CourseComponent();
        courseEntity.add(cc);
        engine.addEntity(courseEntity);
        return courseEntity;
    }

    public void createPhysics(){
        Entity physicsEntity = new Entity();
        PhysicsComponent pc = new PhysicsComponent();
        physicsEntity.add(pc);
        engine.addEntity(physicsEntity);
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
        engine.getSystem(PlayerSystem.class).setScore(playerOneEntity,4000);
        engine.getSystem(PlayerSystem.class).setWeaponTexture(playerOneEntity, new TextureRegion(Assets.getTexture(Assets.revolverWeapon)));

        // creation of player 2
        Entity playerTwoEntity = new Entity();
        PlayerComponent playerTwoComponent = new PlayerComponent();
        playerTwoEntity.add(playerTwoComponent);

        // connect player 2 to the game engine, and set initial variables
        engine.addEntity(playerTwoEntity);
        engine.getSystem(PlayerSystem.class).setHealth(playerTwoEntity, 150);
        engine.getSystem(PlayerSystem.class).setUsername(playerTwoEntity,"CORONA");
        engine.getSystem(PlayerSystem.class).setScore(playerTwoEntity,1500);
        engine.getSystem(PlayerSystem.class).setWeaponTexture(playerTwoEntity, new TextureRegion(Assets.getTexture(Assets.revolverWeapon)));

        entities.add(playerOneEntity);
        entities.add(playerTwoEntity);

        return entities;
    }

}
