package it.giga.wastegone.gestioneProfiloUtente.storage.entity;

import java.util.Date;

public class User
{
    private String nome;
    private String cognome;
    private String email;
    private Date dataNascita;
    private String indirizzo;


    /**
     * Costruttore vuoto
     */
    public User()
    {

    }

    /**
     * Costruttore con nome, cognome, email, dataNascita e indirizzo.
     *
     * @param nome, nome dell'utente.
     * @param cognome, cognome  dell'utente.
     * @param email, email  dell'utente.
     * @param dataNascita, data di nascita  dell'utente.
     * @param indirizzo, indirizzo  dell'utente.
     */
    public User(String nome, String cognome, String email, Date dataNascita, String indirizzo)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
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
     * Restituisce la data di nascita dell'utente.
     *
     * @return Date, rappresenta la data di nascita dell'utente.
     */
    public Date getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita dell'utente.
     *
     * @param dataNascita, la data di nascita che si vuole impostare.
     */
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
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
}
