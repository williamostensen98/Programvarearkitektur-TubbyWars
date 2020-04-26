package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states  = new Stack<>();
    }

    public void push(State state){
        states.push(state);
    }

    private void pop(){
        states.pop();
    }

    void changeState(State state){
        pop();
        states.push(state);
        System.out.println(state.toString());

    }

    public void changeScreen(String type){
        states.peek().changeScreen(type);

    }
}
