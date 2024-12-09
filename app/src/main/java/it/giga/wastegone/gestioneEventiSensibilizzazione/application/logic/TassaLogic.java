package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Classe che gestisce la logica per il caricamento delle tasse dal database.
 */
public class TassaLogic {

    private FirebaseFirestore db;

    /**
     * Costruttore della classe TassaLogic.
     * Inizializza l'istanza di FirebaseFirestore.
     */
    public TassaLogic() {
        this.db = FirebaseFirestore.getInstance();
    }

    /**
     * Carica tutte le tasse per un determinato utente dal database.
     *
     * @param userID ID dell'utente.
     * @return Task contenente i risultati della query.
     */
    public Task<QuerySnapshot> loadTasseFromDatabase(String userID) {
        return db.collection("tasse")
                .whereEqualTo("userID", userID)
                .get();
    }
}