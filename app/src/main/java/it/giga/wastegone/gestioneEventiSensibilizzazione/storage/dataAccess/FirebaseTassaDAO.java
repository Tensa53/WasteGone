package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;

public class FirebaseTassaDAO {
    private static final String TABLE_NAME = "Tasse";

    FirebaseFirestore db;


    public FirebaseTassaDAO() {
        db = FirebaseFirestore.getInstance();
    }


    public Task<DocumentSnapshot> doRetrieveTassaById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }


    public Task<Void> doSaveTassa(Tassa tassa, String id) {
        return db.collection(TABLE_NAME).document(id).set(tassa);
    }


    public Task<Void> doDeleteTassa(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    public Task<Void> doUpdateTassa(Tassa tassa, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(tassa);
    }

    public Task<QuerySnapshot> doRetrieveAllTasseByUserId(String userID){
        return db.collection(TABLE_NAME).whereEqualTo("userId", userID).get();
    }

    public Task<QuerySnapshot> doRetrieveAllTasseNonPagateByUserId(String userID){
        return db.collection(TABLE_NAME).whereEqualTo("userId", userID).
                                         whereEqualTo("isPagato", false).get();
    }
}
