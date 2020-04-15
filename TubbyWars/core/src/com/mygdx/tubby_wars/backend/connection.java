package com.mygdx.tubby_wars.backend;

// https://stitch.mongodb.com/groups/5e81d351b91d536e6d636684/apps/5e8afdc16898b88af61e26c3/sdks/android
// https://docs.mongodb.com/stitch-sdks/java/4/com/mongodb/stitch/android/core/Stitch.html#getDefaultAppClient--
public class connection {
        final StitchAppClient client =
                Stitch.initializeDefaultAppClient("tubbywars-scpta");
    /*
        final RemoteMongoClient mongoClient =
                client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");

        final RemoteMongoCollection<Document> coll =
                mongoClient.getDatabase("<DATABASE>").getCollection("<COLLECTION>");

    client.getAuth().loginWithCredential(new AnonymousCredential()).continueWithTask(
            new Continuation<StitchUser, Task<RemoteUpdateResult>>() {

            @Override
            public Task<RemoteUpdateResult> then(@NonNull Task<StitchUser> task) throws Exception {
                if (!task.isSuccessful()) {
                    Log.e("STITCH", "Login failed!");
                    throw task.getException();
                }

                final Document updateDoc = new Document(
                        "owner_id",
                        task.getResult().getId()
                );

                updateDoc.put("number", 42);
                return coll.updateOne(
                        null, updateDoc, new RemoteUpdateOptions().upsert(true)
                );
            }
        }
    ).continueWithTask(new Continuation<RemoteUpdateResult, Task<List<Document>>>() {
            @Override
            public Task<List<Document>> then(@NonNull Task<RemoteUpdateResult> task) throws Exception {
                if (!task.isSuccessful()) {
                    Log.e("STITCH", "Update failed!");
                    throw task.getException();
                }
                List<Document> docs = new ArrayList<>();
                return coll
                        .find(new Document("owner_id", client.getAuth().getUser().getId()))
                        .limit(100)
                        .into(docs);
            }
        }).addOnCompleteListener(new OnCompleteListener<List<Document>>() {
            @Override
            public void onComplete(@NonNull Task<List<Document>> task) {
                if (task.isSuccessful()) {
                    Log.d("STITCH", "Found docs: " + task.getResult().toString());
                    return;
                }
                Log.e("STITCH", "Error: " + task.getException().toString());
                task.getException().printStackTrace();
            }
        });*/

}
