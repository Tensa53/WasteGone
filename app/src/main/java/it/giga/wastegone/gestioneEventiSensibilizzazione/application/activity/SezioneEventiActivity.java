package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.EventAdapter;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;

public class SezioneEventiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eventi);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewEventi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Event> events = new ArrayList<>();
        events.add(new Event("Recycling Basics", "Click here for more info", R.drawable.event_placeholder));
        events.add(new Event("Composting", "Click here for more info", R.drawable.event_placeholder));
        events.add(new Event("Creative Recycling", "Click here for more info", R.drawable.event_placeholder));

        EventAdapter adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}