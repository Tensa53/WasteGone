package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;

/**
 * Questa classe rappresenta un oggetto per l'interazione con i dati degli eventi di
 * sensibilizzazione memorizzati nel database Firebase Firestore.
 */
public class Event {
    /**
     * Questo enum rappresenta lo stato di un evento di sensibilizzazione.
     */
    public enum Stato {
        /**
         * L'evento è in corso.
         */
        IN_CORSO,
        /**
         * L'evento è sospeso.
         */
        SOSPESO,
        /**
         * L'evento è terminato.
         */
        TERMINATO,
        /**
         * L'evento è in programmazione.
         */
        IN_PROGRAMMA
    }

    /**
     * Il nome dell'evento.
     */
    private String nome;
    /**
     * La posizione dell'evento.
     */
    private String luogo;
    /**
     * La data dell'evento.
     */
    private String data;
    /**
     * L'ora dell'evento.
     */
    private String ora;
    /**
     * Lo stato dell'evento.
     */
    private Stato stato;
    /**
     * La programmazione dell'evento.
     */
    private String programmazione;
    /**
     * I nomi degli addetti all'evento.
     */
    private String nomiAddetti;


    /**
     * Costruttore vuoto.
     */
    public Event() {

    }

    /**
     * Costruttore con argomenti.
     *
     * @param nome,           il nome dell'evento
     * @param luogo,          la posizione dell'evento
     * @param data,           la data dell'evento
     * @param ora,            l'ora dell'evento
     * @param stato,          lo stato dell'evento
     * @param programmazione, la programmazione dell'evento
     * @param nomiAddetti,    i nomi degli addetti all'evento
     */
    public Event(String nome, String luogo, String data, String ora,
                 Stato stato, String programmazione, String nomiAddetti) {
        this.nome = nome;
        this.luogo = luogo;
        this.data = data;
        this.ora = ora;
        this.stato = stato;
        this.programmazione = programmazione;
        this.nomiAddetti = nomiAddetti;
    }


    /**
     * Ritorna il nome dell'evento.
     *
     * @return String, il nome dell'evento
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'evento.
     *
     * @param nome, il nome dell'evento
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ritorna la posizione dell'evento.
     *
     * @return String, la posizione dell'evento
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Ritorna la posizione dell'evento.
     *
     * @return String, la posizione dell'evento
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Imposta la posizione dell'evento.
     *
     * @param luogo, la posizione dell'evento
     */
    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    /**
     * Ritorna la data dell'evento.
     *
     * @return String, la data dell'evento
     */
    public String getData() {
        return data;
    }

    /**
     * Imposta la data dell'evento.
     *
     * @param data, la data dell'evento
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Ritorna l'ora dell'evento.
     *
     * @return String, l'ora dell'evento
     */
    public String getOra() {
        return ora;
    }

    /**
     * Imposta l'ora dell'evento.
     *
     * @param ora, l'ora dell'evento
     */
    public void setOra(String ora){
        this.ora = ora;
    }

    /**
     * Ritorna lo stato dell'evento.
     *
     * @return Stato, lo stato dell'evento
     */
    public Stato getStato() {
        return stato;
    }

    /**
     * Imposta lo stato dell'evento.
     *
     * @param stato, lo stato dell'evento
     */
    public void setStato(Stato stato) {
        this.stato = stato;
    }

    /**
     * Ritorna la programmazione dell'evento.
     *
     * @return String, la programmazione dell'evento
     */
    public String getProgrammazione() {
        return programmazione;
    }

    /**
     * Imposta la programmazione dell'evento.
     *
     * @param programmazione, la programmazione dell'evento
     */
    public void setProgrammazione(String programmazione) {
        this.programmazione = programmazione;
    }

    /**
     * Ritorna i nomi degli addetti all'evento.
     *
     * @return String, i nomi degli addetti all'evento
     */
    public String getNomiAddetti() {
        return nomiAddetti;
    }

    /**
     * Imposta i nomi degli addetti all'evento.
     *
     * @param nomiAddetti, i nomi degli addetti all'evento
     */
    public void setNomiAddetti(String nomiAddetti) {
        this.nomiAddetti = nomiAddetti;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto.
     *
     * @return String, la rappresentazione testuale dell'oggetto
     */
    @Override
    public String toString() {
        return "Evento{\n" +
                "nome = " + nome + "\n" +
                "luogo = " + luogo + "\n" +
                "Data = " + data + "\n" +
                "Ora = " + ora + "\n" +
                "Stato = " + stato + "\n" +
                "Programmazione = " + programmazione + "\n" +
                "Addetti = " + nomiAddetti + "\n" +
                "}";
    }

