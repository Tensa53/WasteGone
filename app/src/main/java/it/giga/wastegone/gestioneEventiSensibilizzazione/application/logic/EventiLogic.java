package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseEventDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;

public class EventiLogic {
    private FirebaseFirestore db;
   FirebaseEventDAO eventDAO = new FirebaseEventDAO();

    public EventiLogic() {
        this.db = FirebaseFirestore.getInstance();
    }
    public Task<QuerySnapshot> getAllEvent() {
        return eventDAO.doRetrieveAllEvent(); // Recupera tutti i documenti dalla collezione
    }
}

