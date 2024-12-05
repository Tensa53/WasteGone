package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;

import com.google.firebase.Timestamp;

public class Event {
    public enum Stato {IN_CORSO, SOSPESO, TERMINATO, IN_PROGRAMMA};

    private String nome;
    private String luogo;
    private Timestamp data;
    private String informazioni;
    private Stato stato;


    public Event() {

    }

    public Event(String nome, String luogo, Timestamp data, String informazioni,
                 Stato stato) {
        this.nome = nome;
        this.luogo = luogo;
        this.data = data;
        this.informazioni = informazioni;
        this.stato = stato;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
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

    public String getInformazioni() {
        return informazioni;
    }

    public void setInformazioni(String informazioni) {
        this.informazioni = informazioni;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }


    @Override
    public String toString() {
        return "Evento{\n" +
                "nome = " + nome + "\n" +
                "luogo = " + luogo + "\n" +
                "Data = " + data + "\n" +
                "Info = " + informazioni + "\n" +
                "Stato = " + stato + "\n" +
                "}";
    }
}
