package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.annotation.SuppressLint;
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
//import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.TassaAdapter;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

// Activity per la visualizzazione delle tasse
public class TasseActivity extends AppCompatActivity {

    //private RecyclerView recyclerViewTasse;
    //private TassaAdapter tassaAdapter;
    //private List<Tassa> tasseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tasse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        recyclerViewTasse = findViewById(R.id.recyclerViewTasse);
        recyclerViewTasse.setLayoutManager(new LinearLayoutManager(this));


        tasseList = new ArrayList<>();
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp timestamp1 = new Timestamp(Objects.requireNonNull(dateFormat.parse("01/01/2025")).getTime());
            Timestamp timestamp2 = new Timestamp(Objects.requireNonNull(dateFormat.parse("15/03/2025")).getTime());

            tasseList.add(new Tassa("1", 50, timestamp1, false));
            tasseList.add(new Tassa("2", 100, timestamp2, true));
            tasseList.add(new Tassa("1", 50, timestamp1, false));
            tasseList.add(new Tassa("2", 100, timestamp2, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tassaAdapter = new TassaAdapter(this, tasseList);
        recyclerViewTasse.setAdapter(tassaAdapter);*/



    }
}