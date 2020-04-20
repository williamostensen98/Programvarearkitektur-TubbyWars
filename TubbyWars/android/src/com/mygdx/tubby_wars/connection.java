package com.mygdx.tubby_wars;

import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteInsertOneResult;
import com.mygdx.tubby_wars.backend.IBackend;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class connection implements IBackend {
    StitchAppClient client;
    RemoteMongoClient mongoClient;
    RemoteMongoCollection <Document> coll;
    List<String> resultList;

    @Override
    public void connect() {
        client = Stitch.initializeDefaultAppClient("tubbywars-scpta");
        mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
        coll = mongoClient.getDatabase("tubbywars").getCollection("tubby");
        client.getAuth().loginWithCredential(new AnonymousCredential());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> getTopTen() {
        resultList = new ArrayList<>();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       coll.find()
                .sort(new Document().append("score", -1))
                .limit(10)
                .forEach(document -> {
                    this.resultList.add( document.get("name").toString() + "," + document.get("score").toString());
                });
        try {
            Thread.sleep(2000);
            return this.resultList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addResult(String name, int score) {
        Document newPlayer = new Document()
                .append("name", name).append("score", score);
        final Task<RemoteInsertOneResult> insertTask = coll.insertOne(newPlayer);

    }
}