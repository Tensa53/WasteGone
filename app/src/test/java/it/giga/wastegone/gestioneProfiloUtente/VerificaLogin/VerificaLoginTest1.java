package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginCampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import it.giga.wastegone.utils.FormUtils;

public class VerificaLoginTest1 {

  @Test
  public void testEmailNonValida() throws LoginCampiException {
    String email = "wg?gmail.com";
    String password = "Password123!";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}