package it.giga.wastegone.gestioneEventiSensibilizzazione.VerificaTassa;


import it.giga.wastegone.utils.FormUtils;

public class VerificaTassaMethod {

    public boolean verificaTassa(String nome, String cognome, String numeroCarta, String scadenza, String cvv) {
        FormUtils formUtils = new FormUtils();

        try {
            formUtils.controllaPagamento(nome, cognome, numeroCarta, scadenza, cvv);
        } catch (FormUtils.PagamentoException e) {
            return false;
        }

        return true;
    }

}