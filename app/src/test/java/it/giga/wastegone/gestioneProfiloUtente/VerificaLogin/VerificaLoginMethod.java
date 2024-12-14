package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import it.giga.wastegone.utils.FormUtils;

public class VerificaLoginMethod {

  public boolean verificaLogin(String email, String password) throws LoginException {
    FormUtils formUtils = new FormUtils();


    try {
      formUtils.controllaLogin(email, password);
    } catch (FormUtils.LoginCampiException e) {
      return false;
    }

    return true;
  }
}