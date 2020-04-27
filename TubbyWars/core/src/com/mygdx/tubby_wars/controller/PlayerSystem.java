package com.mygdx.tubby_wars.controller;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.tubby_wars.model.components.PlayerComponent;
import com.mygdx.tubby_wars.view.Bullet;

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

    public String getUsername(Entity playerEntity){
        return pm.get(playerEntity).playerName;
    }

    public void setScore(Entity playerEntity, int score){
        pm.get(playerEntity).score += score;
    }


    public void setHealth(Entity playerEntity, int health){
        pm.get(playerEntity).health = health;
    }

    public int getHealth(Entity playerEntity){
        return pm.get(playerEntity).health;
    }

    public void dealDamage(Entity playerEntity, int damage){
        pm.get(playerEntity).health -= damage;
    }

    public float getWeaponDamage(Entity playerEntity){
        return pm.get(playerEntity).weaponDamage;
    }

    public void setWeaponDamage(Entity playerEntity, float weaponDamage){
        pm.get(playerEntity).weaponDamage = weaponDamage;
    }

    public void setTexture(Entity playerEntity, Texture texture){
        pm.get(playerEntity).characterBody = texture;
    }

    public Texture getTexture(Entity playerEntity){
        return pm.get(playerEntity).characterBody;
    }

    public void setWeaponTexture(Entity playerEntity, TextureRegion weaponTexture){
        pm.get(playerEntity).weaponTexture = weaponTexture;
    }

    public TextureRegion getWeaponTexture(Entity playerEntity) {
        return pm.get(playerEntity).weaponTexture;
    }

    public int getScore(Entity playerEntity){
        return pm.get(playerEntity).score;
    }

    public void createTextureRegion(Entity playerEntity){
        Texture texture = pm.get(playerEntity).characterBody;
        pm.get(playerEntity).region = new TextureRegion(texture, 0,0,texture.getWidth(), texture.getHeight());
    }

    public TextureRegion getTextureRegion(Entity playerEntity){
        return pm.get(playerEntity).region;
    }

    public void addBullets(Entity playerEntity, Bullet bullet){
        pm.get(playerEntity).bullets.add(bullet);
    }

    public Array<Bullet> getBullets(Entity playerEntity){
        return pm.get(playerEntity).bullets;
    }
    public void initializeNewBullets(Entity playerEntity){
        pm.get(playerEntity).bullets = new Array<>();
    }

}
