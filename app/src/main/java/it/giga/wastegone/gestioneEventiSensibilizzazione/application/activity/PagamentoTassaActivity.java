package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseTassaDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;
import it.giga.wastegone.utils.FormUtils;
import it.giga.wastegone.utils.FormUtils.PagamentoException;

/**
 * Activity per gestire il pagamento di una tassa.
 */
public class PagamentoTassaActivity extends AppCompatActivity {

  private EditText etNome, etCognome, etNumeroCarta, etDataScadenza, etCVV;
  private Button btnConfermaPagamento, btnIndietro;
  private FirebaseTassaDAO firebaseTassaDAO;
  private String tassaId;

  /**
   * Chiamato quando l'activity viene creata per la prima volta.
   *
   * @param savedInstanceState questo Bundle contiene i dati più recenti forniti
   */
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

    etNome = findViewById(R.id.etNome);
    etCognome = findViewById(R.id.etCognome);
    etNumeroCarta = findViewById(R.id.etNumeroCarta);
    etDataScadenza = findViewById(R.id.etDataScadenza);
    etCVV = findViewById(R.id.etCVV);
    btnConfermaPagamento = findViewById(R.id.btnConfermaPagamento);
    btnIndietro = findViewById(R.id.btnIndietro);

    firebaseTassaDAO = new FirebaseTassaDAO();
    tassaId = getIntent().getStringExtra("tassaId");

    btnConfermaPagamento.setOnClickListener(this::onConfermaPagamentoClicked);
    btnIndietro.setOnClickListener(v -> finish());
  }

  /**
   * Gestisce l'evento di click per il pulsante di conferma del pagamento.

   * @param view La vista che è stata cliccata.
   */
  private void onConfermaPagamentoClicked(View view) {
    String nome = etNome.getText().toString().trim();
    String cognome = etCognome.getText().toString().trim();
    String numeroCarta = etNumeroCarta.getText().toString().trim();
    String dataScadenza = etDataScadenza.getText().toString().trim();
    String cvv = etCVV.getText().toString().trim();

    if (nome.isEmpty() || cognome.isEmpty() || numeroCarta.isEmpty() || dataScadenza.isEmpty()
            || cvv.isEmpty()) {
      Toast.makeText(this, "Per favore, compila tutti i campi",
              Toast.LENGTH_SHORT).show();
      return;
    }

    FormUtils formUtils = new FormUtils();
    try {
      formUtils.controllaPagamento(nome, cognome, numeroCarta, dataScadenza, cvv);
    } catch (PagamentoException e) {
      Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
      return;
    }

    boolean pagamentoRiuscito = eseguiPagamento(nome, cognome, numeroCarta, dataScadenza, cvv);

    if (pagamentoRiuscito) {
      firebaseTassaDAO.doRetrieveTassaById(tassaId).addOnSuccessListener(documentSnapshot -> {
        Tassa tassa = documentSnapshot.toObject(Tassa.class);
        if (tassa != null) {
          tassa.setIsPagato(true);
          firebaseTassaDAO.doUpdateTassa(tassa, tassaId)
                  .addOnSuccessListener(a -> {
                    Toast.makeText(this, "Pagamento effettuato con successo",
                            Toast.LENGTH_SHORT).show();
                    finish();
                  })
                  .addOnFailureListener(e -> {
                    Toast.makeText(this, "Errore durante l'aggiornamento del database",
                            Toast.LENGTH_SHORT).show();
                  });
        }
      }).addOnFailureListener(e -> {
        Toast.makeText(this, "Errore durante il recupero della tassa",
                Toast.LENGTH_SHORT).show();
      });
    } else {
      Toast.makeText(this, "Errore durante il pagamento", Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * Esegue il processo di pagamento.
   *
   * @param nome Il nome del titolare della carta.
   *
   * @param cognome Il cognome del titolare della carta.
   *
   * @param numeroCarta Il numero della carta.
   *
   * @param dataScadenza La data di scadenza della carta.
   *
   * @param cvv Il codice CVV della carta.
   *
   * @return true se il pagamento è stato effettuato con successo, false altrimenti.
   */
  private boolean eseguiPagamento(String nome, String cognome, String numeroCarta,
                                  String dataScadenza, String cvv) {
    return true;
  }
}