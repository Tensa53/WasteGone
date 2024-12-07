package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Prenotazione;

/**
 * Classe che gestisce la logica di salvataggio di una prenotazione.
 */
public class PrenotazioneLogic {
  /**
  * Gestisce la logica di salvataggio di una prenotazione.
  *
  * @param userId      l'ID dell'utente che effettua la prenotazione
  * @param description la descrizione dell'evento
  * @param date        la data dell'evento

  * @return un Task che rappresenta l'operazione asincrona di salvataggio della prenotazione
  */
  public Task<Void> handleSubmit(String userId, String description, String date, String hour) {

    // Crea un'istanza della prenotazione
    Prenotazione prenotazione = new Prenotazione(userId, description, date, hour);

    // Salva la prenotazione nel database
    return savePrenotazione(prenotazione);

  }

  /**
  * Salva una prenotazione nel database.
  *
  * @param prenotazione la prenotazione da salvare
  * @return un Task che rappresenta l'operazione asincrona di salvataggio della prenotazione
  */
  public Task<Void> savePrenotazione(Prenotazione prenotazione) {
    return FirebaseFirestore.getInstance().collection("Prenotazioni")
            .add(prenotazione).continueWithTask(task -> {
              if (task.isSuccessful()) {
                return Tasks.forResult(null);
              } else {
                throw task.getException();
              }
            });
  }
}
