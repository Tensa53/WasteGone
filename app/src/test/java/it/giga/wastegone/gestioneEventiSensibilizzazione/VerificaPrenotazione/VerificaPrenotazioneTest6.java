package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaPrenotazione;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaPrenotazioneTest6 {

  @Test
  public void testPrenotazione() {
    String userId = "UserID";
    String descrizione = "TV danneggiata";
    String data = "06/01/2025";
    String ora = "12:00";

    VerificaPrenotazioneMethod verificaPrenotazioneMethod = new VerificaPrenotazioneMethod();

    assertFalse(verificaPrenotazioneMethod.verificaPrenotazione(userId, descrizione, data, ora));
  }
}
