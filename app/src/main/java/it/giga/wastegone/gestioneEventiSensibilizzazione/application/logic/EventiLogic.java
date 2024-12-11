package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseEventDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;
import java.util.ArrayList;
/**
 * Classe per la logica degli eventi.
 * Gestisce le operazioni di business logic sugli eventi.
 */

public class EventiLogic {
  private FirebaseFirestore db;
  FirebaseEventDAO eventDao = new FirebaseEventDAO();

  /**
   * Costruttore per EventiLogic.
   * Inizializza il database Firestore.
   */
  public EventiLogic() {
    this.db = FirebaseFirestore.getInstance();
  }

  public Task<QuerySnapshot> getAllEvent() {
    return eventDao.doRetrieveAllEvent(); // Recupera tutti i documenti dalla collezione
  }
}

