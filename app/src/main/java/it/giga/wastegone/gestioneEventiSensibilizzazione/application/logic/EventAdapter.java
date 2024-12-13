package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.EventoActivity;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseEventDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;
import java.util.List;
/**
 * Adapter per visualizzare una lista di eventi in un RecyclerView.
 * Permette di visualizzare i dettagli di un evento cliccando su di esso.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
  private List<Event> eventList;
  private Context context;
  private FirebaseEventDAO eventDao;

  /**
   * Costruttore per EventAdapter.
   *
   * @param context   il contesto in cui viene utilizzato l'adapter
   * @param eventList la lista degli eventi da visualizzare
   * @param eventDao  l'oggetto di accesso ai dati per gli eventi
   */
  public EventAdapter(Context context, List<Event> eventList, FirebaseEventDAO eventDao) {
    this.context = context;
    this.eventList = eventList;
    this.eventDao = eventDao;
  }

  /**
   * Crea un nuovo ViewHolder per un elemento evento.
   *
   * @param parent   il ViewGroup genitore
   * @param viewType il tipo di vista del nuovo View
   * @return un nuovo EventViewHolder
   */
  @NonNull
  @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_event_card, parent, false);
    return new EventViewHolder(view);
  }

  /**
   * Associa un evento a un ViewHolder.
   *
   * @param holder   il ViewHolder a cui associare l'evento
   * @param position la posizione dell'evento nella lista
   */
  @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
    Event event = eventList.get(position);
    holder.tvTitolo.setText(event.getNome());
    holder.tvDescrizione.setText("Clicca qui per maggiori informazioni");

    holder.tvDescrizione.setOnClickListener(v -> {
      Intent intent = new Intent(context, EventoActivity.class);
      intent.putExtra("title", event.getNome());
      intent.putExtra("date", event.getData());
      intent.putExtra("time", event.getOra());
      intent.putExtra("staff", event.getNomiAddetti());
      intent.putExtra("status", event.getStato().toString());
      intent.putExtra("location", event.getLuogo());
      intent.putExtra("description", event.getProgrammazione());
      context.startActivity(intent);
    });
  }

  /**
   * Restituisce il numero totale di eventi nella lista.
   *
   * @return il numero di eventi
   */
  @Override
    public int getItemCount() {
    return eventList.size();
  }

  /**
  * Carica gli eventi dal database e aggiorna l'adapter.
  */
  public void loadEventsFromDatabase() {
    Log.d("EventAdapter", "Caricamento eventi dal database...");
    eventDao.doRetrieveAllEvent()
                .addOnCompleteListener(task -> {
                  if (task.isSuccessful()) {
                    eventList.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                      try {
                        Event event = document.toObject(Event.class);
                        eventList.add(event);
                      } catch (Exception e) {
                        Log.e("EventAdapter", "Errore nel parsing del documento: ", e);
                      }
                    }
                    notifyDataSetChanged();
                    Log.d("EventAdapter", "Eventi caricati con successo.");
                  } else {
                    Log.e("EventAdapter", "Errore nel recupero degli eventi: ",
                                task.getException());
                  }
                });
  }

  /**
  * ViewHolder per un elemento evento.
  * Contiene i riferimenti alle viste dell'elemento evento.
  */
  public static class EventViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitolo;
    TextView tvDescrizione;


    /** Costruttore per EventViewHolder.
     *
     * @param itemView la vista dell'elemento evento
     */
    public EventViewHolder(@NonNull View itemView) {
      super(itemView);
      tvTitolo = itemView.findViewById(R.id.tvTitolo);
      tvDescrizione = itemView.findViewById(R.id.tvDescrizione);
    }
  }
}
