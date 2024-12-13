package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;
import it.giga.wastegone.gestioneProfiloUtente.application.logic.LoginRegisterLogic;
import it.giga.wastegone.gestioneProfiloUtente.storage.entity.User;
import it.giga.wastegone.utils.FormUtils;

/**
 * Activity per la registrazione di un nuovo utente.
 */
public class RegisterActivity extends AppCompatActivity {

  private EditText etNome, etCognome, etEmail, etIndirizzo, etPassword, etConfermaPassword;
  private Button btnRegistrati;
  private TextView tvLogin;
  private LoginRegisterLogic loginRegisterLogic;

  /**
   * Metodo chiamato alla creazione dell'activity.
   *
   * @param savedInstanceState stato precedentemente salvato dell'activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FirebaseApp.initializeApp(this);

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

        if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || email.isEmpty()
                || password.isEmpty() || confermaPassword.isEmpty()) {
          Toast.makeText(RegisterActivity.this, "Compila tutti i campi!",
                  Toast.LENGTH_SHORT).show();
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
  private void onRegisterClicked(String nome, String cognome, String indirizzo, String email,
                                 String password, String confermaPassword) {
    try {
      FormUtils formUtils = new FormUtils();
      formUtils.controllaRegistrazione(email, password, confermaPassword);

      LoginRegisterLogic loginRegisterLogic = new LoginRegisterLogic();

      loginRegisterLogic.createUser(email, password).addOnCompleteListener(new
                OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if (task.isSuccessful()) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            User user = new User(nome, cognome, email, indirizzo);
            loginRegisterLogic.saveUser(user, userId).addOnCompleteListener(new
                OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                  Toast.makeText(RegisterActivity.this,
                          "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(RegisterActivity.this,
                          LoginActivity.class);
                  startActivity(intent);
                } else {
                  Toast.makeText(RegisterActivity.this,
                          "Errore nel salvataggio dei dati utente", Toast.LENGTH_SHORT).show();
                }
              }
            });
          } else {
            etEmail.setError("Indirizzo email già in uso");
          }
        }
      });
    } catch (RegistrazioneException e) {
      Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }
}