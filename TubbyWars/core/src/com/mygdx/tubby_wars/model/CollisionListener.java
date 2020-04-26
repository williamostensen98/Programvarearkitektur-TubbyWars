package com.mygdx.tubby_wars.model;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.view.Bullet;

import static java.lang.StrictMath.abs;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if(isSwitchContact(fixtureA, fixtureB)){
            if(fixtureA.getUserData() instanceof PlayerModel){
                whenImpact(fixtureA, fixtureB);
            }
            else{
                whenImpact(fixtureB, fixtureA);
            }
        }
    }

    private void whenImpact(Fixture player, Fixture bullet){
        // find playerSystem and entity corresponding with the player which is hit by the bullet
        PlayerSystem playerSystem = ((PlayerModel) player.getUserData()).getPlayerSystem();
        Entity playerHit = ((PlayerModel) player.getUserData()).getPlayerEntity();
        ImmutableArray players = playerSystem.getEntities();

        // bullet is fixtureB, calculate damageMultiplier depending on bullet velocity
        float x = ((Bullet) bullet.getUserData()).b2Body.getLinearVelocity().x;
        float weaponDamage = playerSystem.getWeaponDamage(playerHit == players.get(0) ? (Entity)players.get(1) : (Entity)players.get(0));
        float damageMultiplier = (abs(x) / weaponDamage);
        bulletDamage(playerSystem, playerHit, damageMultiplier);
    }

    private void bulletDamage(PlayerSystem playerSystem, Entity playerEntity, float damageMultiplier){
        playerSystem.dealDamage(playerEntity, (int)((damageMultiplier * 3) ));

        ImmutableArray players = playerSystem.getEntities();
        int score = (int)(((damageMultiplier) * (damageMultiplier) * (damageMultiplier)) / 2);
        playerSystem.setScore(playerEntity == players.get(0) ? (Entity) players.get(1) : (Entity) players.get(0), score);
    }

    // check if either one is instance of player and bullet
    private boolean isSwitchContact(Fixture a, Fixture b) {
        if(a.getUserData() instanceof Bullet || b.getUserData() instanceof Bullet) {
            return b.getUserData() instanceof PlayerModel || a.getUserData() instanceof PlayerModel;
        }
        return false;
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
