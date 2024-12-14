package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaTassa;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaTassaTest8 {

    @Test
    public void testTassa() {
        String nome = "G";
        String cognome= "Verdi";
        String numeroCarta = "1111-2222-3333-4444";
        String scadenza = "04/20";
        String cvv = "123";

        VerificaTassaMethod verificaTassaMethod = new VerificaTassaMethod();

        assertFalse(verificaTassaMethod.verificaTassa(nome,cognome,numeroCarta,scadenza,cvv));
    }

}
