package it.giga.wastegone.gestioneProfiloUtente.application.exception;

public class CampiException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public CampiException() {
        super("Inserire il campo nel formato corretto !!!");
    }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public CampiException(String message) {
        super(message);
    }
}



