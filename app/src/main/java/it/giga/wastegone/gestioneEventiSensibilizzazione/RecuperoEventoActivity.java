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
        btStatoEvento1 = findViewById(R.id.btStatoEvento1);
        btStatoEvento2 = findViewById(R.id.btStatoEvento2);
        btStatoEvento3 = findViewById(R.id.btStatoEvento3);
        btStatoEvento4 = findViewById(R.id.btStatoEvento4);

        btStatoEvento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseEventDAO.doRetrieveAllEventByStato(Event.Stato.IN_PROGRAMMA).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()){
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
                        }else {
                            Log.e("FirestoreError", "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });

            }
        });




    }
}