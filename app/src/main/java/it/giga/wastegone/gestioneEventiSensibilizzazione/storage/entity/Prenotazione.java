package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;

/**
 * Questa classe rappresenta una prenotazione.
 * Questa classe e' caratterizzata dall'ID dell'utente che ha effettuato la prenotazione,
 * la descrizione della prenotazione, la data della prenotazione e l'ora della prenotazione.
 *
 */
public class Prenotazione {

    /**
     * L'ID dell'utente che ha effettuato la prenotazione.
     */
    private String userID;

    /**
     * La descrizione della prenotazione.
     */
    private String descrizione;

    /**
     * La data della prenotazione.
     */
    private String data;

    /**
     * L'ora della prenotazione.
     */
    private String ora;


    /**
     * Costruttore vuoto.
     */
    public Prenotazione() {

    }

    /**
     * Costruttore con parametri.
     *
     * @param userID,      l'ID dell'utente che ha effettuato la prenotazione
     * @param descrizione, la descrizione della prenotazione
     * @param data,        la data della prenotazione
     * @param ora,         l'ora della prenotazione
     */
    public Prenotazione(String userID, String descrizione, String data, String ora) {
        this.userID = userID;
        this.descrizione = descrizione;
        this.data = data;
        this.ora = ora;
    }


    /**
     * Ritorna l'ID dell'utente che ha effettuato la prenotazione.
     *
     * @return String, l'ID dell'utente
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Imposta l'ID dell'utente che ha effettuato la prenotazione.
     *
     * @param userID, l'ID dell'utente
     */
    public void setUserID(String userID){
        this.userID = userID;
    }

    /**
     * Ritorna la descrizione della prenotazione.
     *
     * @return String, la descrizione della prenotazione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione della prenotazione.
     *
     * @param descrizione, la descrizione della prenotazione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Ritorna la data della prenotazione.
     *
     * @return String, la data della prenotazione
     */
    public String getData() {
        return data;
    }

    /**
     * Imposta la data della prenotazione.
     *
     * @param data, la data della prenotazione
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Ritorna l'ora della prenotazione.
     *
     * @return String, l'ora della prenotazione
     */
    public String getOra() {
        return ora;
    }

    /**
     * Imposta l'ora della prenotazione.
     *
     * @param ora, l'ora della prenotazione
     */
    public void setOra(String ora){
        this.ora = ora;
    }


    /**
     * Genera una rappresentazione testuale dell'oggetto.
     *
     * @return String, la rappresentazione testuale dell'oggetto
     */
    @Override
    public String toString() {
        return "Prenotazione{\n" +
                "Descrizione = " + descrizione + "\n" +
                "Data = " + data + "\n" +
                "Ora = " + ora + "\n" +
                "}";
    }
}
