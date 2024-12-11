package it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.dataAccess.FirebaseRifiutoDAO;


public class Rifiuto {
    public enum Categoria {PLASTICA, CARTA, INDIFFERENZIATA, UMIDO, ALLUMINIO, VETRO, TESSILI};

    private Categoria categoria;
    private DayOfWeek giornoConferimento;
    private String orarioConferimento;
    private String istruzioni;
    private String codiceColore;
    private String colore;


    public Rifiuto() {

    }

    public Rifiuto(Categoria categoria, String istruzioni,
                   DayOfWeek giornoConferimento, String orarioConferimento,
                   String colore, String codiceColore) {
        this.categoria = categoria;
        this.istruzioni = istruzioni;
        this.giornoConferimento = giornoConferimento;
        this.orarioConferimento = orarioConferimento;
        this.colore = colore;
        this.codiceColore = codiceColore;
    }


    public String getIstruzioni() {
        return istruzioni;
    }

    public void setIstruzioni(String istruzioni) {
        this.istruzioni = istruzioni;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public DayOfWeek getGiornoConferimento() {
        return giornoConferimento;
    }

    public void setGiornoConferimento(DayOfWeek giornoConferimento) {
        this.giornoConferimento = giornoConferimento;
    }

    public String getOrarioConferimento() {
        return orarioConferimento;
    }

    public void setOrarioConferimento(String orarioConferimento) {
        this.orarioConferimento = orarioConferimento;
    }


    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getCodiceColore() {
        return codiceColore;
    }

    public void setCodiceColoreColore(String codiceColore) {
        this.codiceColore = codiceColore;
    }


    @Override
    public String toString() {
        return "Rifiuto{\n" +
                "Categoria = " + categoria + "\n" +
                "Istruzioni = " + istruzioni + "\n" +
                "Giorno conferimento = " + giornoConferimento.toString() + "\n" +
                "Orario conferimento = " + orarioConferimento + "\n" +
                "Colore = " + colore + "\n" +
                "#Colore = " + codiceColore + "\n" +
                "}";
        }
    }
