package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.ScreenFactory;

public class GameOverState implements State {


    private GameStateManager gsm;
    private Screen currentScreen;


    /***
     * This state handles when the game is over and when game is in this state the highscore screen is rendered.
     * When the menu or quit button is pressed the game goes back to Menu state.
     * @param gsm
     */
    GameOverState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = ScreenFactory.getScreen("HIGHSCORE");
        renderScreen();
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
        else {
            currentScreen = ScreenFactory.getScreen(type);
            renderScreen();
        }
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }

    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
    }
}
