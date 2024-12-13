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
import android.widget.Button;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.TassaAdapter;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseTassaDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;

/**
 * Activity che gestisce la lista delle tasse.
 */
public class TasseActivity extends AppCompatActivity {

  private RecyclerView recyclerViewTasse;
  private TassaAdapter tassaAdapter;
  private List<Tassa> tasseList;
  private Button btnIndietro;

  /**
   * Chiamato quando l'activity viene creata per la prima volta.
   *
   * @param savedInstanceState Se l'activity viene ri-inizializzata dopo essere stata
   *                           precedentemente chiusa, questo Bundle contiene i dati più recenti
   *                           forniti in onSaveInstanceState(Bundle).
   */
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

    btnIndietro = findViewById(R.id.btnIndietro);

    // Configura il RecyclerView
    recyclerViewTasse = findViewById(R.id.recyclerViewTasse);
    recyclerViewTasse.setLayoutManager(new LinearLayoutManager(this));

    // Inizializza la lista delle tasse e l'adapter
    tasseList = new ArrayList<>();
    FirebaseTassaDAO firebaseTassaDAO = new FirebaseTassaDAO();
    tassaAdapter = new TassaAdapter(this, tasseList, firebaseTassaDAO);
    recyclerViewTasse.setAdapter(tassaAdapter);

    // Listener per tornare indietro quando viene premuto il bottone Indietro
    btnIndietro.setOnClickListener(v -> finish());

    // Carica i dati dal database
    loadTasseFromDatabase();
  }

  /**
   * Chiamato quando l'activity viene ripresa.
   */
  @Override
  protected void onResume() {
    super.onResume();
    loadTasseFromDatabase();
  }

  /**
   * Carica la lista delle tasse dal database.
   */
  private void loadTasseFromDatabase() {
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Log.d("TasseActivity", "Caricamento dati dal database per l'utente: " + userID);
    tassaAdapter.loadTasseFromDatabase(userID);
  }
}