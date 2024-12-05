package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;

import java.sql.Timestamp;

public class Prenotazione {
    private String userID;
    private String descrizione;
    private Timestamp data;
    private int ora;


    public Prenotazione(String userID, String descrizione, Timestamp data, int ora) {
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

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }


    @Override
    public String toString() {
        return "Prenotazione{\n" +
                "Descrizione = " + descrizione + "\n" +
                "Data = " + data + "\n" +
                "Ora: " + ora + "\n" +
                "}";
    }
}
