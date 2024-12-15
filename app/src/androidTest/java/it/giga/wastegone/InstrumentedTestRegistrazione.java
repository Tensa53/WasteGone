package it.giga.wastegone;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import android.widget.EditText;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import it.giga.wastegone.gestioneProfiloUtente.application.activity.RegisterActivity;
import it.giga.wastegone.gestioneProfiloUtente.application.logic.LoginRegisterLogic;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTestRegistrazione {

  @Mock
  private LoginRegisterLogic mockLoginRegisterLogic;

  @Mock
  private Task<AuthResult> mockTask;

  @Captor
  private ArgumentCaptor<OnCompleteListener<AuthResult>> captorListener;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    // Configura il comportamento di createUser per restituire mockTask
    when(mockLoginRegisterLogic.createUser(anyString(), anyString())).thenReturn(mockTask);

    // Configura addOnCompleteListener per simulare il completamento del Task
    doAnswer(invocation -> {
      OnCompleteListener<AuthResult> listener = invocation.getArgument(0);
      listener.onComplete(mockTask); // Simula il completamento del task
      return null;
    }).when(mockTask).addOnCompleteListener(any());
  }

  @Test
  public void testOnRegisterClicked_EmailAlreadyExists() throws InterruptedException {
    // Simula che il Task fallisca
    when(mockTask.isSuccessful()).thenReturn(false);

    // Simula che il Task restituisca un'eccezione specifica di Firebase
    when(mockTask.getException()).thenReturn(new FirebaseAuthUserCollisionException(
            "ERROR_EMAIL_ALREADY_IN_USE", "Email già in uso"));

    CountDownLatch latch = new CountDownLatch(1); // Per sincronizzare il callback

    try (ActivityScenario<RegisterActivity> scenario = ActivityScenario.launch(RegisterActivity.class)) {
      scenario.onActivity(activity -> {
        // Inietta il mock di LoginRegisterLogic nell'Activity
        activity.setLoginRegisterLogic(mockLoginRegisterLogic);

        // Chiama il metodo onRegisterClicked
        activity.onRegisterClicked("Waste", "Gone", "Via Ciao 2", "wg@gmail.com", "Wastegone1!", "Wastegone1!");

        // Verifica che createUser sia stato chiamato
        verify(mockLoginRegisterLogic).createUser(eq("wg@gmail.com"), eq("Wastegone1!"));

        // Simula il completamento del task
        latch.countDown();
      });

      // Attendi il completamento del callback
      latch.await(2, TimeUnit.SECONDS);

      scenario.onActivity(activity -> {
        // Verifica che l'errore sia mostrato su etEmail
        EditText etEmail = activity.findViewById(R.id.etEmail);
        assertEquals("Indirizzo email già in uso", etEmail.getError());
      });
    }
  }
}
