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
import it.giga.wastegone.gestioneProfiloUtente.application.activity.LoginActivity;

//Classe per la gestione delle segnalazioni
public class SegnalazioneActivity extends AppCompatActivity {

    private Button btnIndietro,btnSubmit;
    private EditText etIndirizzo,etDescription;

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
        // Torna indietro al clic sul pulsante
        btnIndietro.setOnClickListener(v -> finish());

        // Invia la segnalazione al clic sul pulsante
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String indirizzo = etIndirizzo.getText().toString();
                String descrizione = etDescription.getText().toString();
                if(indirizzo.isEmpty() || descrizione.isEmpty()){
                    Toast.makeText(SegnalazioneActivity.this, "Inserire tutti i campi", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SegnalazioneActivity.this, "Segnalazione inviata", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SegnalazioneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}