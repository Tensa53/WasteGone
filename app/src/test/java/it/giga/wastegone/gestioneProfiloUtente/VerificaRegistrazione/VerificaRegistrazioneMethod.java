package it.giga.wastegone.gestioneProfiloUtente.VerificaRegistrazione;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.CampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;
import it.giga.wastegone.utils.FormUtils;

public class VerificaRegistrazioneMethod {

  public boolean VerificaRegistrazioneCampi(String email, String password,
                                            String confermaPassword,String nome,
                                            String cognome, String indirizzo) throws RegistrazioneException ,CampiException {

    FormUtils formUtils = new FormUtils();
    try {
      formUtils.controllaRegistrazione(email, password, confermaPassword);
      formUtils.controllaAltriCampi(nome, cognome, indirizzo);
    } catch (RegistrazioneException e) {
      return false;
    }catch (CampiException e) {
      return false;
    }
    return true;
  }

}
