package com.mygdx.tubby_wars.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.PlayerModel;
import com.mygdx.tubby_wars.view.Bullet;

public class Physics {

    private float distance, angle;
    private boolean wasPressed = false;

    private Vector2 startPoint;
    private PlayerModel currentPlayer;
    public Bullet bullet;

    private Vector2 pressedPosition = new Vector2();
    private Vector2 currentPosition = new Vector2();
    private Vector2 velocityVector = new Vector2();


    public void setPlayer(PlayerModel player){
        currentPlayer = player;
        //startPoint = new Vector2(player.getBullet().b2Body.getPosition()).scl(100);

        startPoint = player.getStartPoint().scl(100);
        bullet = currentPlayer.getBullet();

    }


    /***
     * Given the startVelocity and the startpoint of the player this methos calculates the points to the trajectory of the players weapon.
     * It returns a vector with the nth point in the trajectory
     */
     public Vector2 getTrajectoryPoint(float n, Vector2 startVelocity){

         float t = 1 / 60f;
         float tt = t * t;
         float stepVelocityX = t * -startVelocity.x;
         float stepVelocityY = t * -startVelocity.y;
         float stepGravityX = tt * 0;
         float stepGravityY = tt * (-9.81f);

         //disse bruker pressed pos
//         float tpx = pressedPosition.x + n * stepVelocityX + 0.5f * (n * n + n) * stepGravityX;
//         float tpy = pressedPosition.y + n * stepVelocityY + 0.5f * (n * n + n) * stepGravityY;


         // disse bruker startpoint til player, men playerpos.x blir 5000 ish, og player står på 640
         // så for å fikse dette må vi på en eller annen måte finne playerpos.x PÅ current skjerm
//         float tpx = startPoint.x + n * stepVelocityX + 0.5f * (n * n + n) * stepGravityX;
//         float tpy = startPoint.y + n * stepVelocityY + 0.5f * (n * n + n) * stepGravityY;

         // hardkodet bare inn 640 her
         // TODO HVIS VI SKAL HA AT SPILLEREN ALLTID RESETTER SEG, GÅR DETTE FINT.
         //  MEN HVIS DET KUN SKAL RESETTE SEG VED AT DEN TREFFER EN VEG ELNS MÅ DENNE FIKSES.
         float tpx = 640 + n * stepVelocityX + 0.5f * (n * n + n) * stepGravityX;
         float tpy = startPoint.y + n * stepVelocityY + 0.5f * (n * n + n) * stepGravityY;

         //System.out.println("playerpos x: " + startPoint.x + "   -   pressedpos x: " + pressedPosition.x);


         return new Vector2(tpx , tpy);

     }
     /**
      * Draws a circle around the current player and checks iff the touchDown was within the range of that circle
      * If so it sets the flag to true
      * The params is the points where the screen was touched.
      * */
     public void pressed(int screenX, int screenY){

        // TODO BUG: AFTER BULLET IS FIRED YPU CAN STILL PRESS SCREEN TO FIRE
         pressedPosition.set(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
         wasPressed = true;
}

     /**
      * IF the player has been pressed and is currently beeing dragged its sets the currentposition
      * to the position of the finger at the screen.
      * It then calculates the vector between the pressed position (where the player is) and where the last dragged position is.
      * The distance and angle is so calculated.
      * */
     public void dragged(int screenX, int screenY){

         if(wasPressed){
            ControllerLogic.charging = true;
            currentPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
            velocityVector.set(currentPosition).sub(pressedPosition);
            distance = velocityVector.len()/22;
            angle = MathUtils.atan2(velocityVector.y, velocityVector.x);
            angle %= 2 * MathUtils.PI;
        }
     }

     /**
      * When the touch is let go of this method will calculate the velocities in the different directions and will
      * set the players bullet to this so it shoots.
      * */
     public void unPressed(){
         if(ControllerLogic.charging) {

             float velX = (2.25f * -MathUtils.cos(angle) * distance);
             float velY = (2.25f * -MathUtils.sin(angle) * distance);
             Vector2 velvec = new Vector2(velX, velY);
             ControllerLogic.charging = false;
             wasPressed = false;
             currentPlayer.showBullet();
             bullet.b2Body.setLinearVelocity(velvec);

         }

     }

    public Vector2 getVelocityVector() {
        return velocityVector;
    }
}
