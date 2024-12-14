package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;

public class VerificaLoginTest4 {

  @Test
  public void testPasswordNonValida() throws LoginException {
    String email = "test@example.com";
    String password = "wastegone1!";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}