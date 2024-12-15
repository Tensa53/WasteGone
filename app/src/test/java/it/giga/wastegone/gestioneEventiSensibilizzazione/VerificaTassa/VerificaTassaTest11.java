package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaTassa;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaTassaTest11 {

    @Test
    public void testTassa() {
        String nome = "Giovanni";
        String cognome= "Verdi";
        String numeroCarta = "1111222233334444";
        String scadenza = "04/25";
        String cvv = "1234";

        VerificaTassaMethod verificaTassaMethod = new VerificaTassaMethod();

        assertFalse(verificaTassaMethod.verificaTassa(nome,cognome,numeroCarta,scadenza,cvv));
    }

}
