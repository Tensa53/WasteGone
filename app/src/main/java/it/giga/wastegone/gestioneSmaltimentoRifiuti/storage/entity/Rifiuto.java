package it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity;

import java.time.DayOfWeek;

public class Rifiuto {
    public enum Categoria {SPECIALE, NORMALE};

    //Attributi comuni
    private Categoria categoria;
    private String istruzioni;
    private String descrizione;

    //Attributi rifiuti normali
    private DayOfWeek giornoconferimento;
    private int orarioconferimento;
    private String colore;

    //Attributi rifiuti speciali
    private String materiale;
    private int dimensioni;


    public Rifiuto() {

    }

    public Rifiuto(Categoria categoria, String istruzioni, String descrizione) {
        this.categoria = categoria;
        this.istruzioni = istruzioni;
        this.descrizione = descrizione;
    }

    public Rifiuto(Categoria categoria, String istruzioni, String descrizione,
                   DayOfWeek giornoconferimento, int orarioconferimento, String colore) {
        this.categoria = categoria;
        this.istruzioni = istruzioni;
        this.descrizione = descrizione;

        this.giornoconferimento = giornoconferimento;
        this.orarioconferimento = orarioconferimento;
        this.colore = colore;
    }

    public Rifiuto(Categoria categoria, String istruzioni, String descrizione,
                   String materiale, int dimensioni) {
        this.categoria = categoria;
        this.istruzioni = istruzioni;
        this.descrizione = descrizione;

        this.materiale = materiale;
        this.dimensioni = dimensioni;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getIstruzioni() {
        return istruzioni;
    }

    public void setIstruzioni(String istruzioni) {
        this.istruzioni = istruzioni;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public DayOfWeek getGiornoconferimento() {
        return giornoconferimento;
    }

    public void setGiornoconferimento(DayOfWeek giornoconferimento) {
        this.giornoconferimento = giornoconferimento;
    }

    public int getOrarioconferimento() {
        return orarioconferimento;
    }

    public void setOrarioconferimento(int orarioconferimento) {
        this.orarioconferimento = orarioconferimento;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getMateriale() {
        return materiale;
    }

    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }

    public int getDimensioni() {
        return dimensioni;
    }

    public void setDimensioni(int dimensioni) {
        this.dimensioni = dimensioni;
    }


    @Override
    public String toString() {
        switch (categoria) {
            case NORMALE: return "Rifiuto (Normale){\n" +
                    "Istruzioni = " + istruzioni + "\n" +
                    "Descrizione = " + descrizione + "\n" +
                    "Giorno conferimento = " + giornoconferimento.toString() + "\n" +
                    "Orario conferimento = " + orarioconferimento + "\n" +
                    "Colore = " + colore + "\n" +
                    "}";
            case SPECIALE: return "Rifiuto (Normale){\n" +
                    "Istruzioni = " + istruzioni + "\n" +
                    "Descrizione = " + descrizione + "\n" +
                    "Materiale = " + materiale + "\n" +
                    "Dimensioni = " + dimensioni + "\n" +
                    "}";
            default: return "Rifiuto non valido";
        }
    }
}
