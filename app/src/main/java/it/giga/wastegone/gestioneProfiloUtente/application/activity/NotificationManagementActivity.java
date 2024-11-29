package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import it.giga.wastegone.R;

public class NotificationManagementActivity extends AppCompatActivity {

    private TimePicker tpRifiutiDaConferire;
    private Switch swEventi, swTasse, swRifiuti, swPunti;
    private Button btnIndietro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        swEventi = findViewById(R.id.swEventi);
        swTasse = findViewById(R.id.swTasse);
        swRifiuti = findViewById(R.id.swRifiuti);
        swPunti = findViewById(R.id.swPunti);
        btnIndietro = findViewById(R.id.btnIndietro);
        tpRifiutiDaConferire = findViewById(R.id.tpRifiutiDaConferire);

        btnIndietro.setOnClickListener(v -> finish());

        // Configura notifiche per altri switch con orario statico
        swEventi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                scheduleNotification("Eventi di sensibilizzazione", 10, 0); // Orario statico
            }
        });

        swTasse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                scheduleNotification("Tasse in scadenza", 12, 0); // Orario statico
            }
        });

        swPunti.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                scheduleNotification("Nuovo punto di ritiro", 15, 0); // Orario statico
            }
        });

        // Configura la notifica per i rifiuti usando il TimePicker
        swRifiuti.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Ottieni l'ora e i minuti dal TimePicker
                int hour = tpRifiutiDaConferire.getHour();
                int minute = tpRifiutiDaConferire.getMinute();

                // Programma la notifica con l'orario selezionato
                scheduleNotification("Rifiuti da conferire", hour, minute);
            }
        });
    }

    private void scheduleNotification(String message, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Controlla se l'orario è nel passato e aggiusta per il giorno successivo
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Usa un Handler per ritardare la notifica fino all'orario specificato
        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();

        new android.os.Handler().postDelayed(() -> {
            showNotification(message);
        }, delay);

        Toast.makeText(this, "Notifica programmata: " + message, Toast.LENGTH_SHORT).show();
    }

    private void showNotification(String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "notifications_channel";

        // Crea il canale per Android Oreo e versioni successive
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Notifiche",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Configura l'intent per quando l'utente clicca sulla notifica (può riaprire l'app)
        Intent notificationIntent = new Intent(this, NotificationManagementActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Crea la notifica
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.logodark) // Icona della notifica
                .setContentTitle("Notifica WasteGone")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(contentIntent)
                .setAutoCancel(true);

        // Mostra la notifica
        notificationManager.notify(message.hashCode(), builder.build());
    }

}