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

import java.util.Calendar;

import it.giga.wastegone.R;

/**
 * Classe che gestisce l'attività di prenotazione.
 */
public class PrenotazioneActivity extends AppCompatActivity {

    private EditText etDate, etDescription;
    private Spinner spHourSelection;
    private Button btnSubmit;

    /**
     * Metodo chiamato alla creazione dell'activity.
     *
     * @param savedInstanceState stato precedentemente salvato dell'activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione);

        // Collegare i widget con gli ID
        etDate = findViewById(R.id.etDate);
        etDescription = findViewById(R.id.etDescription);
        spHourSelection = findViewById(R.id.spHourSelection);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Configurare il menu a tendina (Spinner)
        String[] hours = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hours);
        spHourSelection.setAdapter(adapter);

        // Configurare il DatePicker per etDate
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Configurare il pulsante "Invia"
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

                // Converti i primi due numeri dell'orario in int
                int hourInt;
                try {
                    hourInt = Integer.parseInt(hour.split(":")[0]);
                } catch (NumberFormatException e) {
                    Toast.makeText(PrenotazioneActivity.this, "Errore nell'orario selezionato", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Simula l'invio dei dati // da rimuovere
                String message = "Descrizione: " + description + "\n" +
                        "Data: " + date + "\n" +
                        "Orario: " + hourInt + ":00";
                Toast.makeText(PrenotazioneActivity.this, "Dati inviati:\n" + message, Toast.LENGTH_LONG).show();

                handleSubmit(description, date, hourInt);
            }
        });
    }

    /**
     * Mostra un DatePickerDialog per selezionare una data.
     */
    private void showDatePickerDialog() {
        // Ottieni la data corrente
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Mostra il DatePickerDialog con il colore personalizzato
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            // Formatta e imposta la data nell'EditText
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            etDate.setText(selectedDate);
        }, year, month, day);

        // Imposta il colore del DatePickerDialog
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E9FFEA")));
        datePickerDialog.show();
    }



    /**
     * Gestisce l'invio dei dati della prenotazione.
     *
     * @param description la descrizione dell'evento
     * @param date la data selezionata
     * @param hour l'ora selezionata
     */
    private void handleSubmit(String description, String date, int hour) {
        // Logica da implementare
    }
}
