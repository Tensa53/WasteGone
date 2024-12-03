package it.giga.wastegone.gestioneProfiloUtente.application.exception;

public class LoginException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public LoginException() {
        super("Inserire il campo nel formato corretto !!!");
    }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public LoginException(String message) {
        super(message);
    }
}



