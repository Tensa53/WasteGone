package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import it.giga.wastegone.MainActivity;
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
import com.google.firebase.auth.AuthResult;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneProfiloUtente.application.logic.LoginRegisterLogic;


/**
 * Classe che permette all'utente di loggarsi nell'applicazione.
 */
public class LoginActivity extends AppCompatActivity {
  private EditText etMail;
  private EditText etPassword;
  private Button btnLogin;
  private TextView tvRegistrati;

  /**
   * Metodo chiamato alla creazione dell'activity.
   *
   * @param savedInstanceState stato precedentemente salvato dell'activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_login);

    etMail = findViewById(R.id.textemail);
    etPassword = findViewById(R.id.textpassword);
    btnLogin = findViewById(R.id.loginbutton);
    tvRegistrati = findViewById(R.id.registrati);

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    // Quando viene premuto il bottone accedi vengono inviate le informazioni del form al metodo
    // che permette il login
    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = etMail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
          Toast.makeText(LoginActivity.this, "Compila entrambi i campi!",
                  Toast.LENGTH_SHORT).show();
        } else {
          // Metodo da implementare
          onLoginClicked(email, password);
        }
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

  /**
   * Metodo usato per aprire la schermata di registrazione.
   */
  private void ontvRegistratiClicked() {
    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
    startActivity(intent);
  }

  /**
   * Metodo usato per effettuare il login.
   *
   * @param email    l'email inserita dall'utente
   * @param password la password inserita dall'utente
   */
  private void onLoginClicked(String email, String password) {
    LoginRegisterLogic loginRegisterLogic = new LoginRegisterLogic();
    loginRegisterLogic.loginUser(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                  Toast.makeText(LoginActivity.this,
                          "Login avvenuto con successo", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                  startActivity(intent);



                } else {
                  Toast.makeText(LoginActivity.this,
                          "Errore nel login dell' utente", Toast.LENGTH_SHORT).show();
                }
              }
            });
  }
}