package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;

public class LoadingState implements State{


    private GameStateManager gsm;
    private Screen currentScreen;

    public LoadingState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = gsm.game.screenFactory.getScreen("LOADING");
        gsm.game.setScreen(currentScreen);

    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("MENU");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            changeState(new MenuState(gsm));
        }
        currentScreen = gsm.game.screenFactory.getScreen(type);
        gsm.game.setScreen(currentScreen);
    }
}
