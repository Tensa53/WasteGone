package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;


/**
 * Classe per l'interazione con i dati delle tasse.
 * Questa classe rappresenta una tassa.
 * Una tassa è caratterizzata dall'ID dell'utente che ha effettuato la prenotazione,
 * l'importo della tassa, la data di scadenza della tassa e se la tassa è stata pagata o meno.
 * 
 */
public class Tassa {

    /**
     * L'identificatore univoco del documento.
     */
    private String documentId;

    /**
     * L'identificatore dell'utente.
     */
    private String userID;

    /**
     * L'importo della tassa.
     */
    private float importo;

    /**
     * La data di scadenza della tassa.
     */
    private String dataScadenza;

    /**
     * Indica se la tassa è stata pagata o meno.
     */
    private boolean isPagato;

    /**
     * Costruttore vuoto.
     */
    public Tassa() {

    }

    /**
     * Costruttore con i parametri necessari per creare un'istanza di Tassa.
     *
     * @param userID,       L'identificatore dell'utente.
     * @param importo,      L'importo della tassa.
     * @param dataScadenza, La data di scadenza della tassa.
     * @param isPagato,     Indica se la tassa è stata pagata o meno.
     */
    public Tassa(String userID, float importo, String dataScadenza, boolean isPagato) {
        this.userID = userID;
        this.importo = importo;
        this.dataScadenza = dataScadenza;
        this.isPagato = isPagato;
    }

    /**
     * Restituisce l'identificatore univoco del documento.
     *
     * @return String, L'identificatore univoco del documento.
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * Imposta l'identificatore univoco del documento.
     *
     * @param documentId, L'identificatore univoco del documento.
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * Restituisce l'identificatore dell'utente.
     *
     * @return String, L'identificatore dell'utente.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Imposta l'identificatore dell'utente.
     *
     * @param userID, L'identificatore dell'utente.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Restituisce l'importo della tassa.
     *
     * @return float, L'importo della tassa.
     */
    public float getImporto() {
        return importo;
    }

    /**
     * Imposta l'importo della tassa.
     *
     * @param importo, L'importo della tassa.
     */
    public void setImporto(float importo) {
        this.importo = importo;
    }

    /**
     * Restituisce la data di scadenza della tassa.
     *
     * @return String, La data di scadenza della tassa.
     */
    public String getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta la data di scadenza della tassa.
     *
     * @param dataScadenza, La data di scadenza della tassa.
     */
    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * Indica se la tassa è stata pagata o meno.
     *
     * @return boolean, True se la tassa è stata pagata, false altrimenti.
     */
    public boolean getIsPagato() {
        return isPagato;
    }

    /**
     * Imposta l'indicatore se la tassa è stata pagata o meno.
     *
     * @param isPagato, L'indicatore se la tassa è stata pagata o meno.
     */
    public void setIsPagato(boolean isPagato) {
        this.isPagato = isPagato;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto.
     *
     * @return Rappresentazione testuale dell'oggetto.
     */
    @Override
    public String toString() {
        return "Tassa{" +
                "documentId='" + documentId + '\'' +
                ", userID='" + userID + '\'' +
                ", importo=" + importo +
                ", dataScadenza='" + dataScadenza + '\'' +
                ", isPagato=" + isPagato +
                '}';
    }
}
