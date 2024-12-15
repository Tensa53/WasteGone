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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.CampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;
import it.giga.wastegone.gestioneProfiloUtente.application.logic.LoginRegisterLogic;
import it.giga.wastegone.gestioneProfiloUtente.storage.entity.User;
import it.giga.wastegone.utils.FormUtils;

/**
 * La classe {@code RegisterActivity} gestisce la registrazione di nuovi utenti
 * nell'applicazione WasteGone. Consente di inserire i dati richiesti,
 * validarli e salvare le informazioni nel database.
 */
public class RegisterActivity extends AppCompatActivity {

  /**
   * Campo di input per il nome dell'utente.
   */
  private EditText etNome;

  /**
   * Campo di input per il cognome dell'utente.
   */
  private EditText etCognome;

  /**
   * Campo di input per l'email dell'utente.
   */
  private EditText etEmail;

  /**
   * Campo di input per l'indirizzo dell'utente.
   */
  private EditText etIndirizzo;

  /**
   * Campo di input per la password dell'utente.
   */
  private EditText etPassword;

  /**
   * Campo di input per confermare la password dell'utente.
   */
  private EditText etConfermaPassword;

  /**
   * Pulsante per completare la registrazione.
   */
  private Button btnRegistrati;

  /**
   * Testo che consente di accedere alla schermata di login.
   */
  private TextView tvLogin;

  /**
   * Logica utilizzata per eseguire le operazioni di registrazione.
   */
  private LoginRegisterLogic loginRegisterLogic;

  /**
   * Indica il risultato dell'operazione di registrazione.
   * {@code true} se la registrazione è avvenuta con successo, {@code false} altrimenti.
   */
  private Boolean result;

  /**
   * Metodo chiamato alla creazione dell'attività.
   *
   * @param savedInstanceState stato precedente dell'attività, se esistente.
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

    if (loginRegisterLogic == null) {
      loginRegisterLogic = new LoginRegisterLogic();
    }

    btnRegistrati.setOnClickListener(v -> {
      String nome = etNome.getText().toString();
      String cognome = etCognome.getText().toString();
      String indirizzo = etIndirizzo.getText().toString();
      String email = etEmail.getText().toString();
      String password = etPassword.getText().toString();
      String confermaPassword = etConfermaPassword.getText().toString();

      if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || email.isEmpty()
              || password.isEmpty() || confermaPassword.isEmpty()) {
        Toast.makeText(RegisterActivity.this, "Compila tutti i campi!", Toast.LENGTH_SHORT).show();
      } else {
        onRegisterClicked(nome, cognome, indirizzo, email, password, confermaPassword);
      }
    });

    tvLogin.setOnClickListener(v -> toLoginClicked());
  }

  /**
   * Metodo usato per aprire la schermata di login.
   */
  private void toLoginClicked() {
    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
    startActivity(intent);
  }

  /**
   * Metodo per gestire il click sul pulsante di registrazione. Valida i dati
   * inseriti e tenta di creare un nuovo utente.
   *
   * @param nome              nome dell'utente.
   * @param cognome           cognome dell'utente.
   * @param indirizzo         indirizzo dell'utente.
   * @param email             email dell'utente.
   * @param password          password scelta dall'utente.
   * @param confermaPassword  conferma della password.
   */
  public void onRegisterClicked(String nome, String cognome, String indirizzo, String email,
                                String password, String confermaPassword) {
    try {
      FormUtils formUtils = new FormUtils();
      formUtils.controllaRegistrazione(email, password, confermaPassword);
      formUtils.controllaAltriCampi(nome, cognome, indirizzo);

      loginRegisterLogic.createUser(email, password).addOnCompleteListener(new
                                                           OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if (task.isSuccessful()) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            User user = new User(nome, cognome, email, indirizzo);

            loginRegisterLogic.saveUser(user, userId).addOnCompleteListener(saveTask -> {
              if (saveTask.isSuccessful()) {
                Toast.makeText(RegisterActivity.this,
                        "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                result = true;
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
              } else {
                Toast.makeText(RegisterActivity.this,
                        "Errore nel salvataggio dei dati utente", Toast.LENGTH_SHORT).show();
                result = false;
              }
            });
          } else {
            Exception exception = task.getException();
            if (exception instanceof FirebaseAuthUserCollisionException) {
              etEmail.setError("Indirizzo email già in uso");
            } else {
              Toast.makeText(RegisterActivity.this,
                      "Errore durante la registrazione", Toast.LENGTH_SHORT).show();
            }
            result = false;
          }
        }
      });
    } catch (RegistrazioneException | CampiException e) {
      Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * Metodo per impostare la logica di registrazione, utile per test strumentali.
   *
   * @param loginRegisterLogic logica di registrazione da utilizzare.
   */
  public void setLoginRegisterLogic(LoginRegisterLogic loginRegisterLogic) {
    this.loginRegisterLogic = loginRegisterLogic;
  }

  /**
   * Metodo per ottenere il risultato dell'operazione di registrazione.
   *
   * @return {@code true} se la registrazione è avvenuta con successo, {@code false} altrimenti.
   */
  public Boolean getResult() {
    return result;
  }
}
