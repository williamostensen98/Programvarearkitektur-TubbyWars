package com.mygdx.tubby_wars.model.states;

public interface State {


    void changeState(State state);

    boolean shouldChangeState(String type);

    void changeScreen(String type);

}
