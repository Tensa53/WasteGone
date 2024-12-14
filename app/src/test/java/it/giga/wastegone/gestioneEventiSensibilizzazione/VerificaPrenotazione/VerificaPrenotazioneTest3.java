package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaPrenotazione;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaPrenotazioneTest3 {

  @Test
  public void testPrenotazione() {
    String userId = "UserID";
    String descrizione = "TV danneggiata";
    String data = "03/12/2024";
    String ora = "10:00";

    VerificaPrenotazioneMethod verificaPrenotazioneMethod = new VerificaPrenotazioneMethod();

    assertFalse(verificaPrenotazioneMethod.verificaPrenotazione(userId, descrizione, data, ora));
  }
}
