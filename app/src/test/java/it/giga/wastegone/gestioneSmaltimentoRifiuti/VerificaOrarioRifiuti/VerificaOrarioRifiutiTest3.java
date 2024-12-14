package it.giga.wastegone.gestioneSmaltimentoRifiuti.VerificaOrarioRifiuti;

import static org.junit.Assert.assertFalse;



import org.junit.Test;



public class VerificaOrarioRifiutiTest3 {



  @Test

  public void testOrarioRfiuti() {

    Integer hour = 04;

    Integer minute = 0000;

    boolean isChecked = true;




    VerificaOrarioRifiutiMethod verificaOrarioRifiutiMethod = new VerificaOrarioRifiutiMethod();



    assertFalse(verificaOrarioRifiutiMethod.VerificaOrarioRifiuti(hour, minute, isChecked));

  }



}
