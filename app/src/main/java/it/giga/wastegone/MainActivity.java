package it.giga.wastegone;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.SezioneEventiActivity;
import it.giga.wastegone.gestioneProfiloUtente.application.activity.LoginActivity;
import it.giga.wastegone.gestioneProfiloUtente.application.activity.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


            tvWelcome = findViewById(R.id.tvWelcome);
            // Recupera il nome dell'utente (esempio fittizio)
            String username = ""; // Sostituire con il valore reale recuperato da Firebase
            if (username != null && !username.isEmpty()) {
                tvWelcome.setText("Benvenuto, " + username + "!");
            }

        setCardEffect(findViewById(R.id.cardEvents), SezioneEventiActivity.class);
        setCardEffect(findViewById(R.id.cardReports), LoginActivity.class);
        setCardEffect(findViewById(R.id.cardPickupPoints), RegisterActivity.class);

            findViewById(R.id.cardEvents).setOnClickListener(v -> {
                // Naviga all'activity eventi
                startActivity(new Intent(MainActivity.this, SezioneEventiActivity.class));
            });

            findViewById(R.id.cardReports).setOnClickListener(v -> {
                // Naviga all'activity segnalazioni
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            });

            findViewById(R.id.cardPickupPoints).setOnClickListener(v -> {
                // Naviga all'activity punti di ritiro (Google Maps)
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            });

    }

    private void setCardEffect(View card, Class<?> targetActivity) {
        card.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator.ofFloat(v, "scaleX", 1.05f).setDuration(100).start();
                    ObjectAnimator.ofFloat(v, "scaleY", 1.05f).setDuration(100).start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator.ofFloat(v, "scaleX", 1f).setDuration(100).start();
                    ObjectAnimator.ofFloat(v, "scaleY", 1f).setDuration(100).start();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.performClick();
                        startActivity(new Intent(MainActivity.this, targetActivity));
                    }
                    break;
            }
            return true;
        });
    }
}