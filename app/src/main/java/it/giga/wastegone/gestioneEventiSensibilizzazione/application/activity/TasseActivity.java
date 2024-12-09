/*package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.TassaAdapter;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseTassaDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class TasseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasse;
    private TassaAdapter tassaAdapter;
    private List<Tassa> tasseList;
    private FirebaseTassaDAO dao = new FirebaseTassaDAO();

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


        recyclerViewTasse = findViewById(R.id.recyclerViewTasse);
        recyclerViewTasse.setLayoutManager(new LinearLayoutManager(this));

        tasseList = new ArrayList<>();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("TassaAdapter", "Caricamento dati dal database...");
        dao.doRetrieveAllTasseByUserId(userID).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("TasseActivity", document.getId() + " => " + document.getData());
                }
                //tassaAdapter = new TassaAdapter(this, tasseList);
                //recyclerViewTasse.setAdapter(tassaAdapter);
            }
        });
       /* try {
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

        tassaAdapter = new TassaAdapter(TasseActivity.this, tasseList);
        recyclerViewTasse.setAdapter(tassaAdapter);



    }

}*/
package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.TassaAdapter;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseTassaDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;

public class TasseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasse;
    private TassaAdapter tassaAdapter;
    private List<Tassa> tasseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tasse);

        // Configura i padding per le barre di sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configura il RecyclerView
        recyclerViewTasse = findViewById(R.id.recyclerViewTasse);
        recyclerViewTasse.setLayoutManager(new LinearLayoutManager(this));

        // Inizializza la lista delle tasse e l'adapter
        tasseList = new ArrayList<>();
        FirebaseTassaDAO firebaseTassaDAO = new FirebaseTassaDAO();
        tassaAdapter = new TassaAdapter(this, tasseList, firebaseTassaDAO);
        recyclerViewTasse.setAdapter(tassaAdapter);

        // Carica i dati dal database
        loadTasseFromDatabase();
    }

    private void loadTasseFromDatabase() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("TasseActivity", "Caricamento dati dal database per l'utente: " + userID);
        tassaAdapter.loadTasseFromDatabase(userID);
    }
}