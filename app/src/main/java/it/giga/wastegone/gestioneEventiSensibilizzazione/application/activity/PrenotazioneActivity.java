package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.PrenotazioneLogic;

/**
 * Attività per gestire la prenotazione di eventi di sensibilizzazione.
 */
public class PrenotazioneActivity extends AppCompatActivity {

    private EditText etDate, etDescription;
    private Spinner spHourSelection;
    private Button btnSubmit;

    /**
     * Metodo chiamato alla creazione dell'attività.
     *
     * @param savedInstanceState lo stato salvato dell'attività, se disponibile.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione);

        // Collegamento degli elementi UI
        etDate = findViewById(R.id.etDate);
        etDescription = findViewById(R.id.etDescription);
        spHourSelection = findViewById(R.id.spHourSelection);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Configurazione dello Spinner per le ore
        String[] hours = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hours);
        spHourSelection.setAdapter(adapter);

        // Configurazione del DatePicker per la selezione della data
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Configurazione del pulsante "Invia"
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String hour = spHourSelection.getSelectedItem().toString();

                // Validazione dei dati
                if (description.isEmpty() || date.isEmpty() || hour.isEmpty()) {
                    Toast.makeText(PrenotazioneActivity.this, "Compila tutti i campi prima di inviare", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ottieni userID dall'entità e invia i dati tramite handleSubmit
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // Invio dei dati con handleSubmit
                handleSubmit(userID, description, date, hour);
            }
        });
    }

    /**
     * Mostra un dialogo per selezionare una data.
     */
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            etDate.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E9FFEA")));
        datePickerDialog.show();
    }

    /**
     * Gestisce l'invio dei dati di prenotazione.
     *
     * @param userID      ID dell'utente che effettua la prenotazione.
     * @param description descrizione fornita dall'utente.
     * @param date        data dell'evento.
     * @param hour        ora dell'evento.
     */
    private void handleSubmit(String userID, String description, String date, String hour) {
        // Logica per inviare i dati


        Log.d("PrenotazioneActivity", "Dati inviati: userID = " + userID + ", Descrizione = " + description + ", Data = " + date + ", Ora = " + hour);

        // Logica per inviare i dati
        Log.e("PrenotazioneActivity", "Dati inviati: userID = " + userID + ", Descrizione = " + description + ", Data = " + date + ", Ora = " + hour);

        // Chiamata a PrenotazioneLogic
        /*PrenotazioneLogic prenotazioneLogic = new PrenotazioneLogic();
        prenotazioneLogic.handleSubmit(userID, description, date, hour)
                .addOnSuccessListener(aVoid -> {
                    // Mostra un messaggio di successo
                    Toast.makeText(this, "Prenotazione salvata con successo", Toast.LENGTH_SHORT).show();
                    // Naviga indietro alla pagina precedente
                    finish();
                })
                .addOnFailureListener(e -> {
                    // Mostra un messaggio di errore
                    Toast.makeText(this, "Errore durante il salvataggio: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                });*/
    }
}
