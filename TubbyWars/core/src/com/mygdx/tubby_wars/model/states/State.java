package com.mygdx.tubby_wars.model.states;

public interface State {

    /***
     * Sends a call to the game state manager to change state
     * @param state: The state to change to.
     */
    void changeState(State state);

    /***
     * Checks whether the state should change based on the screen type sent in.
     * It checks if the new screen is a trigger of a new state based on the current state
     * @param type: Screen type (MENU, LOADING, PLAY, SETTINGS etc..)
     * @return: boolean of whether to change state or not.
     */
    boolean shouldChangeState(String type);

    /***
     * Sets current screen to the screen with the given type if not a trigger of new state.
     * Renders the current screen to the game application.
     * @param type: type of screen to change to.
     */
    void changeScreen(String type);

    void renderScreen();

}
