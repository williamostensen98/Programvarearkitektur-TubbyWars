package com.mygdx.tubby_wars.backend;

public interface IBackend {

    //Declare methods needed here
    void Connect();

    String getPlayerName();

    int getScore();

    void setPlayerName();

    void setScore();

    void getTopTen();

}
