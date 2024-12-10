package it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.DayOfWeek;

import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;

/**
 * Questa classe rappresenta un oggetto per l'interazione con i dati dei rifiuti memorizzati
 * nel database Firebase Firestore.
 */
public class FirebaseRifiutoDAO {
    private static final String TABLE_NAME = "Rifiuti";

    FirebaseFirestore db;


    /**
     * Costruttore della classe.
     * Inizializza l'istanza del database.
     */
    public FirebaseRifiutoDAO() {
        db = FirebaseFirestore.getInstance();
    }


    /**
     * Recupera il documento del rifiuto tramite id.
     *
     * @param id, id del documento desiderato.
     * @return Task<DocumentSnapshot> rappresenta il risultato dell'operazione.
     */
    public Task<DocumentSnapshot> doRetrieveRifiutoById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }


    /**
     * Salva il rifiuto nel database.
     *
     * @param rifiuto, rifiuto da salvare.
     * @param id, id del documento in cui salvare il rifiuto.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doSaveRifiuto(Rifiuto rifiuto, String id) {
        return db.collection(TABLE_NAME).document(id).set(rifiuto);
    }


    /**
     * Rimuove il rifiuto dal database.
     *
     * @param id, id del documento desiderato.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doDeleteRifiuto(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    /**
     * Aggiorna i dati del rifiuto nel database.
     *
     * @param rifiuto, rifiuto aggiornato
     * @param id, id del documento da modificare.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doUpdateRifiuto(Rifiuto rifiuto, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(rifiuto);
    }


    /**
     * Recupera tutti i documenti dei rifiuti memorizzati nel database.
     *
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllRifiuti() {
        return db.collection(TABLE_NAME).get();
    }


    /**
     * Recupera tutti i documenti dei rifiuti speciali memorizzati nel database.
     *
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllRifiutiSpeciali() {
        return db.collection(TABLE_NAME).
               whereEqualTo("tipo", Rifiuto.Tipo.SPECIALE).get();
    }


    /**
     * Recupera tutti i documenti dei rifiuti urbani memorizzati nel database.
     *
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllRifiutiUrbani() {
        return db.collection(TABLE_NAME).
                whereEqualTo("tipo", Rifiuto.Tipo.URBANO).get();
    }


    /**
     * Recupera tutti i documenti dei rifiuti che si conferiscono al giorno specificato.
     *
     * @param dayOfWeek giorno di conferimento desiderato.
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllRifiutiByDayOfWeek(DayOfWeek dayOfWeek) {
        return db.collection(TABLE_NAME).
                whereEqualTo("giornoConferimento", dayOfWeek).get();
    }


    /**
     * Recupera tutti i documenti dei rifiuti costituiti dal materiale specificato.
     *
     * @param materiale materiale desiderato.
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllRifiutiByMateriale(String materiale) {
        return db.collection(TABLE_NAME).
                whereEqualTo("materiale", materiale).get();
    }

    /**
     * Recupera tutti i documenti dei rifiuti appartenenti alla categoria specificata.
     *
     * @param categoria categoria desiderata.
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllRifiutiByCategoria(Rifiuto.Categoria categoria) {
        return db.collection(TABLE_NAME).
                whereEqualTo("categoria", categoria).get();
    }
}
