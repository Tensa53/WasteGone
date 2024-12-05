package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;

public class FirebaseEventDAO {
    private static final String TABLE_NAME = "Eventi";

    FirebaseFirestore db;


    public FirebaseEventDAO() {
        db = FirebaseFirestore.getInstance();
    }


    public Task<DocumentSnapshot> doRetrieveEventById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }


    public Task<Void> doSaveEvent(Event event, String id) {
        return db.collection(TABLE_NAME).document(id).set(event);
    }


    public Task<Void> doDeleteEvent(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    public Task<Void> doUpdateEvent(Event event, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(event);
    }
}
