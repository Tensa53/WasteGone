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

public class RegisterActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etCognome;
    private EditText etEmail;
    private EditText etIndirizzo;
    private EditText etPassword;
    private EditText etConfermaPassword;
    private Button btnRegistrati;
    private TextView tvLogin;

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

        //fa onclick e manda le informazioni del form al metodo che permette la registrazione 
        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nome = etNome.getText().toString();
                String cognome = etCognome.getText().toString();
                String indirizzo = etIndirizzo.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confermaPassword = etConfermaPassword.getText().toString();

                // Metodo da implementare onRegisterClicked
                // Metodo da implemetare CheckPass (vedere se password è uguale a confermaPassword)
                
                if(checkPass(password,confermaPassword)){
                    onRegisterClicked(nome, cognome, indirizzo, email, password);
                }else{
                    //errore nella compilazione della password
                }

            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo da implementare
                toLoginClicked();
            }
        });



    }
    //manda alla pagina Login, il nome dell'activity di Login va cambiata secondo le  Naming Convention
    private void toLoginClicked(){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    //fa la registrazione 
    private void onRegisterClicked (String nome, String cognome, String indirizzo, String email, String password){
    }
    
    
    //vede se le password sono uguali
    private boolean checkPass(String password, String confermaPassword){
        boolean passUguale=true;
        //cambiare passUguale (ho dovuto mettere una variabile  se no mi dava errore nel return)
        return passUguale;
    }


}