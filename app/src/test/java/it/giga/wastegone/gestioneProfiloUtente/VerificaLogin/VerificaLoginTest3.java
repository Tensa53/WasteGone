package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import static org.junit.Assert.assertFalse;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginCampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import org.junit.Test;

public class VerificaLoginTest3 {

  @Test
  public void testEmailLunghezzaMinoreDelLimite() throws LoginCampiException {
    String email = "a@b.c";
    String password = "Password123!";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}
