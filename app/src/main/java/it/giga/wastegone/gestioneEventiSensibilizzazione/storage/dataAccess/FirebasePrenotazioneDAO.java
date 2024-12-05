package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Prenotazione;

public class FirebasePrenotazioneDAO {
    private static final String TABLE_NAME = "Prenotazioni";

    FirebaseFirestore db;


    public FirebasePrenotazioneDAO() {
        db = FirebaseFirestore.getInstance();
    }


    public Task<DocumentSnapshot> doRetrievePrenotazioneById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }


    public Task<Void> doSavePrenotazione(Prenotazione prenotazione, String id) {
        return db.collection(TABLE_NAME).document(id).set(prenotazione);
    }


    public Task<Void> doDeletePrenotazione(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    public Task<Void> doUpdatePrenotazione(Prenotazione prenotazione, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(prenotazione);
    }
}
