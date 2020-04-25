package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;

public interface State {


    void changeState(State state);

    boolean shouldChangeState(String type);

    void changeScreen(String type);

}
