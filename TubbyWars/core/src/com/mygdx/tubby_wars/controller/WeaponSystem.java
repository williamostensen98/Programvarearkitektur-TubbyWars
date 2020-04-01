package com.mygdx.tubby_wars.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.tubby_wars.model.components.WeaponComponent;

public class WeaponSystem extends IteratingSystem {

    private static final Family family = Family.all(WeaponComponent.class).get();
    private ComponentMapper<WeaponComponent> wm;

    public WeaponSystem(){
        super(family);
        wm = ComponentMapper.getFor(WeaponComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        WeaponComponent weaponComp = wm.get(entity);
    }


    public void initiateNewWeapon(Entity weaponEntity, String name, int power){
        wm.get(weaponEntity).weaponName = name;
        //wm.get(weaponEntity).weaponTexture = texture;
        wm.get(weaponEntity).weaponPower = power;
    }

    // kan hende vi bør ha muligheten til å oppgradere et spesielt våpen? typ increaseDamage(weaponEntity, power){}



}
