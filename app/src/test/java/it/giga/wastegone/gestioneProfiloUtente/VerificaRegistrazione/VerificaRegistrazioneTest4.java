package it.giga.wastegone.gestioneProfiloUtente.VerificaRegistrazione;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.CampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;

public class VerificaRegistrazioneTest4 {
  @Test
  public void testNomeNonValido() throws RegistrazioneException, CampiException {

    String email="marioros@gmail.com";
    String password="Ciaomondo1!";
    String confermaPassword="Ciaomondo1!";
    String nome="Mario Antonio Francesco";
    String cognome="Rossi";
    String indirizzo="via ciaone 20";

    VerificaRegistrazioneMethod VerificaRegistrazioneMethod = new VerificaRegistrazioneMethod();

    assertFalse(VerificaRegistrazioneMethod.VerificaRegistrazioneCampi(email, password, confermaPassword, nome, cognome, indirizzo));
  }
}