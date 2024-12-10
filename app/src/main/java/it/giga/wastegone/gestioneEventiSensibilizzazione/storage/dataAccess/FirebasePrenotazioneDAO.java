package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Prenotazione;

/**
 * Questa classe rappresenta un oggetto per l'interazione con i dati delle prenotazioni
 * memorizzate nel database Firebase Firestore.
 */
public class FirebasePrenotazioneDAO {
    private static final String TABLE_NAME = "Prenotazioni";

    FirebaseFirestore db;


    /**
     * Costruttore della classe.
     * Inizializza l'istanza del database.
     */
    public FirebasePrenotazioneDAO() {
        db = FirebaseFirestore.getInstance();
    }


    /**
     * Recupera il documento della prenotazione tramite id.
     *
     * @param id, id del documento desiderato.
     * @return Task<DocumentSnapshot> rappresenta il risultato dell'operazione.
     */
    public Task<DocumentSnapshot> doRetrievePrenotazioneById(String id) {
        return db.collection(TABLE_NAME).document(id).get();
    }


    /**
     * Salva la prenotazione nel database.
     *
     * @param prenotazione, prenotazione da salvare.
     * @param id, id del documento in cui salvare la prenotazione.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doSavePrenotazione(Prenotazione prenotazione, String id) {
        return db.collection(TABLE_NAME).document(id).set(prenotazione);
    }


    /**
     * Salva la prenotazione nel database.
     *
     * @param prenotazione, prenotazione da salvare.
     * @return Task<DocumentReference> rappresenta il risultato dell'operazione.
     */
    public Task<DocumentReference> doSavePrenotazione(Prenotazione prenotazione) {
        return  db.collection(TABLE_NAME).add(prenotazione);
    }

    /**
     * Rimuove la prenotazione dal database.
     *
     * @param id, id del documento desiderato.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doDeletePrenotazione(String id) {
        return db.collection(TABLE_NAME).document(id).delete();
    }


    /**
     * Aggiorna i dati della prenotazione nel database.
     *
     * @param prenotazione, prenotazione aggiornata
     * @param id, id del documento da modificare.
     * @return Task<Void> rappresenta il risultato dell'operazione.
     */
    public Task<Void> doUpdatePrenotazione(Prenotazione prenotazione, String id) {
        DocumentReference doc = db.collection(TABLE_NAME).document(id);

        return doc.set(prenotazione);
    }

    /**
     * Recupera tutti i documenti delle prenotazioni effettuate da uno specifico utente.
     *
     * @param userID, id dell'utente desiderato.
     * @return Task<QuerySnapshot> un oggetto Task che contiene i risultati della query.
     */
    public Task<QuerySnapshot> doRetrieveAllPrenotazioniByUserId(String userID){
        return db.collection(TABLE_NAME).whereEqualTo("userID", userID).get();
    }
}
