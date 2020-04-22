package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class B2WorldCreator {

    public float PPM = 100;

    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bdef =  new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //TODO: Update to match new maps (De håkon har laget)
        int mapNumber;
        if (ControllerLogic.roundCount == 1) {
            mapNumber = 2;
        }
        else if (ControllerLogic.roundCount == 2) {
            mapNumber = 1;
        }
        else {
            mapNumber = 2;
        }

        for(MapObject obj: map.getLayers().get(mapNumber).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = ControllerLogic.GROUND_BIT;
            body.createFixture(fdef);
        }
    }
}
