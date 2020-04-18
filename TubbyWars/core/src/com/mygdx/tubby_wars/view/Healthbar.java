package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

public class Healthbar extends Sprite {

    private int health;
    private Body body;

    private Texture bar;
    private TextureRegion region1;

    // ASHLEY
    private Entity playerEntity;
    private ComponentMapper<PlayerComponent> pm;

    public Healthbar(Body body, Entity playerEntity){

        this.body = body;
        this.playerEntity = playerEntity;
        pm = ComponentMapper.getFor(PlayerComponent.class);

        this.health = pm.get(playerEntity).health;

        // creates the healthbar through pixmap
        create();

        region1 = new TextureRegion(bar,0,0,150,20);
        setBounds(body.getPosition().x, body.getPosition().y + 100,1.5f, 0.2f);
        setRegion(region1);
    }

    public void update(float dt){
        // find updated health here
        setPosition(body.getPosition().x - 0.75f, body.getPosition().y + 1f);

        // if the health changes, draw create a new healthbar with correct health
        if(pm.get(playerEntity).health != health){
            create();
        }

        // må kjøre create() her for å få updated healthbaren, kan evt ha en if som sjekker om health er endret
    }




    private void create(){
        int width = 150;
        int height = 20;
        Pixmap pixmap1 = createPixmap(width, height, health);

        bar = new Texture(pixmap1);

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

}
