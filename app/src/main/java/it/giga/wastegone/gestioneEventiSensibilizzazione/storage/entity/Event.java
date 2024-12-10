package it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity;


public class Event {
    public enum Stato {IN_CORSO, SOSPESO, TERMINATO, IN_PROGRAMMA};

    private String nome;
    private String luogo;
    private String data;
    private String ora;
    private String informazioni;
    private Stato stato;
    private String programmazione;
    private String nomiAddetti;


    public Event() {

    }

    public Event(String nome, String luogo, String data, String ora, String informazioni,
                 Stato stato, String programmazione, String nomiAddetti) {
        this.nome = nome;
        this.luogo = luogo;
        this.data = data;
        this.ora = ora;
        this.informazioni = informazioni;
        this.stato = stato;
        this.programmazione = programmazione;
        this.nomiAddetti = nomiAddetti;
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

    public String getProgrammazione() {
        return programmazione;
    }

    public void setProgrammazione(String programmazione) {
        this.programmazione = programmazione;
    }

    public String getNomiAddetti() {
        return nomiAddetti;
    }

    public void setNomiAddetti(String nomiAddetti) {
        this.nomiAddetti = nomiAddetti;
    }


    @Override
    public String toString() {
        return "Evento{\n" +
                "nome = " + nome + "\n" +
                "luogo = " + luogo + "\n" +
                "Data = " + data + "\n" +
                "Ora = " + ora + "\n" +
                "Info = " + informazioni + "\n" +
                "Stato = " + stato + "\n" +
                "Programmazione = " + programmazione + "\n" +
                "Addetti = " + nomiAddetti + "\n" +
                "}";
    }
}
