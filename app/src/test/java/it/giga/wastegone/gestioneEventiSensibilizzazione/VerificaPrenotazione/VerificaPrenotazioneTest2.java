package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaPrenotazione;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaPrenotazioneTest2 {

  @Test
  public void testPrenotazione() {
    String userId = "UserID";
    String descrizione = "TV danneggiata con lo schermo rotto, il telecomando non funziona "
            + " e il piede del supporto è completamente spezzato.";
    String data = "06/01/2025";
    String ora = "10:00";

    VerificaPrenotazioneMethod verificaPrenotazioneMethod = new VerificaPrenotazioneMethod();

    assertFalse(verificaPrenotazioneMethod.verificaPrenotazione(userId, descrizione, data, ora));
  }
}
