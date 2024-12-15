package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginCampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;

public class VerificaLoginTest6 {

  @Test
  public void testPasswordLunghezzaOltreIlLimite() throws LoginCampiException {
    String email = "test@example.com";
    String password = "lapasswordèpiùlungadellimite";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}