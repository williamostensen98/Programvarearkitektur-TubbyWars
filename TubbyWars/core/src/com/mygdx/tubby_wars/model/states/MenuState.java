package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.ScreenFactory;

public class MenuState implements State {

    private GameStateManager gsm;
    private Screen currentScreen;

    /***
     * All methods in this class is described in the State interface
     * This state renders the menu screen and all screens in reach before the game has started.
     * @param gsm: Game state manager
     */
    MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = ScreenFactory.getScreen("MENU");

        renderScreen();
    }

    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("PLAY");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            changeState(new PlayState(gsm));
        }else {
            currentScreen = ScreenFactory.getScreen(type);
            renderScreen();
        }
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }


}
