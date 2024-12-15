package it.giga.wastegone.gestioneProfiloUtente.VerificaLogin;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginCampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import it.giga.wastegone.utils.FormUtils;

public class VerificaLoginMethod {

  public boolean verificaLogin(String email, String password) throws LoginCampiException {
    FormUtils formUtils = new FormUtils();


    try {
      formUtils.controllaLogin(email, password);
    } catch (LoginCampiException e) {
      return false;
    }

    return true;
  }
}