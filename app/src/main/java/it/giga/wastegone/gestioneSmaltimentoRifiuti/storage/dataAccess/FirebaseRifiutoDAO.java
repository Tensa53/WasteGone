package it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.DayOfWeek;

import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;

public class FirebaseRifiutoDAO {
    private static final String TABLE_NAME = "Rifiuti";

    FirebaseFirestore db;


    public FirebaseRifiutoDAO() {
        db = FirebaseFirestore.getInstance();
    }


    public Task<DocumentSnapshot> doRetrieveRifiutoById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }


    public Task<Void> doSaveRifiuto(Rifiuto rifiuto, String id) {
        return db.collection(TABLE_NAME).document(id).set(rifiuto);
    }


    public Task<Void> doDeleteRifiuto(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    public Task<Void> doUpdateRifiuto(Rifiuto rifiuto, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(rifiuto);
    }


    public Task<QuerySnapshot> doRetrieveAllRifiuti() {
        return db.collection(TABLE_NAME).get();
    }


    public Task<QuerySnapshot> doRetrieveAllRifiutiSpeciali() {
        return db.collection(TABLE_NAME).
               whereEqualTo("categoria", Rifiuto.Tipo.SPECIALE).get();
    }


    public Task<QuerySnapshot> doRetrieveAllRifiutiUrbani() {
        return db.collection(TABLE_NAME).
                whereEqualTo("categoria", Rifiuto.Tipo.URBANO).get();
    }


    public Task<QuerySnapshot> doRetrieveAllRifiutiByDayOfWeek(DayOfWeek dayOfWeek) {
        return db.collection(TABLE_NAME).
                whereEqualTo("giornoConferimento", dayOfWeek).get();
    }


    public Task<QuerySnapshot> doRetrieveAllRifiutiByMateriale(String materiale) {
        return db.collection(TABLE_NAME).
                whereEqualTo("materiale", materiale).get();
    }

    public Task<QuerySnapshot> doRetrieveAllRifiutiByCategoria(Rifiuto.Categoria categoria) {
        return db.collection(TABLE_NAME).
                whereEqualTo("categoria", categoria).get();
    }
}
