package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import it.giga.wastegone.MainActivity;
import it.giga.wastegone.R;
import it.giga.wastegone.databinding.ItemEventCardBinding;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.EventAdapter;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.EventiLogic;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseEventDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;
//import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.EventAdapter;
//import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;


//Activity che gestisce la sezione eventi contenente la lista degli eventi

public class SezioneEventiActivity extends AppCompatActivity {

    private Button btnIndietro;
    private TextView tvDescrizione;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eventi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnIndietro = findViewById(R.id.btnIndietro);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewEventi);
        FirebaseEventDAO eventDAO = new FirebaseEventDAO();
        List<Event> events = new ArrayList<>();
        EventAdapter adapter = new EventAdapter(this, events, eventDAO);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Carica gli eventi dal database
        adapter.loadEventsFromDatabase();

        //Listener per tornare indietro quando viene premuto il bottone Indietro
            btnIndietro.setOnClickListener(v -> finish());

        //Listener per visualizzare le informazioni dell'evento quando viene premuto il testo
//        tvDescrizione.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });


    }

    private List<Event> getEvents(List<Event> events) {
        EventiLogic eventiLogic = new EventiLogic();

        // Popola la lista degli eventi da Firebase
        eventiLogic.getAllEvent().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                // Converte i documenti in oggetti Event
                queryDocumentSnapshots.forEach(documentSnapshot -> {
                    Event event = documentSnapshot.toObject(Event.class);
                    events.add(event);
                });

            } else {
                // Gestione del caso in cui non ci siano eventi
                Toast.makeText(this, "Nessun evento disponibile", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            // Gestione degli errori
            Toast.makeText(this, "Errore nel caricamento degli eventi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });

        return events;
    }


    private void ontvDescrizioneClicked(String title) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}