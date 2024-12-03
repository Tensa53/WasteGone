package it.giga.wastegone.gestioneProfiloUtente.application.logic;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import it.giga.wastegone.R;

/**
 * Classe che gestisce la logica delle notifiche.
 */
public class NotificationLogic extends BroadcastReceiver {

    /**
     * Metodo chiamato quando viene ricevuto un broadcast.
     *
     * @param context Il contesto dell'applicazione.
     * @param intent  L'intent che ha attivato il broadcast.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // Recupera il messaggio dagli extra dell'intent
        String message = intent.getStringExtra("message");

        // Ottiene il servizio di NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "notifications_channel";

        // Crea un canale di notifica per Android O e versioni successive
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Notifiche",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Crea e mostra la notifica
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logodark) // Imposta l'icona della notifica
                .setContentTitle("WasteGone ti ricorda:") // Imposta il titolo della notifica
                .setContentText(message) // Imposta il testo della notifica
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Imposta la priorità della notifica
                .setAutoCancel(true); // Rimuove automaticamente la notifica quando viene cliccata

        // Verifica che il messaggio non sia nullo e mostra la notifica
        assert message != null;
        notificationManager.notify(message.hashCode(), builder.build());
    }
}