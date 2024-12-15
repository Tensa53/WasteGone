package it.giga.wastegone.gestioneProfiloUtente.application.exception;

public class LoginCampiException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message Il messaggio di errore.
     */
    public LoginCampiException(String message) {
        super(message);
    }
}