package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;


public class Tassa {
    private String userID;
    private float importo;
    private String dataScadenza;
    private boolean isPagato;


    public Tassa() {

    }

    public Tassa(String userID, float importo, String dataScadenza, boolean isPagato) {
        this.userID = userID;
        this.importo = importo;
        this.dataScadenza = dataScadenza;
        this.isPagato = isPagato;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getImporto() {
        return importo;
    }

    public void setImporto(float importo) {
        this.importo = importo;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public boolean isPagato() {
        return isPagato;
    }

    public void setPagato(boolean pagato) {
        isPagato = pagato;
    }


    @Override
    public String toString() {
        return "Tassa{\n" +
                "Importo = " + importo + "\n" +
                "Data scadenza = " + dataScadenza + "\n" +
                "Pagato: " + isPagato + "\n" +
                "}";
    }
}
