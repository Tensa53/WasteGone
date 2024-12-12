package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;

/**
 * Questa classe rappresenta un oggetto per l'interazione con i dati delle tasse
 * memorizzate nel database Firebase Firestore.
 */
public class FirebaseTassaDAO {
  private static final String TABLE_NAME = "Tasse";

  FirebaseFirestore db;


  /**
   * Costruttore della classe.
   * Inizializza l'istanza del database.
   */
  public FirebaseTassaDAO() {
    db = FirebaseFirestore.getInstance();
  }


  /**
   * Recupera il documento della tassa tramite id.
   *
   * @param id , id del documento desiderato.
   * @return Task < DocumentSnapshot > rappresenta il risultato dell'operazione.
   */
  public Task<DocumentSnapshot> doRetrieveTassaById(String id) {
    return db.collection(TABLE_NAME).document(id).get();
  }


  /**
   * Salva la tassa nel database.
   *
   * @param tassa , tassa da salvare.
   * @param id , id del documento in cui salvare la tassa.
   * @return Task < Void > rappresenta il risultato dell'operazione.
   */
  public Task<Void> doSaveTassa(Tassa tassa, String id) {
    return db.collection(TABLE_NAME).document(id).set(tassa);
  }


  /**
   * Salva la tassa nel database.
   *
   * @param tassa , tassa da salvare.
   * @return Task < DocumentReference > rappresenta il risultato dell'operazione.
   */
  public Task<DocumentReference> doSaveTassa(Tassa tassa) {
    return  db.collection(TABLE_NAME).add(tassa);
  }

  /**
   * Rimuove la tassa dal database.
   *
   * @param id , id del documento desiderato.
   * @return Task < Void > rappresenta il risultato dell'operazione.
   */
  public Task<Void> doDeleteTassa(String id) {
    return db.collection(TABLE_NAME).document(id).delete();
  }


  /**
   * Aggiorna i dati della tassa nel database.
   *
   * @param tassa , tassa aggiornata
   * @param id , id del documento da modificare.
   * @return Task < Void > rappresenta il risultato dell'operazione.
   */
  public Task<Void> doUpdateTassa(Tassa tassa, String id) {
    DocumentReference doc = db.collection(TABLE_NAME).document(id);

    return doc.set(tassa);
  }


  /**
   * Recupera tutti i documenti delle tasse di uno specifico utente.
   *
   * @param userID , id dell'utente desiderato.
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query.
   */
  public Task<QuerySnapshot> doRetrieveAllTasseByUserId(String userID) {
    return db.collection(TABLE_NAME).whereEqualTo("userID", userID).get();
  }


  /**
   * Recupera tutti i documenti delle tasse da pagare di uno specifico utente.
   *
   * @param userID , id dell'utente desiderato.
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query.
   */
  public Task<QuerySnapshot> doRetrieveAllTasseNonPagateByUserId(String userID) {
    return db.collection(TABLE_NAME).whereEqualTo("userID", userID)
            .whereEqualTo("isPagato", false).get();
  }
}
