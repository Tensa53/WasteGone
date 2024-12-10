package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.giga.wastegone.R;

public class EventoActivity extends AppCompatActivity {

    private TextView tvDescrizione, tvData, tvOra, tvAddetti, tvTitolo, tvStato, tvLuogo;
    private Button btnIndietro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_evento);

        tvDescrizione = findViewById(R.id.tvDescrizione);
        tvAddetti = findViewById(R.id.tvAddetti);
        tvData = findViewById(R.id.tvData);
        tvOra = findViewById(R.id.tvOra);
        tvTitolo = findViewById(R.id.tvTitolo);
        tvStato = findViewById(R.id.tvStato);
        tvLuogo = findViewById(R.id.tvLuogo);
        btnIndietro = findViewById(R.id.btnIndietro);

        // configura il tasto per tornare indietro
        btnIndietro.setOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}