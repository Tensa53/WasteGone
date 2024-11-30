package it.giga.wastegone.gestioneProfiloUtente.application.exception;

public class RegistrazioneException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public RegistrazioneException() {
        super("Inserire il campo nel formato corretto !!!");
    }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public RegistrazioneException(String message) {
        super(message);
    }
}



