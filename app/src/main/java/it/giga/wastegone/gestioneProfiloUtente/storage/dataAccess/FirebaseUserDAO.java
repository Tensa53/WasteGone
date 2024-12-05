package it.giga.wastegone.gestioneProfiloUtente.storage.dataAccess;

import it.giga.wastegone.gestioneProfiloUtente.storage.entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Questa classe rappresenta un oggetto per l'interazione con i dati degli utenti memorizzati
 * nel database Firebase Firestore.
 */
public class FirebaseUserDAO {
    private static final String TABLE_NAME = "Utenti";

    FirebaseFirestore db;

    /**
     * Costruttore della classe.
     * Inizializza l'istanza del database.
     */
    public FirebaseUserDAO() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Recupera il documento dell'utente tramite id.
     *
     * @param id, id del documento desiderato.
     * @return Task<DocumentSnapshot> rappresenta il risultato dell'operazione.
     */
    public Task<DocumentSnapshot> doRetrieveUserById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }

    /**
     * Salva l'utente nel database.
     *
     * @param user, utente da salvare.
     * @param id, id del documento in cui salvare l'utente.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doSaveUser(User user, String id) {
        return db.collection(TABLE_NAME).document(id).set(user);
    }

    /**
     * Rimuove l'utente dal database.
     *
     * @param id, id del documento desiderato.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doDeleteUser(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }

    /**
     * Aggiorna i dati dell'utente nel database.
     *
     * @param user, utente aggiornato
     * @param id, id del documento da modificare.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doUpdateUser(User user, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(user);
    }
}
