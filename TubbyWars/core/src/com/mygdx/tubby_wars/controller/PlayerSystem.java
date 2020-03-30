package com.mygdx.tubby_wars.controller;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.tubby_wars.model.World;
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


    public void setUpPosition(World world, Entity playerEntity, int x, int y){
        pm.get(playerEntity).position = new Vector3(x,y,0);

    }

    public boolean getIsYourTurn(Entity playerEntity){
        return pm.get(playerEntity).isYourTurn;
    }

    public void setIsYourTurn(Entity playerEntity, boolean isYourTurn){
        pm.get(playerEntity).isYourTurn = isYourTurn;
    }

    public void setAimArrow(Entity playerEntity, int x, int y){
        if(pm.get(playerEntity).isYourTurn){
            pm.get(playerEntity).aimArrow.x += x;
            pm.get(playerEntity).aimArrow.y += y;
        }
    }

    public void setInitialAimArrow(Entity playerEntity, int x, int y){
        pm.get(playerEntity).aimArrow.x = x;
        pm.get(playerEntity).aimArrow.y = y;
    }


    public Vector3 getAimArrow(Entity playerEntity){
        return pm.get(playerEntity).aimArrow;
    }


    public boolean getHasFired(Entity playerEntity){
        return pm.get(playerEntity).hasFired;
    }

    public void setHasFired(Entity playerEntity, boolean hasFired){
        if(pm.get(playerEntity).hasFired && !hasFired){
            setShotCounter(playerEntity);
        }
        pm.get(playerEntity).hasFired = hasFired;
    }


    public void setBulletPos(Entity playerEntity){

        // define previous bullet point and aimArrow for easier access.
        Vector3 prevPos = pm.get(playerEntity).bulletPos;
        Vector3 aimArrow = pm.get(playerEntity).aimArrow;

        // home-made x and y velocity
        int xVelocity = (int) (aimArrow.x  - pm.get(playerEntity).position.x) / 20;
        int yVelocity = (int) (aimArrow.y - 75) / 20;

        // creates the new bullet position
        Vector3 newPos = new Vector3(prevPos.x + xVelocity, prevPos.y + (yVelocity - prevPos.z), prevPos.z + (float)0.1);
        pm.get(playerEntity).bulletPos = newPos;

        // if the bullet is below ground, 20 = radius of bullet
        if(getBulletPos(playerEntity).y + 20 < 0){
            setHasFired(playerEntity, false);
            setInitBulletPos(playerEntity, (int) pm.get(playerEntity).position.x, 75);
        }
    }

    // set the initial bullet coords.
    public void setInitBulletPos(Entity playerEntity, int x, int y){
        //setShotCounter(playerEntity);
        pm.get(playerEntity).bulletPos.x = x;
        pm.get(playerEntity).bulletPos.y = y;
        pm.get(playerEntity).bulletPos.z = 0;


    }


    public Vector3 getBulletPos(Entity playerEntity){
        return pm.get(playerEntity).bulletPos;
    }

    public void setShotCounter(Entity playerEntity){
        if(pm.get(playerEntity).shotCounter == 3){
            pm.get(playerEntity).shotCounter = 0;
        }
        else{
            pm.get(playerEntity).shotCounter += 1;

            // deal 10 damage each turn to test
            dealDamage(playerEntity, 10);
        }
    }

    public int getShotCounter(Entity playerEntity){
        return pm.get(playerEntity).shotCounter;
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



}
