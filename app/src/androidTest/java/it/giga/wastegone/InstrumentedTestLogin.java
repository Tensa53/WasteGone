package it.giga.wastegone;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import it.giga.wastegone.gestioneProfiloUtente.application.activity.LoginActivity;
import it.giga.wastegone.gestioneProfiloUtente.application.logic.LoginRegisterLogic;

// InstrumentedTestLogin.java
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestLogin {

  @Mock
  private LoginRegisterLogic mockLoginRegisterLogic; // Mock di LoginRegisterLogic

  @Mock
  private FirebaseAuth mockAuth; // Mock di FirebaseAuth

  @Mock
  private Task<AuthResult> mockTask;  // Mock per Task<AuthResult>

  @Captor
  private ArgumentCaptor<OnCompleteListener<AuthResult>> captorListener; // ArgumentCaptor

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);  // Inizializza i mock

    // Mock l'inizializzazione del comportamento del login
    when(mockAuth.signInWithEmailAndPassword(anyString(), anyString()))
            .thenReturn(mockTask);
  }

  @Test
  public void testOnLoginClicked_Success() {
    // Crea un comportamento mockato del task (completamento riuscito)
    when(mockTask.isSuccessful()).thenReturn(true);

    // Crea un comportamento per simulare la risposta quando loginUser viene chiamato
    when(mockLoginRegisterLogic.loginUser(anyString(), anyString())).thenReturn(mockTask);

    // Utilizza ActivityScenario per avviare l'Activity
    try (ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class)) {
      scenario.onActivity(activity -> {
        // Inietta il mock di LoginRegisterLogic nell'Activity
        activity.setLoginRegisterLogic(mockLoginRegisterLogic);

        // Chiama il metodo della activity che stai testando
        activity.onLoginClicked("wg@gmail.com", "Wastegone1!");

        // Verifica che il listener sia stato chiamato con i giusti parametri
        verify(mockTask).addOnCompleteListener(eq(activity), captorListener.capture());

        // Cattura il listener e invoca manualmente la logica di completamento
        OnCompleteListener<AuthResult> capturedListener = captorListener.getValue();
        assertNotNull(capturedListener);  // Verifica che il listener sia stato catturato

        Task<AuthResult> completedTask = mock(Task.class);
        when(completedTask.isSuccessful()).thenReturn(true);
        capturedListener.onComplete(completedTask);  // Simula la conclusione riuscita del task

        // Verifica che la login sia successiva e che la UI venga aggiornata (come un Toast o un Intent)
        assertTrue(activity.getResult());  // Ad esempio, verifica che il risultato del login sia true
      });
    }
  }

  @Test
  public void testOnLoginClicked_Failure() {
    // Crea un comportamento mockato del task (completamento fallito)
    when(mockTask.isSuccessful()).thenReturn(false);

    // Crea un comportamento per simulare la risposta quando loginUser viene chiamato
    when(mockLoginRegisterLogic.loginUser(anyString(), anyString())).thenReturn(mockTask);

    // Utilizza ActivityScenario per avviare l'Activity
    try (ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class)) {
      scenario.onActivity(activity -> {
        // Inietta il mock di LoginRegisterLogic nell'Activity
        activity.setLoginRegisterLogic(mockLoginRegisterLogic);

        // Chiama il metodo della activity che stai testando
        activity.onLoginClicked("wg@gmail.com", "Wastegone123!");

        // Verifica che il listener sia stato chiamato con i giusti parametri
        verify(mockTask).addOnCompleteListener(eq(activity), captorListener.capture());

        // Cattura il listener e invoca manualmente la logica di completamento
        OnCompleteListener<AuthResult> capturedListener = captorListener.getValue();
        assertNotNull(capturedListener);  // Verifica che il listener sia stato catturato

        Task<AuthResult> completedTask = mock(Task.class);
        when(completedTask.isSuccessful()).thenReturn(false);
        capturedListener.onComplete(completedTask);  // Simula la conclusione fallita del task

        // Verifica che la login sia fallita e che la UI venga aggiornata (come un Toast o un Intent)
        assertFalse(activity.getResult());  // Ad esempio, verifica che il risultato del login sia false
      });
    }
  }
}