package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

import java.util.Stack;

public class GameStateManager {
    Stack<State> states;
    public TubbyWars game;

    public GameStateManager(TubbyWars game) {
        states  = new Stack<>();
        this.game = game;
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void changeState(State state){
        pop();
        states.push(state);
        System.out.println(state.toString());

    }

    public void changeScreen(String type){
        states.peek().changeScreen(type);

    }
}
