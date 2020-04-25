package com.mygdx.tubby_wars.model;


import com.mygdx.tubby_wars.view.PlayScreen;

import java.util.ArrayList;


public class ControllerLogic {
    public static boolean charging = false;
    public static boolean isPlayersTurn = false;

    //Used to check if users need to register usernames
    public static Boolean loggedIn = false;
    public static Boolean fromHighScoreScreen = false;

    public static String username1 = "";
    public static String username2 = "";

    public static int roundCount = 0; //Counts how many rounds have been played

    //BITS
    public static final short PLAYER_1 = 1;
    public static final short PLAYER_2 = 2;
    public static final short BULLET_1 = 4;
    public static final short BULLET_2 = 8;
    public static final short GROUND_BIT = 16;

    // PLAYSCREEN
    public static PlayScreen currentGame;

}
