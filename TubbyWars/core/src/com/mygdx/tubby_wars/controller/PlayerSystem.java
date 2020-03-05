package com.mygdx.tubby_wars.controller;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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
        pm.get(playerEntity).hasFired = hasFired;
    }











}
