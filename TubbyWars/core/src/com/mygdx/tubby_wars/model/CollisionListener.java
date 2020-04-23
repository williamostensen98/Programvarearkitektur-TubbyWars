package com.mygdx.tubby_wars.model;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
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
        Entity playerEntity = ((PlayerModel) player.getUserData()).getPlayerEntity();

        // bullet is fixtureB, calculate damageMultiplier depending on bullet velocity
        float x = ((Bullet) bullet.getUserData()).b2Body.getLinearVelocity().x;
        float weaponDamage = playerSystem.getWeaponDamage(playerEntity);
        float damageMultiplier = ((abs(x) + 1)/10) * weaponDamage;

        bulletDamage(playerSystem, playerEntity, damageMultiplier);
    }

    private void bulletDamage(PlayerSystem playerSystem, Entity playerEntity, float damageMultiplier){
        playerSystem.dealDamage(playerEntity, (int)(10 * damageMultiplier));
        System.out.println(playerSystem.getUsername(playerEntity) + " took " + (int)(10 * damageMultiplier) + " damage");

        ImmutableArray players = playerSystem.getEntities();

        if(playerEntity == players.get(0)){
            playerSystem.setScore((Entity) players.get(1),(int)(100 * damageMultiplier));
        } else{
            playerSystem.setScore((Entity) players.get(0),(int)(100 * damageMultiplier));
        }
    }



    // check if either one is instance of player and bullet
    private boolean isSwitchContact(Fixture a, Fixture b) {
        if(a.getUserData() instanceof Bullet || b.getUserData() instanceof Bullet) {
            if(b.getUserData() instanceof PlayerModel || a.getUserData() instanceof PlayerModel) {
                return true;
            }
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
