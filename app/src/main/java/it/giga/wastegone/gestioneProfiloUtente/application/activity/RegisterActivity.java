package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

import it.giga.wastegone.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNome, etCognome, etDataNascita, etEmail, etIndirizzo,
            etPassword, etConfermaPassword;
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
       // etDataNascita=findViewById(R.id.etDataNascita);
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
               // String dataNascita = etDataNascita.getText().toString();
                String indirizzo = etIndirizzo.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confermaPassword = etConfermaPassword.getText().toString();


                if (nome.isEmpty() || cognome.isEmpty()  /*||  dataNascita.isEmpty()*/
                        || indirizzo.isEmpty()|| email.isEmpty() || password.isEmpty()
                        || confermaPassword.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "Compila tutti i campi!",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // Metodo da implementare
                    onRegisterClicked(nome, cognome,/* dataNascita,*/ indirizzo, email, password);
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

        //apre il calendario e fa decidere la data
        /*
        etDataNascita.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        etDataNascita.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
        */
    }
    //manda alla pagina Login, il nome dell'activity di Login va cambiata secondo le  Naming Convention
    private void toLoginClicked(){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    //fa la registrazione 
    private void onRegisterClicked (String nome, String cognome, /* String dataNascita,*/ String indirizzo, String email, String password){
    }
    
    



}