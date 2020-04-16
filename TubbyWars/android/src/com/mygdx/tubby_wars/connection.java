package com.mygdx.tubby_wars;

import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mygdx.tubby_wars.backend.IBackend;

import org.bson.Document;

// https://stitch.mongodb.com/groups/5e81d351b91d536e6d636684/apps/5e8afdc16898b88af61e26c3/sdks/android
// https://docs.mongodb.com/stitch-sdks/java/4/com/mongodb/stitch/android/core/Stitch.html#getDefaultAppClient--
public class connection implements IBackend {
    StitchAppClient client;
    RemoteMongoClient mongoClient;
    RemoteMongoCollection<Document> coll;

    @Override
    public void Connect() {
        client = Stitch.initializeDefaultAppClient("tubbywars-scpta");
        mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
        coll = mongoClient.getDatabase("tubbywars").getCollection("tubby");
    }

    @Override
    public String getPlayerName() {
        System.out.println("GetPlayerName");
        return "GetPlayerName";
    }

    @Override
    public int getScore() {
        System.out.println("GetScore");
        return 123;
    }

    @Override
    public void setPlayerName() {
        System.out.println("setPlayerName");
    }

    @Override
    public void setScore() {
        System.out.println("SetScore");
    }

    public void getTopTen(){
        System.out.println(coll.find());

    }



}


