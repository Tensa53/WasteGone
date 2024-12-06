package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.sql.Timestamp;
import java.text.ParseException;
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

                // Conversione dell'ora
                try {
                    String dateTimeString = date + " " + hour + ":00";
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                    Timestamp timestamp = new Timestamp(format.parse(dateTimeString).getTime());

                    // Conversione in Firebase Timestamp
                    com.google.firebase.Timestamp firebaseTimestamp = new com.google.firebase.Timestamp(timestamp.getTime() / 1000, 0);

                    // Ottieni userID dall'entità e invia i dati tramite handleSubmit
                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    handleSubmit(userID, description, firebaseTimestamp);
                } catch (ParseException e) {
                    Toast.makeText(PrenotazioneActivity.this, "Errore nella formattazione della data/ora", Toast.LENGTH_SHORT).show();
                }
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
     * @param timestamp   timestamp dell'evento prenotato.
     */
    private void handleSubmit(String userID, String description, com.google.firebase.Timestamp timestamp) {
        // Logica per inviare i dati
        System.out.println("Dati inviati: userID = " + userID + ", Descrizione = " + description + ", Timestamp = " + timestamp);

        // Chiamata a PrenotazioneLogic
        PrenotazioneLogic prenotazioneLogic = new PrenotazioneLogic();
        prenotazioneLogic.handleSubmit(userID, description, timestamp)
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
                });
    }
}

