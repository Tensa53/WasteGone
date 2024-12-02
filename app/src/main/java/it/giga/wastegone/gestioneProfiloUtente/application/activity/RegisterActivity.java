package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;
import it.giga.wastegone.utils.FormUtils;

/**
 * Activity per la registrazione di un nuovo utente.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText etNome, etCognome, etEmail, etIndirizzo, etPassword, etConfermaPassword;
    private Button btnRegistrati;
    private TextView tvLogin;

    /**
     * Metodo chiamato alla creazione dell'activity.
     *
     * @param savedInstanceState stato precedentemente salvato dell'activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNome = findViewById(R.id.etNome);
        etCognome = findViewById(R.id.etCognome);
        etEmail = findViewById(R.id.etEmail);
        etIndirizzo = findViewById(R.id.etIndirizzo);
        etPassword = findViewById(R.id.etPassword);
        etConfermaPassword = findViewById(R.id.etConfermaPassword);
        btnRegistrati = findViewById(R.id.btnRegistrati);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String cognome = etCognome.getText().toString();
                String indirizzo = etIndirizzo.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confermaPassword = etConfermaPassword.getText().toString();

                if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || email.isEmpty() || password.isEmpty() || confermaPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Compila tutti i campi!", Toast.LENGTH_SHORT).show();
                } else {
                    onRegisterClicked(nome, cognome, indirizzo, email, password, confermaPassword);
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginClicked();
            }
        });
    }

    /**
     * Metodo usato per aprire la schermata di login.
     */
    private void toLoginClicked() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo usato per gestire il click sul bottone di registrazione.
     *
     * @param nome il nome dell'utente
     * @param cognome il cognome dell'utente
     * @param indirizzo l'indirizzo dell'utente
     * @param email l'email dell'utente
     * @param password la password dell'utente
     * @param confermaPassword la password confermata dell'utente
     */
    private void onRegisterClicked(String nome, String cognome, String indirizzo, String email, String password, String confermaPassword) {
        try {
            FormUtils formUtils = new FormUtils();
            formUtils.controllaRegistrazione(email, password, confermaPassword);

            // Logica di registrazione (es. salva i dati dell'utente, naviga a un'altra activity)
            Toast.makeText(RegisterActivity.this, "Registrazione effettuata con successo!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Chiudi l'activity corrente
        } catch (RegistrazioneException e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}