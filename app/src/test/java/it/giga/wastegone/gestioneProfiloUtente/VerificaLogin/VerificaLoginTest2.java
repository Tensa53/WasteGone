package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;

public class VerificaLoginTest2 {

  @Test
  public void testEmailLunghezzaMaggioreDelLimite() throws LoginException {
    String email = "emailmolto.lunga.che.supera.il.limite.consentito@example.com";
    String password = "Password123!";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}
