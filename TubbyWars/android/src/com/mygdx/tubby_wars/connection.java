package com.mygdx.tubby_wars;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteUpdateResult;
import com.mygdx.tubby_wars.backend.IBackend;

import org.bson.Document;


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
    public void printPlayers() {
        System.out.println("hei hallo ");
        Document filterDoc = new Document()
                .append("reviews.0", new Document().append("$exists", true));

        RemoteFindIterable findResults = coll
                .find(filterDoc)
                .projection(new Document().append("_id", 0));

        findResults.forEach(item -> {
            System.out.println(Log.d("app", String.format("successfully found:  %s", item.toString())));

        });
    }


}


