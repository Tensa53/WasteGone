package it.giga.wastegone.gestioneSmaltimentoRifiuti.VerificaOrarioRifiuti;

import static org.junit.Assert.assertFalse;



import org.junit.Test;



public class VerificaOrarioRifiutiTest2 {



  @Test

  public void testOrarioRfiuti() {

    Integer hour = 4;

    Integer minute = 000;

    boolean isChecked = true;




    VerificaOrarioRifiutiMethod verificaOrarioRifiutiMethod = new VerificaOrarioRifiutiMethod();



    assertFalse(verificaOrarioRifiutiMethod.VerificaOrarioRifiuti(hour, minute, isChecked));

  }



}
