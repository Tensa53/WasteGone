package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaPrenotazione;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaPrenotazioneTest5 {

  @Test
  public void testPrenotazione() {
    String userId = "UserID";
    String descrizione = "TV danneggiata";
    String data = "06/01/2025";
    String ora = "22:00";

    VerificaPrenotazioneMethod verificaPrenotazioneMethod = new VerificaPrenotazioneMethod();

    assertFalse(verificaPrenotazioneMethod.verificaPrenotazione(userId, descrizione, data, ora));
  }
}
