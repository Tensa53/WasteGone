package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import static com.google.android.gms.tasks.Task.*;
import static com.google.android.gms.tasks.Tasks.forResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Prenotazione;
import it.giga.wastegone.gestioneProfiloUtente.storage.dataAccess.FirebaseUserDAO;
import it.giga.wastegone.gestioneProfiloUtente.storage.entity.User;

public class PrenotazioneLogic {
    /**
     * Gestisce la logica di salvataggio di una prenotazione.
     *
     * @param userID      l'ID dell'utente che effettua la prenotazione
     * @param description la descrizione dell'evento
     * @param date        la data dell'evento

     * @return un Task che rappresenta l'operazione asincrona di salvataggio della prenotazione
     */
    /*public Task<Void> handleSubmit(String userID, String description, Timestamp date) {

        // Crea un'istanza della prenotazione
        Prenotazione prenotazione = new Prenotazione(userID, description, date);

        // Salva la prenotazione nel database
        return savePrenotazione(prenotazione);

    }

    public Task<Void> savePrenotazione(Prenotazione prenotazione) {
        return FirebaseFirestore.getInstance().collection("Prenotazioni").add(prenotazione).continueWithTask(task -> {
            if (task.isSuccessful()) {
                return Tasks.forResult(null);
            } else {
                throw task.getException();
            }
        });
    }*/
}
