package com.mygdx.tubby_wars.model;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.controller.WeaponSystem;
import com.mygdx.tubby_wars.model.components.CourseComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;
import com.mygdx.tubby_wars.model.components.WeaponComponent;

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

        // NB: HER MÅ NOK MYE ENDRES, MEN VENTER MED DET TIL VI FAKTISK VET HVA

        List<Entity> entities = new ArrayList<Entity>();

        // add systems to the the engine
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new WeaponSystem());

        // creation of player 1, both component and system
        Entity playerOneEntity = new Entity();
        PlayerComponent playerOneComponent = new PlayerComponent();
        playerOneEntity.add(playerOneComponent);

        // connect player 1 to the game engine and set initial variables
        engine.addEntity(playerOneEntity);
        engine.getSystem(PlayerSystem.class).setHealth(playerOneEntity, 100);

        // create weapon component and system, then add them to player one.
        Entity weaponOneEntity = new Entity();
        WeaponComponent weaponOneComponent = new WeaponComponent();
        weaponOneEntity.add(weaponOneComponent);
        engine.getSystem(WeaponSystem.class).initiateNewWeapon(weaponOneEntity,"Starting weapon", 20);
        engine.getSystem(PlayerSystem.class).setWeapon(playerOneEntity, weaponOneEntity);



        // creation of player 2, both component and system
        Entity playerTwoEntity = new Entity();
        PlayerComponent playerTwoComponent = new PlayerComponent();
        playerTwoEntity.add(playerTwoComponent);

        // connect player 2 to the game engine, and set initial variables
        engine.addEntity(playerTwoEntity);
        engine.getSystem(PlayerSystem.class).setHealth(playerTwoEntity, 100);

        // create weapon component and system, then add them to player two.
        Entity weaponTwoEntity = new Entity();
        WeaponComponent weaponTwoComponent = new WeaponComponent();
        weaponTwoEntity.add(weaponTwoComponent);
        engine.getSystem(WeaponSystem.class).initiateNewWeapon(weaponTwoEntity,"Starting weapon", 10);
        engine.getSystem(PlayerSystem.class).setWeapon(playerTwoEntity, weaponTwoEntity);



        // adds both entities into the same list and return it.
        entities.add(playerOneEntity);
        entities.add(playerTwoEntity);

        return entities;
    }

}
