package it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Calendar;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic.PrenotazioneLogic;
import it.giga.wastegone.utils.FormUtils;

/**
 * Activity per gestire le prenotazioni agli eventi di sensibilizzazione.
 */
public class PrenotazioneActivity extends AppCompatActivity {

  private EditText etDate;
  private EditText etDescription;
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
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_prenotazione);

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    etDate = findViewById(R.id.etDate);
    etDescription = findViewById(R.id.etDescription);
    spHourSelection = findViewById(R.id.spHourSelection);
    btnSubmit = findViewById(R.id.btnSubmit);

    String[] hours
            = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_dropdown_item, hours);
    spHourSelection.setAdapter(adapter);

    etDate.setOnClickListener(v -> showDatePickerDialog());

    btnSubmit.setOnClickListener(v -> {
      String description = etDescription.getText().toString().trim();
      String date = etDate.getText().toString().trim();
      String hour = spHourSelection.getSelectedItem().toString();

      if (description.isEmpty() || date.isEmpty() || hour.isEmpty()) {
        Toast.makeText(this, "Compila tutti i campi prima di inviare", Toast.LENGTH_SHORT).show();
        return;
      }

      String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
      handleSubmit(userId, description, date, hour);
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

    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
            (view, selectedYear, selectedMonth, dayOfMonth) -> {
              String selectedDate = dayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
              etDate.setText(selectedDate);
            }, year, month, day);

    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(
            Color.parseColor("#E9FFEA")));
    datePickerDialog.show();
  }

    /**
     * Gestisce l'invio dei dati di prenotazione.
     *
     * @param userId      ID dell'utente che effettua la prenotazione.
     * @param description Descrizione fornita dall'utente.
     * @param date        Data dell'evento.
     * @param hour        Ora dell'evento.
     */
    private void handleSubmit(String userId, String description, String date, String hour) {
        try {
            FormUtils formUtils = new FormUtils();
            formUtils.controllaPrenotazione(userId, description, date, hour);
            PrenotazioneLogic prenotazioneLogic = new PrenotazioneLogic();
            prenotazioneLogic.handleSubmit(userId, description, date, hour)
                    .addOnSuccessListener(success -> {
                        Toast.makeText(this, "Prenotazione salvata con successo", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(error -> {
                        Toast.makeText(this, "Errore durante il salvataggio: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    });

        } catch (FormUtils.PrenotazioneException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
