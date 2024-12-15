package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;


import org.junit.Test;
import static org.junit.Assert.assertFalse;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginCampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;

public class VerificaLoginTest5 {

  @Test
  public void testPasswordLunghezzaMinoreDelLimite() throws LoginCampiException {
    String email = "test@example.com";
    String password = "corta";

    VerificaLoginMethod verificaLoginMethod = new VerificaLoginMethod();

    assertFalse(verificaLoginMethod.verificaLogin(email, password));
  }
}