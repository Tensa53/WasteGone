package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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

    public Task<DocumentReference> doSaveEvent(Event event) {
        return  db.collection(TABLE_NAME).add(event);
    }


    public Task<Void> doDeleteEvent(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    public Task<Void> doUpdateEvent(Event event, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(event);
    }


    public Task<QuerySnapshot> doRetrieveAllEventByData(Timestamp data){
        return db.collection(TABLE_NAME).whereEqualTo("data", data).get();
    }


    public Task<QuerySnapshot> doRetrieveAllEventByStato(Event.Stato stato){
        return db.collection(TABLE_NAME).whereEqualTo("stato", stato).get();
    }


    public Task<QuerySnapshot> doRetrieveAllEventByLuogo(String luogo){
        return db.collection(TABLE_NAME).whereEqualTo("luogo", luogo).get();
    }
}
