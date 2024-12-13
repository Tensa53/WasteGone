package it.giga.wastegone.gestioneEventiSensibilizzazione;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseEventDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Event;

/**
 * Activity per il recupero e la visualizzazione degli eventi salvati su Firestore in base al loro stato.
 * Permette agli utenti di filtrare e visualizzare gli eventi con stato "IN_CORSO", "TERMINATO" o "IN_PROGRAMMA".
 * Funzionalità principali:
 * - Recupero dei dati da Firestore tramite il DAO FirebaseEventDAO.
 * - Visualizzazione dei risultati nella TextView associata.
 * - Gestione degli stati degli eventi tramite pulsanti dedicati.
 */

public class RecuperoEventoActivity extends AppCompatActivity {
    TextView tvValue;
    Button btStatoEvento1;
    Button btStatoEvento2;
    Button btStatoEvento3;
    Button btStatoEvento4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recupero_evento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseEventDAO firebaseEventDAO = new FirebaseEventDAO();

        tvValue = findViewById(R.id.tvValue);
        // Stato in corso (IN_CORSO)
        btStatoEvento1 = findViewById(R.id.btStatoEvento1);
        // Stato sospeso (SOSPESO) - Non implementato poiche' non ci sono vincoli di validazione
        //btStatoEvento2 = findViewById(R.id.btStatoEvento2);
        // Stato terminato (TERMINATO)
        btStatoEvento3 = findViewById(R.id.btStatoEvento3);
        // Stato in programma (IN_PROGRAMMA)
        btStatoEvento4 = findViewById(R.id.btStatoEvento4);

        // recupero data eventi in corso
        btStatoEvento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseEventDAO.doRetrieveAllEventByStato(Event.Stato.IN_CORSO).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                tvValue.setText("NESSUN EVENTO");
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        tvValue.setText("" + document.get("data"));
                                    } catch (Exception e) {
                                        Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                                    }
                                }
                            }
                        } else {
                            Log.e("FirestoreError", "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });

            }
        });

        // recupero data eventi terminati
        btStatoEvento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseEventDAO.doRetrieveAllEventByStato(Event.Stato.TERMINATO).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                tvValue.setText("NESSUN EVENTO");
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        tvValue.setText("" + document.get("data"));
                                    } catch (Exception e) {
                                        Log.e("FirestoreError", "Errore nel parsing del documento: ", e);
                                    }
                                }
                            }
                        } else {
                            Log.e("FirestoreError", "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });

            }
        });

        // recupero data eventi in programma
        btStatoEvento4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseEventDAO.doRetrieveAllEventByStato(Event.Stato.IN_PROGRAMMA).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                tvValue.setText("NESSUN EVENTO");
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        tvValue.setText("" + document.get("data"));
                                    } catch (Exception e) {
                                        Log.e("FirestoreError", "Errore nel parsing del documento: ", e);
                                    }
                                }
                            }
                        } else {
                            Log.e("FirestoreError", "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });

            }
        });

    }
}