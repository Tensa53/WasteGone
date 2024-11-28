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

public class LoginActivity extends AppCompatActivity {
    private EditText etMail, etPassword;
    private Button btnLogin;
    private TextView tvRegistrati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);

        etMail = findViewById(R.id.textemail);
        etPassword = findViewById(R.id.textpassword);
        btnLogin = findViewById(R.id.loginbutton);
        tvRegistrati = findViewById(R.id.registrati);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Quando viene premuto il bottone accedi vengono inviate  le informazioni del form al metodo che permette il login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Devi compilare entrambi i campi!", Toast.LENGTH_SHORT).show();
                } else {
                    // Metodo da implementare se i campi sono validi
                    onLoginClicked(email, password);
                }

                // Metodo da implementare
                onLoginClicked(email, password);
            }
        });
        // Quando viene premuto il testo "Registrati" viene aperta la schermata di registrazione
        tvRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ontvRegistratiClicked();
            }
        });

    }

    //Metodo usato per aprire la schermata di registrazione
    private void ontvRegistratiClicked() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    //Metodo usato per effettuare il login
    private void onLoginClicked (String email, String password){

        }





}