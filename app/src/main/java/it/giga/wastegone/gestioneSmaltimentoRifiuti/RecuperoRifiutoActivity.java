package it.giga.wastegone.gestioneSmaltimentoRifiuti;

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

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Comparator;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.dataAccess.FirebaseRifiutoDAO;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;

public class RecuperoRifiutoActivity extends AppCompatActivity {

    TextView tvValue;
    Button btColoreRifiuto1;
    Button btColoreRifiuto2;
    Button btGiornoRifiuto1;
    Button btGiornoRifiuto2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recupero_rifiuto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseRifiutoDAO firebaseRifiutoDAO = new FirebaseRifiutoDAO();

        tvValue = findViewById(R.id.tvValue);
        btColoreRifiuto1 = findViewById(R.id.btColoreRifiuto1);
        btColoreRifiuto2 = findViewById(R.id.btColoreRifiuto2);
        btGiornoRifiuto1 = findViewById(R.id.btGiornoRifiuto1);
        btGiornoRifiuto2 = findViewById(R.id.btGiornoRifiuto2);

        btColoreRifiuto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseRifiutoDAO.doRetrieveRifiutoByColore("Rosso").addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()){
                                tvValue.setText("INESISTENTE");
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        tvValue.setText("" + document.get("categoria"));
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


        btColoreRifiuto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        btGiornoRifiuto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DayOfWeek giornoConferimento = DayOfWeek.valueOf("MONDAY");
                firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()){
                                tvValue.setText("NON VALIDO");
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        tvValue.setText("" + document.get("categoria"));
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

        btGiornoRifiuto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

    }
}