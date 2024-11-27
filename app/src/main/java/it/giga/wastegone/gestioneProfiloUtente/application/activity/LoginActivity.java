package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.giga.wastegone.R;

public class LoginActivity extends AppCompatActivity {
    private EditText emailText, passwordText;
    private Button loginButton;
    private TextView registratiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);

        emailText = findViewById(R.id.textemail);
        passwordText = findViewById(R.id.textpassword);
        loginButton = findViewById(R.id.login);
        registratiView = findViewById(R.id.registrati);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                // Metodo da implementare
                onLoginClicked(email, password);
            }
        });

        registratiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo da implementare
                onRegistratiClicked();
            }
        });

    }

    private void onRegistratiClicked() {
        Intent intent = new Intent(LoginActivity.this, RegistrazioneActivity.class);
        startActivity(intent);
    }

    private void onLoginClicked (String email, String password){
        }





}