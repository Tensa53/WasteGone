package it.giga.wastegone.gestioneSmaltimentoRifiuti.VerificaOrarioRifiuti;

import it.giga.wastegone.utils.FormUtils;

public class VerificaOrarioRifiutiMethod {

    public boolean VerificaOrarioRifiuti(int hour, int minute, boolean isChecked) {
      FormUtils formUtils = new FormUtils();

      try {
        formUtils.controllaOrario(hour, minute, isChecked);
      } catch ( FormUtils.OrarioException e ) {
        return false;
      }
      return true;
    }

  }

