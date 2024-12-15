package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaTassa;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VerificaTassaTest14 {

    @Test
    public void testTassa() {
        String nome = "Giovanni";
        String cognome= "Verdi";
        String numeroCarta = "1111222233334444";
        String scadenza = "04/25";
        String cvv = "123";

        VerificaTassaMethod verificaTassaMethod = new VerificaTassaMethod();

        assertTrue(verificaTassaMethod.verificaTassa(nome,cognome,numeroCarta,scadenza,cvv));
    }

}