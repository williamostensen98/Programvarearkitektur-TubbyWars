package com.mygdx.tubby_wars.model;

public class ControllerLogic {
    public static boolean charging = false;
    public static boolean isPlayersTurn = false;
    public static boolean showBullet = false;
    public static boolean hasFired = false;

    //Used to check if users need to register usernames
    public static Boolean loggedIn = false;

    public static String username1 = "";
    public static String username2 = "";

    //TODO: Change to prefered formate
    //Chooses character. Either r (red) or p (purple) depending on players choise.
    public static String character1 = "";

    //Chooses character. Either y (yellow) or g (green) depending on players choise.
    public static String character2 = "";

    //BITS

    public static final short PLAYER_1 = 1;
    public static final short PLAYER_2 = 2;
    public static final short BULLET_1 = 4;
    public static final short BULLET_2 = 8;
    public static final short GROUND_BIT = 16;


    //PLAYER POSITIONS


    // WEAPON AND BULLET LOGICS - DICTIONARY

}
