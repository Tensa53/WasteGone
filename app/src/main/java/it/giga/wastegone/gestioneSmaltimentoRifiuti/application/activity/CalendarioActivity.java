package it.giga.wastegone.gestioneSmaltimentoRifiuti.application.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.application.logic.CalendarioAdapter;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.dataAccess.FirebaseRifiutoDAO;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;

/**
 * Activity per visualizzare il calendario di smaltimento dei rifiuti.
 * Questa classe recupera i dati dal database Firestore e li visualizza in un RecyclerView.
 */
public class CalendarioActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private CalendarioAdapter adapter;
  private List<Rifiuto> rifiutiList;
  private FirebaseRifiutoDAO rifiutoDao;
  private Button btnIndietro;

  /**
   * Metodo chiamato durante la creazione dell'Activity.
   * Imposta il layout, inizializza il RecyclerView e carica i dati dal database Firestore.
   *
   * @param savedInstanceState lo stato salvato dell'Activity.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_calendario);

    ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main),
            (v, insets) -> {
              Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
              v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
              return insets;
            }
    );

    btnIndietro = findViewById(R.id.btnIndietro);
    recyclerView = findViewById(R.id.recyclerViewCalendario);
    rifiutiList = new ArrayList<>();
    adapter = new CalendarioAdapter(this, rifiutiList);
    recyclerView.setAdapter(adapter);

    btnIndietro.setOnClickListener(v -> finish());

    rifiutoDao = new FirebaseRifiutoDAO();

    // Carica i dati dal database
    loadRifiutiFromFirestore();
  }

  /**
   * Carica i dati dei rifiuti dalla collezione Firestore e li aggiunge alla lista.
   * Ordina la lista in base al giorno di conferimento e aggiorna l'adapter.
   */
  private void loadRifiutiFromFirestore() {
    rifiutoDao.doRetrieveAllRifiuti().addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        for (QueryDocumentSnapshot document : task.getResult()) {
          try {
            String categoriaStr = document.getString("categoria");
            String istruzioni = document.getString("istruzioni");
            String giornoConferimentoStr = document.getString("giornoConferimento");
            String orarioConferimento = document.getString("orarioConferimento");
            String colore = document.getString("colore");
            String codiceColore = document.getString("codiceColore");

            Rifiuto.Categoria categoria = Rifiuto.Categoria.valueOf(categoriaStr);
            DayOfWeek giornoConferimento = DayOfWeek.valueOf(giornoConferimentoStr);

            rifiutiList.add(
                    new Rifiuto(categoria, istruzioni, giornoConferimento,
                            orarioConferimento, colore, codiceColore)
            );

            rifiutiList.sort(
                    Comparator.comparing(Rifiuto::getGiornoConferimento)
            );
          } catch (Exception e) {
            Log.e("FirestoreError", "Errore nel parsing del documento: ", e);
          }
        }
        adapter.notifyDataSetChanged();
      } else {
        Log.e("FirestoreError", "Errore nel caricamento dei dati: ", task.getException());
      }
    });
  }
}
