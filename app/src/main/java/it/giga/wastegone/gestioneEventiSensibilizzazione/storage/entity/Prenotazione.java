package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;

public class Prenotazione {
    private String userID;
    private String descrizione;
    private String data;
    private String ora;


    public Prenotazione() {

    }

    public Prenotazione(String userID, String descrizione, String data, String ora) {
        this.userID = userID;
        this.descrizione = descrizione;
        this.data = data;
        this.ora = ora;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora){
        this.ora = ora;
    }


    @Override
    public String toString() {
        return "Prenotazione{\n" +
                "Descrizione = " + descrizione + "\n" +
                "Data = " + data + "\n" +
                "Ora = " + ora + "\n" +
                "}";
    }
}