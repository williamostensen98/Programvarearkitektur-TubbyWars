package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weapon extends Sprite {

    public TextureRegion region;
    public Texture texture;
    public float posX, posY;
    private Player player;

    public Weapon(Player player, float posX, float posY) {
        this.player = player;
        this.posX = posX;
        this.posY = posY;

        texture = new Texture("GunsSpriteSheet.png");
        region = new TextureRegion(texture, 0, 0, 128, 128);
        setBounds(player.b2Body.getPosition().x - posX, player.b2Body.getPosition().y - posY,1f, 0.5f);
        setRegion(region);
    }

    public void update(float dt){
        setPosition(player.b2Body.getPosition().x - posX, player.b2Body.getPosition().y - posY);
    }


}
