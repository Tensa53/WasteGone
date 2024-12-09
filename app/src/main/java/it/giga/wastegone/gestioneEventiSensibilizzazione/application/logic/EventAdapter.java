package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

import it.giga.wastegone.MainActivity;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseEventDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;
    private Context context;
    private FirebaseEventDAO eventDAO;

    public EventAdapter(Context context, List<Event> eventList, FirebaseEventDAO eventDAO) {
        this.context = context;
        this.eventList = eventList;
        this.eventDAO = eventDAO;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.tvNome.setText(event.getNome());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void loadEventsFromDatabase() {
        Log.d("EventAdapter", "Caricamento eventi dal database...");
        eventDAO.doRetrieveAllEvent()
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
                        Log.e("EventAdapter", "Errore nel recupero degli eventi: ", task.getException());
                    }
                });
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvTitolo);

        }
    }

    public void onClickedListener(String title) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public class DescrizioneViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescrizione;


        public DescrizioneViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescrizione = itemView.findViewById(R.id.tvDescrizione);

        }
    }


}
