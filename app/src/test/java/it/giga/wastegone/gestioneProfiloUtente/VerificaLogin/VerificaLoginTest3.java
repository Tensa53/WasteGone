package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import static org.junit.Assert.assertFalse;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import org.junit.Test;

public class VerificaLoginTest3 {

  @Test
  public void testEmailLunghezzaMinoreDelLimite() throws LoginException {
    String email = "a@b.c";
    String password = "Password123!";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}
