package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaTassa;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaTassaTest13 {

    @Test
    public void testTassa() {
        String nome = "Giovanni";
        String cognome= "Verdi";
        String numeroCarta = "1111222233334444";
        String scadenza = "04/25";
        String cvv = "12A";

        VerificaTassaMethod verificaTassaMethod = new VerificaTassaMethod();

        assertFalse(verificaTassaMethod.verificaTassa(nome,cognome,numeroCarta,scadenza,cvv));
    }

}
