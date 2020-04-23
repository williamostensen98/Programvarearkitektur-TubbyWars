package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;

public class PlayState implements State {

    private GameStateManager gsm;
    private Screen currentScreen;



    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = gsm.game.screenFactory.getScreen("PLAY");

    }

    @Override
    public boolean shouldChangeState(String type) {

        return type.equalsIgnoreCase("HIGHSCORE") ||
                type.equalsIgnoreCase("MENU") ;
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            State state = type.equalsIgnoreCase("HIGHSCORE") ? new GameOverState(gsm): new MenuState(gsm);
            changeState(state);
        }
        currentScreen = gsm.game.screenFactory.getScreen(type);
        gsm.game.setScreen(currentScreen);
    }



    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }


}
