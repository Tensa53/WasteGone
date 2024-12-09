package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

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

import it.giga.wastegone.R;
import it.giga.wastegone.utils.FormUtils;
import it.giga.wastegone.utils.FormUtils.PagamentoException;

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
        // Raccogli i dati inseriti dall'utente
        String nome = etNome.getText().toString().trim();
        String cognome = etCognome.getText().toString().trim();
        String numeroCarta = etNumeroCarta.getText().toString().trim();
        String dataScadenza = etDataScadenza.getText().toString().trim();
        String cvv = etCVV.getText().toString().trim();

        // Verifica che tutti i campi siano stati compilati
        if (nome.isEmpty() || cognome.isEmpty() || numeroCarta.isEmpty() || dataScadenza.isEmpty() || cvv.isEmpty()) {
            // Mostra un messaggio di errore se uno dei campi è vuoto
            Toast.makeText(this, "Per favore, compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica i dati della carta di credito
        FormUtils formUtils = new FormUtils();
        try {
            formUtils.controllaPagamento(nome, cognome, numeroCarta, dataScadenza, cvv);
        } catch (PagamentoException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        // Esegui l'operazione di pagamento (questo è solo un esempio, dovresti implementare la logica di pagamento reale)
        boolean pagamentoRiuscito = eseguiPagamento(nome, cognome, numeroCarta, dataScadenza, cvv);

        // Mostra un messaggio di conferma o errore in base all'esito del pagamento
        if (pagamentoRiuscito) {
            Toast.makeText(this, "Pagamento effettuato con successo", Toast.LENGTH_SHORT).show();
            finish(); // Chiudi l'activity
        } else {
            Toast.makeText(this, "Errore durante il pagamento", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo di esempio per eseguire il pagamento (da implementare con la logica reale)
    private boolean eseguiPagamento(String nome, String cognome, String numeroCarta, String dataScadenza, String cvv) {
        // Implementa qui la logica di pagamento
        // Restituisci true se il pagamento è riuscito, altrimenti false
        return true; // Questo è solo un esempio, dovresti implementare la logica reale
    }
}
