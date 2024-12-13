package it.giga.wastegone.gestioneSmaltimentoRifiuti.VerificaOrarioRifiuti;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;



public class VerificaOrarioRifiutiTest6 {



  @Test

  public void testOrarioRifiuti() {
    Integer hour = 07;
    Integer minute = 00;
    boolean isChecked = true;




    VerificaOrarioRifiutiMethod verificaOrarioRifiutiMethod = new VerificaOrarioRifiutiMethod();



    assertTrue(verificaOrarioRifiutiMethod.VerificaOrarioRifiuti(hour, minute, isChecked));

  }



}
