package it.giga.wastegone.gestioneSmaltimentoRifiuti.application.activity;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.application.logic.CalendarioAdapter;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;

public class CalendarioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CalendarioAdapter adapter;
    private List<Rifiuto> rifiutiList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        recyclerView = findViewById(R.id.recyclerViewCalendario);
        rifiutiList = new ArrayList<>();
        adapter = new CalendarioAdapter(this, rifiutiList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        // Carica i dati dal database
        loadRifiutiFromFirestore();
    }

    private void loadRifiutiFromFirestore() {
        db.collection("Rifiuti") // Nome della tua collezione Firestore
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    // Converte i dati Firestore in un oggetto Rifiuto
                                    String categoriaStr = document.getString("categoria");
                                    String istruzioni = document.getString("istruzioni");
                                    String giornoConferimentoStr = document.getString("giornoConferimento");
                                    String orarioConferimento = document.getString("orarioConferimento");
                                    String colore = document.getString("colore");
                                    String codiceColore = document.getString("codiceColore");

                                    Rifiuto.Categoria categoria = Rifiuto.Categoria.valueOf(categoriaStr);
                                    DayOfWeek giornoConferimento = DayOfWeek.valueOf(giornoConferimentoStr);

                                    rifiutiList.add(new Rifiuto(categoria, istruzioni, giornoConferimento,
                                            orarioConferimento, colore, codiceColore));
                                    Collections.sort(rifiutiList, Comparator.comparing(Rifiuto::getGiornoConferimento));

                                } catch (Exception e) {
                                    Log.e("FirestoreError", "Errore nel parsing del documento: ", e);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.e("FirestoreError", "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
    }
}
