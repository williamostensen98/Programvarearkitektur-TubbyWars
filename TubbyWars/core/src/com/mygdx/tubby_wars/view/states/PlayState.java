package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

public class PlayState implements State {


    private GameStateManager gsm;
    private Screen currentScreen;
    private TubbyWars game = TubbyWars.getInstance();

    PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = game.screenFactory.getScreen("PLAY");
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
        currentScreen = game.screenFactory.getScreen(type);
        game.setScreen(currentScreen);
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }


}
