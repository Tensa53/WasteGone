package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaPrenotazione;

import it.giga.wastegone.utils.FormUtils;

public class VerificaPrenotazioneMethod {

  public boolean verificaPrenotazione(String userId, String descrizione, String data, String ora) {
    FormUtils formUtils = new FormUtils();

    try {
      formUtils.controllaPrenotazione(userId, descrizione, data, ora);
    } catch (FormUtils.PrenotazioneException e) {
      return false;
    }

    return true;
  }
}
