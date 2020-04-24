package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;

public interface State {


    public void changeState(State state);

    boolean shouldChangeState(String type);

    public void changeScreen(String type);

}
