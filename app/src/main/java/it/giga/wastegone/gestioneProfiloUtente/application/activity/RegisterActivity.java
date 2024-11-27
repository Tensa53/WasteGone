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

    EditText ET_Nome,ET_Cognome,ET_Email,ET_Indirizzo,ET_Password,ET_ConfermaPassword;
    Button B_Registrati;
    private TextView TVLogin;

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



        ET_Nome=findViewById(R.id.editTextNome);
        ET_Cognome=findViewById(R.id.editTextCognome);
        ET_Email=findViewById(R.id.editTextEmail);
        ET_Indirizzo=findViewById(R.id.editTextIndirizzo);
        ET_Password=findViewById(R.id.editTextPassword);
        ET_ConfermaPassword=findViewById(R.id.editTextConfermaPassword);
        B_Registrati=findViewById(R.id.buttonRegistrati);
        TVLogin=findViewById(R.id.textViewLogin);

        //fa onclick e manda le informazioni del form al metodo che permette la registrazione 
        B_Registrati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nome=ET_Nome.getText().toString();
                String cognome=ET_Cognome.getText().toString();
                String indirizzo=ET_Indirizzo.getText().toString();
                String email = ET_Email.getText().toString();
                String password = ET_Password.getText().toString();
                String confermaPassword=ET_ConfermaPassword.getText().toString();

                // Metodo da implementare onRegisterClicked
                // Metodo da implemetare CheckPass (vedere se password è uguale a confermaPassword)
                
                if(CheckPass(password,confermaPassword)){
                    onRegisterClicked(nome,cognome,indirizzo, email, password);
                }else{
                    //errore nella compilazione della password
                }

            }
        });

        TVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo da implementare
                ToLoginClicked();
            }
        });



    }
    //manda alla pagina Login
    private void ToLoginClicked(){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    //fa la registrazione 
    private void onRegisterClicked (String nome,String cognome,String indirizzo,String email, String password){
    }
    
    
    //vede se le password sono uguali
    private boolean CheckPass(String password, String confermaPassword){
        boolean passUguale=true;
        //cambiare passUguale (ho dovuto mettere una variabile  se no mi dava errore nel return)
        return passUguale;
    }


}