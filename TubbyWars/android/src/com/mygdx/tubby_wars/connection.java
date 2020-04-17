package com.mygdx.tubby_wars;

import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mygdx.tubby_wars.backend.IBackend;


public class connection implements IBackend {
    StitchAppClient client;
    RemoteMongoClient mongoClient;
    RemoteMongoCollection coll;

    @Override
    public void Connect() {
        client = Stitch.initializeDefaultAppClient("tubbywars-scpta");
        //client = Stitch.getDefaultAppClient();
        mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
        coll = mongoClient.getDatabase("tubbywars").getCollection("tubby");



    }

    @Override
    public void printPlayers() {
        System.out.println("hei hallo ");

        RemoteFindIterable findResults = coll.find();
        Task first = findResults.first();
        //first.getResult()
        if(first.isSuccessful()) {
            System.out.println("Trying to fetch task:" + first.getResult());
        } else {
            System.out.println("Error with loading");

        }

        /*findResults.forEach(item -> {
            System.out.println(Log.d("app", String.format("successfully found: %s", item.toString())));
        });*/



    }

}


