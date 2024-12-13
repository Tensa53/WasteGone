package it.giga.wastegone.gestioneSmaltimentoRifiuti.application.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import it.giga.wastegone.R;

/**
 * Activity che apre Google Maps con i punti di ritiro dei rifiuti.
 */
public class MapsActivity extends AppCompatActivity {

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
    setContentView(R.layout.activity_maps);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    // Crea un Uri per l'intent di Google Maps con la posizione e la query specificate
    Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?z=10&q=Punti+di+ritiro+rifiuti");
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

    // Imposta il package per assicurarsi che l'intent venga gestito da Google Maps
    mapIntent.setPackage("com.google.android.apps.maps");
    startActivity(mapIntent);

    // Chiude l'activity
    finish();
  }
}