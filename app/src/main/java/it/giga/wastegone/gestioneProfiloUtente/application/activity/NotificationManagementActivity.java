package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.util.Calendar;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneProfiloUtente.application.logic.NotificationLogic;
import it.giga.wastegone.utils.FormUtils;
import it.giga.wastegone.utils.FormUtils.OrarioException;

/**
 * Activity per la gestione delle notifiche.
 */
public class NotificationManagementActivity extends AppCompatActivity {

  private TimePicker tpRifiutiDaConferire;
  private Switch swEventi, swTasse, swRifiuti, swPunti;
  private Button btnIndietro;
  private SharedPreferences sharedPreferences;

  /**
   * Metodo chiamato alla creazione dell'activity.
   *
   * @param savedInstanceState stato precedentemente salvato dell'activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_notification);

    sharedPreferences = getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE);


    // Richiedi permessi per notifiche (Android 13+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
              != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
      }
    }

    // Collegamento dei componenti grafici
    swEventi = findViewById(R.id.swEventi);
    swTasse = findViewById(R.id.swTasse);
    swRifiuti = findViewById(R.id.swRifiuti);
    swPunti = findViewById(R.id.swPunti);
    btnIndietro = findViewById(R.id.btnIndietro);
    tpRifiutiDaConferire = findViewById(R.id.tpRifiutiDaConferire);

    // Restore switch states from SharedPreferences
    swEventi.setChecked(sharedPreferences.getBoolean("swEventi", false));
    swTasse.setChecked(sharedPreferences.getBoolean("swTasse", false));
    swRifiuti.setChecked(sharedPreferences.getBoolean("swRifiuti", false));
    swPunti.setChecked(sharedPreferences.getBoolean("swPunti", false));

    // Torna indietro al clic sul pulsante
    btnIndietro.setOnClickListener(v -> finish());

    // Configura notifiche per altri switch con orario statico
    swEventi.setOnCheckedChangeListener((buttonView, isChecked) -> {
      sharedPreferences.edit().putBoolean("swEventi", isChecked).apply();
      if (isChecked) {
        scheduleNotification("C'è un evento di sensibilizzazione", 10, 0); // Orario statico
      }
    });

    swTasse.setOnCheckedChangeListener((buttonView, isChecked) -> {
      sharedPreferences.edit().putBoolean("swTasse", isChecked).apply();
      if (isChecked) {
        scheduleNotification("Ci sono delle tasse in scadenza", 14, 37); // Orario statico
      }
    });

    swPunti.setOnCheckedChangeListener((buttonView, isChecked) -> {
      sharedPreferences.edit().putBoolean("swPunti", isChecked).apply();
      if (isChecked) {
        scheduleNotification("C'è un nuovo punto di ritiro", 15, 0); // Orario statico
      }
    });

    // Configura la notifica per i rifiuti usando il TimePicker
    swRifiuti.setOnCheckedChangeListener((buttonView, isChecked) -> {
      sharedPreferences.edit().putBoolean("swRifiuti", isChecked).apply();
      if (isChecked) {
        // Ottieni l'ora e i minuti dal TimePicker
        int hour = tpRifiutiDaConferire.getHour();
        int minute = tpRifiutiDaConferire.getMinute();

        // Controlla l'orario
        FormUtils formUtils = new FormUtils();
        try {
          formUtils.controllaOrario(hour, minute, isChecked);
          // Programma la notifica con l'orario selezionato
          scheduleNotification("Ci sono rifiuti da conferire", hour, minute);
        } catch (OrarioException e) {
          Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
      }
    });

    // Aggiungi un listener per il TimePicker
    tpRifiutiDaConferire.setOnTimeChangedListener((view, hourOfDay, minute) -> {
      swRifiuti.setChecked(false);
    });
  }

  /**
   * Programma una notifica con il messaggio e l'orario specificati.
   *
   * @param message il messaggio della notifica
   * @param hour l'ora della notifica
   * @param minute i minuti della notifica
   */
  @SuppressLint("ScheduleExactAlarm")
  private void scheduleNotification(String message, int hour, int minute) {

    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    // Verifica se l'app ha il permesso per impostare allarmi esatti (Android 12+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      if (!alarmManager.canScheduleExactAlarms()) {
        Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
        startActivity(intent);
        return;
      }
    }

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, 0);

    if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
      calendar.add(Calendar.DAY_OF_MONTH, 1);
    }

    Intent intent = new Intent(this, NotificationLogic.class);
    intent.putExtra("message", message);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(
            this,
            message.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
    );

    if (alarmManager != null) {
      alarmManager.setExactAndAllowWhileIdle(
              AlarmManager.RTC_WAKEUP,
              calendar.getTimeInMillis(),
              pendingIntent
      );
    }

    Toast.makeText(this, "Notifica programmata ", Toast.LENGTH_SHORT).show();
  }
}