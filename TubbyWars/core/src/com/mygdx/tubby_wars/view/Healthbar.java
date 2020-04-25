package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

public class Healthbar extends Sprite {

    private int health;
    private Body body;

    private Boolean healthDecrease;

    // ASHLEY
    private Entity playerEntity;
    private ComponentMapper<PlayerComponent> pm;

    public Healthbar(Body body, Entity playerEntity){
        this.body = body;
        this.playerEntity = playerEntity;
        pm = ComponentMapper.getFor(PlayerComponent.class);
        this.health = pm.get(playerEntity).health;
        healthDecrease = false;
        // creates the healthbar through pixmap
        create();
    }

    public void update(float dt){
        // find updated health here
        setPosition(body.getPosition().x - 0.75f, body.getPosition().y + 1f);

        // if the health changes, draw create a new healthbar with correct health
        if(pm.get(playerEntity).health != health){
            //Checks if player is hit
            healthDecrease = true;
            health = pm.get(playerEntity).health;
            create();
        }
    }

    private void create(){

        int width = 150;
        int height = 20;
        Pixmap pixmap1 = createPixmap(width, height, health);

        Texture bar = new Texture(pixmap1);

        // makes it so healthbar.draw(game.batch) in PlayerX will draw correctly
        TextureRegion region1 = new TextureRegion(bar,0,0,150,20);
        setBounds(body.getPosition().x, body.getPosition().y + 100,1.5f, 0.2f);
        setRegion(region1);

        pixmap1.dispose();
    }

    private Pixmap createPixmap(int width, int height, int health){
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0,0,health,height);

        return pixmap;
    }

    //Used to play sound when hit. Used in PlayerOne and PlayerTwo classes
    public Boolean getHealthDecrease() {
        return healthDecrease;
    }

    public void setHealthDecrease() {
        healthDecrease = false;
    }
}
