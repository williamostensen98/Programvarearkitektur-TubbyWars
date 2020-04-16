package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Healthbar {

    private int health;

    private ShapeRenderer sr;

    private Player player;



    public Healthbar(int health, Player player){
        this.health = health;
        this.player = player;
        sr = new ShapeRenderer();
    }

    public void update(float dt){
        // find updated health here
    }

    public void draw(){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.GREEN);
        sr.rect(player.b2Body.getPosition().x*100 - (50), player.b2Body.getPosition().y*100 + 70, 100,10);
        sr.setColor(Color.RED);
        sr.rect(player.b2Body.getPosition().x*100 - (50) + health,player.b2Body.getPosition().y*100 + 70,100 - health, 10);
        sr.end();
    }

}
