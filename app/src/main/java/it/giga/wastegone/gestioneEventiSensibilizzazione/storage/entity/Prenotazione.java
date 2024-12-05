package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;

import com.google.firebase.Timestamp;

public class Prenotazione {
    private String userID;
    private String descrizione;
    private Timestamp data;


    public Prenotazione(String userID, String descrizione, Timestamp data) {
        this.userID = userID;
        this.descrizione = descrizione;
        this.data = data;
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
        return data.toDate().getHours();
    }


    @Override
    public String toString() {
        return "Prenotazione{\n" +
                "Descrizione = " + descrizione + "\n" +
                "Data = " + data + "\n" +
                "}";
    }
}