package com.mygdx.tubby_wars.controller;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

public class PlayerSystem extends IteratingSystem {

    private static final Family family = Family.all(PlayerComponent.class).get();
    private ComponentMapper<PlayerComponent> pm;



    public PlayerSystem(){
        super(family);
        pm = ComponentMapper.getFor(PlayerComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent playerComp = pm.get(entity);
    }


    public void setUsername(Entity playerEntity, String username){
        pm.get(playerEntity).playerName = username;
    }

    public void setScore(Entity playerEntity, int score){
        pm.get(playerEntity).score = score;
    }


    public void setHealth(Entity playerEntity, int health){
        pm.get(playerEntity).health = health;
    }



    public void dealDamage(Entity playerEntity, int damage){
        if(pm.get(playerEntity).health - damage <= 0){
            // Should go to the shop, current turn is ended.

            // Atm I just reset the health when dead
            pm.get(playerEntity).health = 100;
        }
        else{
            pm.get(playerEntity).health -= damage;
        }
    }

    // Set the given weaponEntity to a player
    public void setWeapon(Entity playerEntity, Entity weaponEntity){
        pm.get(playerEntity).weapon = weaponEntity;
    }

}
