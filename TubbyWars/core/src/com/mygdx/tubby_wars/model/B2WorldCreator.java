package com.mygdx.tubby_wars.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class B2WorldCreator {

    public float PPM = 100;

    public B2WorldCreator(World world, TiledMap map) {
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

        //  POLYGON SHAPES LIKE TREES

        /*for(MapObject obj: map.getLayers().get(2).getObjects().getByType(PolygonMapObject.class)){
            Shape polyShape = createPolygon((PolygonMapObject) obj);
            bdef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bdef);
            body.createFixture(polyShape, 1f);

        }*/

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

    private PolygonShape createPolygon(PolygonMapObject poly){
        PolygonShape polygon = new PolygonShape();
        float[] vertices = poly.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i] / PPM;
        }

        polygon.set(worldVertices);
        return polygon;
    }
}
