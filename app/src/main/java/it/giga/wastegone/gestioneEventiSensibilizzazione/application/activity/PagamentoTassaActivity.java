package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.giga.wastegone.R;

// Activity per il pagamento delle tasse
public class PagamentoTassaActivity extends AppCompatActivity {

    // Componenti grafici
    private EditText etNome, etCognome, etNumeroCarta, etDataScadenza, etCVV;
    private Button btnConfermaPagamento, btnIndietro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagamento_tassa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Collegamento dei componenti grafici
        etNome = findViewById(R.id.etNome);
        etCognome = findViewById(R.id.etCognome);
        etNumeroCarta = findViewById(R.id.etNumeroCarta);
        etDataScadenza = findViewById(R.id.etDataScadenza);
        etCVV = findViewById(R.id.etCVV);
        btnConfermaPagamento = findViewById(R.id.btnConfermaPagamento);
        btnIndietro = findViewById(R.id.btnIndietro);

        // Imposta il listener per il clic sul pulsante di conferma pagamento
        btnConfermaPagamento.setOnClickListener(this::onConfermaPagamentoClicked);

        // Torna indietro al clic sul pulsante
        btnIndietro.setOnClickListener(v -> finish());
    }

    // Metodo chiamato al clic sul pulsante di conferma pagamento
    private void onConfermaPagamentoClicked(View view) {

    }




}
