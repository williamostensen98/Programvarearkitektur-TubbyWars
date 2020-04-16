package com.mygdx.tubby_wars;

import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mygdx.tubby_wars.backend.IBackend;

// https://stitch.mongodb.com/groups/5e81d351b91d536e6d636684/apps/5e8afdc16898b88af61e26c3/sdks/android
// https://docs.mongodb.com/stitch-sdks/java/4/com/mongodb/stitch/android/core/Stitch.html#getDefaultAppClient--
public class connection implements IBackend {
    StitchAppClient client;
    RemoteMongoClient mongoClient;

    @Override
    public void Connect() {
        client = Stitch.initializeDefaultAppClient("tubbywars-scpta");
        mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
        //StitchAppClient mongoDB = mongoClient.getDatabase("tubbywars");
        //MongoCollection<Document> collection = mongoDB.getCollection("tubby");
    }


}


