package com.mygdx.tubby_wars.model;


import com.mygdx.tubby_wars.view.PlayScreen;


public class ControllerLogic {

    /***
     * This class contains all needed variables and works as a storage.
     */
    public static boolean charging = false;
    public static boolean isPlayersTurn = false;


    public static Boolean loggedIn = false; //Used to check if users need to register usernames
    public static int roundCount = 0; //Counts how many rounds have been played

    //BITS - Collision filtering Box2D
    public static final short PLAYER_1 = 1;
    public static final short PLAYER_2 = 2;
    public static final short BULLET_1 = 4;
    public static final short BULLET_2 = 8;
    public static final short GROUND_BIT = 16;

    // PLAYSCREEN - stores the current game
    public static PlayScreen currentGame;

}
