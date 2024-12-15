package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.List;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;

/**
 * Questa classe rappresenta un oggetto per l'interazione con i dati degli eventi di
 * sensibilizzazione memorizzati nel database Firebase Firestore.
 */
public class FirebaseEventDAO {
  private static final String TABLE_NAME = "Eventi";

  FirebaseFirestore db;

  /**
   * Costruttore della classe.
   * Inizializza l'istanza del database.
   */
  public FirebaseEventDAO() {
    db = FirebaseFirestore.getInstance();
  }


  /**
   * Recupera il documento dell'evento tramite id.
   *
   * @param id , id del documento desiderato.
   * @return Task < DocumentSnapshot > rappresenta il risultato dell'operazione.
   */
  public Task<DocumentSnapshot> doRetrieveEventById(String id) {
    return db.collection(TABLE_NAME).document(id).get();
  }


  /**
   * Salva l'evento nel database.
   *
   * @param event , evento da salvare.
   * @param id , id del documento in cui salvare l'evento.
   * @return Task < Void > rappresenta il risultato dell'operazione.
   */
  public Task<Void> doSaveEvent(Event event, String id) {
    return db.collection(TABLE_NAME).document(id).set(event);
  }

  /**
   * Salva l'evento nel database.
   *
   * @param event , evento da salvare.
   * @return Task < DocumentReference > rappresenta il risultato dell'operazione.
   */
  public Task<DocumentReference> doSaveEvent(Event event) {
    return  db.collection(TABLE_NAME).add(event);
  }


  /**
   * Rimuove l'evento dal database.
   *
   * @param id , id del documento desiderato.
   * @return Task < Void > rappresenta il risultato dell'operazione.
   */
  public Task<Void> doDeleteEvent(String id) {
    return db.collection(TABLE_NAME).document(id).delete();
  }


  /**
   * Aggiorna i dati dell'evento nel database.
   *
   * @param event , evento aggiornato
   * @param id , id del documento da modificare.
   * @return Task < Void > rappresenta il risultato dell'operazione.
   */
  public Task<Void> doUpdateEvent(Event event, String id) {
    DocumentReference doc = db.collection(TABLE_NAME).document(id);

    return doc.set(event);
  }

  /**
   * Recupera tutti i documenti degli eventi memorizzati nel database.
   *
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query-
   */
  public Task<QuerySnapshot> doRetrieveAllEvent() {
    return db.collection(TABLE_NAME).get();
  }


  /**
   * Recupera tutti i documenti degli eventi che si svolgono alla data specificata.
   *
   * @param data , data dell'evento desiderata.
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query.
   */
  public Task<QuerySnapshot> doRetrieveAllEventByData(String data) {
    return db.collection(TABLE_NAME).whereEqualTo("data", data).get();
  }


  /**
   * Recupera tutti i documenti degli eventi che hanno uno stato specifico.
   *
   * @param stato , statp dell'evento desiderata.
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query.
   */
  public Task<QuerySnapshot> doRetrieveAllEventByStato(Event.Stato stato) {
    return db.collection(TABLE_NAME).whereEqualTo("stato", stato).get();
  }


  /**
   * Recupera tutti i documenti degli eventi che non hanno uno stato definito.
   *
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query.
   */
  public Task<QuerySnapshot> doRetrieveAllEventWithoutStato() {
    List<String> valori = Arrays.asList("IN_CORSO", "SOSPESO", "TERMINATO", "IN_PROGRAMMA");
    return db.collection(TABLE_NAME).whereNotIn("stato", valori).get();
  }


  /**
   * Recupera tutti i documenti degli eventi che si svolgono in un luogo specificato.
   *
   * @param luogo , data dell'evento desiderata.
   * @return Task < QuerySnapshot > un oggetto Task che contiene i risultati della query.
   */
  public Task<QuerySnapshot> doRetrieveAllEventByLuogo(String luogo) {
    return db.collection(TABLE_NAME).whereEqualTo("luogo", luogo).get();
  }
}
