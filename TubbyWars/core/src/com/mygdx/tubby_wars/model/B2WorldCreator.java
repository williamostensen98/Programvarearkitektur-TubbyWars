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

    /***
     * This class takes in the world and a map and renders all objects of the map into a World body
     * thus giving it the possibilty og collison with other objects.
     * @param world: game world (Box2D)
     * @param map: Tiled Map ( map of the curremt game)
     */
    public B2WorldCreator(World world, TiledMap map) {
        float PPM = 100;
        BodyDef bdef =  new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // ALL RECTANGLE SHAPES LIKE GROUND AND ETC
        for(MapObject obj: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = ControllerLogic.GROUND_BIT;
            body.createFixture(fdef);
        }

        // BACK EDGES OF THE MAP
        for(MapObject obj: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
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
