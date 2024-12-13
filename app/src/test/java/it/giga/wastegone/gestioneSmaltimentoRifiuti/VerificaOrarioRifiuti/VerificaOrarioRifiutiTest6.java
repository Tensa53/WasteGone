package it.giga.wastegone.gestioneSmaltimentoRifiuti.VerificaOrarioRifiuti;

import static org.junit.Assert.assertFalse;



import org.junit.Test;



public class VerificaOrarioRifiutiTest6 {



  @Test

  public void testOrarioRifiuti() {
    Integer hour = 07;
    Integer minute = 00;
    boolean isChecked = false;




    VerificaOrarioRifiutiMethod verificaOrarioRifiutiMethod = new VerificaOrarioRifiutiMethod();



    assertFalse(verificaOrarioRifiutiMethod.VerificaOrarioRifiuti(hour, minute, isChecked));

  }



}
