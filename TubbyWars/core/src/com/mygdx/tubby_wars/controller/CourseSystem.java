package com.mygdx.tubby_wars.controller;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.tubby_wars.model.components.CourseComponent;

import java.util.List;

public class CourseSystem extends IteratingSystem {

    private static final Family family = Family.all(CourseComponent.class).get();
    private ComponentMapper<CourseComponent> cm;

    public CourseSystem(){
        super(family);
        cm = ComponentMapper.getFor(CourseComponent.class);
    }

    public void addPlayers(Entity course, List<Entity> players){

        if(!family.matches(course)){
            return;
        }

        CourseComponent cc = cm.get(course);

        cc.playerOne = players.get(0);
        cc.playerTwo = players.get(1);
    }

    @Override
    public void processEntity(Entity entity, float dt) {
        CourseComponent courseComponent = cm.get(entity);
    }
}
