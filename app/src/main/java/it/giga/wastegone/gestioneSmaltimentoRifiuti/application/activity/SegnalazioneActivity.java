package it.giga.wastegone.gestioneSmaltimentoRifiuti.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import it.giga.wastegone.MainActivity;
import it.giga.wastegone.R;

/**
 * Activity per la gestione delle segnalazioni di smaltimento rifiuti.
 */
public class SegnalazioneActivity extends AppCompatActivity {

  private Button btnIndietro, btnSubmit;
  private EditText etIndirizzo, etDescription;

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
    setContentView(R.layout.activity_segnalazione);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    btnIndietro = findViewById(R.id.btnIndietro);
    btnSubmit = findViewById(R.id.btnSubmit);
    etIndirizzo = findViewById(R.id.etIndirizzo);
    etDescription = findViewById(R.id.etDescription);

    // Listener per tornare indietro quando viene premuto il bottone Indietro
    btnIndietro.setOnClickListener(v -> finish());

    // Listener per inviare la segnalazione quando viene premuto il bottone Invia
    btnSubmit.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String indirizzo = etIndirizzo.getText().toString();
        String descrizione = etDescription.getText().toString();
        if (indirizzo.isEmpty() || descrizione.isEmpty()) {
          Toast.makeText(SegnalazioneActivity.this, "Inserire tutti i campi",
                  Toast.LENGTH_SHORT).show();
          return;
        }
        Toast.makeText(SegnalazioneActivity.this, "Segnalazione inviata",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SegnalazioneActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });
  }
}