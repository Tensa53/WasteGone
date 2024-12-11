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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.SezioneEventiActivity;
import it.giga.wastegone.gestioneProfiloUtente.application.activity.LoginActivity;
import it.giga.wastegone.gestioneProfiloUtente.application.activity.RegisterActivity;
//import it.giga.wastegone.gestioneSmaltimentoRifiuti.application.activity.MapsActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

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
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Recupera il nome dell'utente attualmente loggato
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DocumentReference docRef = db.collection("utenti").document(userId);
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String username = documentSnapshot.getString("nome");
                    if (username != null && !username.isEmpty()) {
                        tvWelcome.setText("Benvenuto, " + username + "!");
                    }
                }
            }).addOnFailureListener(e -> {
                // Handle the error
                Toast.makeText(MainActivity.this, "Errore nel recupero del nome utente",
                        Toast.LENGTH_SHORT).show();
            });
        }

        // Imposta l'effetto di pressione sui card e passa all'activity corrispondente
        setCardEffect(findViewById(R.id.cardEvents), SezioneEventiActivity.class);
        setCardEffect(findViewById(R.id.cardReports), LoginActivity.class);
        //setCardEffect(findViewById(R.id.cardPickupPoints), MapsActivity.class);



    }

// Metodo per impostare l'effetto di pressione sui card
    private void setCardEffect(View card, Class<?> targetActivity) {
        card.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator.ofFloat(v, "scaleX", 1.05f).setDuration(100).
                            start();
                    ObjectAnimator.ofFloat(v, "scaleY", 1.05f).setDuration(100).
                            start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator.ofFloat(v, "scaleX", 1f).setDuration(100).
                            start();
                    ObjectAnimator.ofFloat(v, "scaleY", 1f).setDuration(100).
                            start();
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