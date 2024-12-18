package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.giga.wastegone.R;

/**
 * Classe che mostra i dettagli di un evento.
 */
public class EventoActivity extends AppCompatActivity {

  private TextView  tvData, tvOra, tvAddetti, tvTitolo, tvStato, tvLuogo, tvDescrizione;
  private Button btnIndietro;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_evento);

    // Collegamento delle variabili agli elementi del layout
    tvAddetti = findViewById(R.id.tvAddetti);
    tvData = findViewById(R.id.tvData);
    tvOra = findViewById(R.id.tvOra);
    tvTitolo = findViewById(R.id.tvNome);
    tvStato = findViewById(R.id.tvStato);
    tvLuogo = findViewById(R.id.tvLuogo);
    tvDescrizione = findViewById(R.id.tvDescrizione);
    btnIndietro = findViewById(R.id.btnIndietro);

    // Configura il tasto per tornare indietro
    btnIndietro.setOnClickListener(v -> finish());

    // Imposta i dati dell'evento ricevuti tramite intent
    Intent intent = getIntent();
    tvTitolo.setText(intent.getStringExtra("title"));
    tvData.setText(intent.getStringExtra("date"));
    tvOra.setText(intent.getStringExtra("time"));
    tvAddetti.setText(intent.getStringExtra("staff"));
    tvStato.setText(intent.getStringExtra("status"));
    tvLuogo.setText(intent.getStringExtra("location"));
    tvDescrizione.setText(intent.getStringExtra("description"));

    ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                  Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                  v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                          systemBars.bottom);
                  return insets;
                }
    );
  }
}
