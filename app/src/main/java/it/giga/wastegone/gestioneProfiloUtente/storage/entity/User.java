package it.giga.wastegone.gestioneProfiloUtente.storage.entity;

import java.util.Date;

/**
 * Questa classe rappresenta un utente che utilizza l'applicazione.
 * Un utente è caratterizzato da un nome. cognome, email, data di nascita e indirizzo.
 */
public class User {
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;


    /**
     * Costruttore vuoto
     */
    public User() {

    }

    /**
     * Costruttore con nome, cognome, email, dataNascita e indirizzo.
     *
     * @param nome, nome dell'utente.
     * @param cognome, cognome  dell'utente.
     * @param email, email  dell'utente.
     * @param indirizzo, indirizzo  dell'utente.
     */
    public User(String nome, String cognome, String email, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.indirizzo = indirizzo;
    }


    /**
     * Restituisce il nome dell'utente.
     *
     * @return String, una stringa che rappresenta il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome, il nome che si vuole impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return String, una stringa che rappresenta il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome, il cognome che si vuole impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'email dell'utente.
     *
     * @return String, una stringa che rappresenta l'email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'utente.
     *
     * @param email, l'email che si vuole impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Restituisce l'indirizzo dell'utente.
     *
     * @return String, una stringa che rappresenta l'indirizzo dell'utente.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo dell'utente.
     *
     * @param indirizzo, l'indirizzo che si vuole impostare.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce la stringa che mostra tutti i dati dell'istanza della classe Utente.
     *
     * @return String, la stringa con tutti i dati.
     */
    @Override
    public String toString() {
        return "Utente{\n" +
                "nome = " + nome + "\n" +
                "cognome = " + cognome + "\n" +
                "email = " + email + "\n" +
                "indirizzo = " + indirizzo + "\n" +
                "}";
    }
}
