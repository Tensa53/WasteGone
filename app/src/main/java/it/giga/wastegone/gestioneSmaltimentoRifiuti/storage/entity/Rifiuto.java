package it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;


public class Rifiuto {
    public enum Tipo {SPECIALE, URBANO};
    public enum Categoria {RIFIUTI_ORGANICI, CARTA_CARTONE, PLASTICA, VETRO, INDIFFERENZIATI};

    //TESSILE, TONER,LEGNO, METALLO, IMBALLAGGI_COMPOSITI, MULTIMATERIALE
    //INGOMBRANTI, VERNICI, DETERGENTI, ALTRI_RIFIUTI,

    //Attributi comuni
    private Tipo tipo;
    private String nome;
    private String istruzioni;
    private String descrizione;

    //Attributi rifiuti normali
    private Categoria categoria;
    private DayOfWeek giornoConferimento;
    private int orarioConferimento;
    private String colore;

    //Attributi rifiuti speciali
    private String materiale;
    private int dimensioni;


    public Rifiuto() {

    }

    public Rifiuto(Tipo tipo, String nome, String istruzioni, String descrizione) {
        this.tipo = tipo;
        this.nome = nome;
        this.istruzioni = istruzioni;
        this.descrizione = descrizione;
    }

    public Rifiuto(Tipo tipo, String nome, String istruzioni, String descrizione, Categoria categoria,
                   DayOfWeek giornoConferimento, int orarioConferimento, String colore) {
        this.tipo = tipo;
        this.istruzioni = istruzioni;
        this.descrizione = descrizione;

        this.categoria = categoria;
        this.giornoConferimento = giornoConferimento;
        this.orarioConferimento = orarioConferimento;
        this.colore = colore;
    }

    public Rifiuto(Tipo tipo, String nome, String istruzioni, String descrizione,
                   String materiale, int dimensioni) {
        this.tipo = tipo;
        this.istruzioni = istruzioni;
        this.descrizione = descrizione;

        this.materiale = materiale;
        this.dimensioni = dimensioni;
    }


    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getOrarioConferimento() {
        return orarioConferimento;
    }

    public void setOrarioConferimento(int orarioConferimento) {
        this.orarioConferimento = orarioConferimento;
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

    /**
     * Restituisce una descrizione testuale associata a una determinata categoria di rifiuti.
     *
     * @param categoria categoria del rifiuto per la quale si desidera ottenere una descrizione.
     * @return String stringa contenente la descrizione della categoria di rifiuto.
     */
    public static String getDescrizioneByCategoria(Categoria categoria) {
        switch (categoria) {
            case RIFIUTI_ORGANICI:
                return "Materiali biodegradabili derivanti da alimenti, " +
                    "scarti di cucina e rifiuti vegetali che possono essere compostati.";
            case CARTA_CARTONE:
                return "Carta e cartone usati, come giornali, scatole e imballaggi, " +
                        "che possono essere riciclati per produrre nuova carta.";
            case PLASTICA:
                return "Oggetti e imballaggi in plastica, come bottiglie, " +
                        "contenitori e sacchetti, che possono essere riutilizzati e riciclati.";
            /*case LEGNO:
                return " Rifiuti di legno, come mobili, pallet e imballaggi, " +
                        "che possono essere riutilizzati o riciclati " +
                        "per produrre nuovi prodotti in legno.";
            case METALLO:
                return "Rifiuti metallici, tra cui lattine, scatole e oggetti " +
                        "in acciaio o alluminio, che possono essere riciclati e riutilizzati.";
            case IMBALLAGGI_COMPOSITI:
                return "Imballaggi costituiti da più materiali, come tetrapak, " +
                        "che non possono essere facilmente separati per il riciclo.";
            case MULTIMATERIALE:
                return "Rifiuti che combinano vari materiali, come imballaggi misti, " +
                        "che richiedono un trattamento specifico per il riciclo.";*/
            case VETRO:
                return "Bottiglie, contenitori e altri oggetti in vetro che possono " +
                        "essere riutilizzati o riciclati senza perdere qualità.";
            /*case TESSILE:
                return "Rifiuti derivanti da abbigliamento, tessuti e altri materiali " +
                        "simili che possono essere riutilizzati o riciclati in nuovi prodotti.";
            case TONER:
                return "Cartucce e toner usati, spesso provenienti da stampanti, " +
                        "che possono essere riciclati o rigenerati.";
            case INGOMBRANTI:
                return "Oggetti voluminosi come mobili, elettrodomestici e materassi " +
                        "che non possono essere smaltiti tramite i normali canali di raccolta.";
            case VERNICI:
                return "Rifiuti di vernici, solventi e smalti, " +
                        "che richiedono un trattamento speciale per evitarne " +
                        "la contaminazione ambientale.";
            case DETERGENTI:
                return "Bottiglie vuote di detergenti e prodotti per la pulizia " +
                        "che possono essere riciclati se correttamente separati.";
            case ALTRI_RIFIUTI:
                return "Rifiuti che non rientrano in altre categorie.";*/
            case INDIFFERENZIATI:
                return "Rifiuti che non possono essere riciclati e " +
                        "devono essere smaltiti in discarica o inceneriti.";
            default:
                return "Categoria rifiuto non valida";
        }
    }

    /**
     * Restituisce i giorni della settimana in cui i rifiuti di una
     * determinata categoria devono essere conferiti.
     * Se una categoria ha più giorni di conferimento, restituisce una lista con più giorni.
     *
     * @param categoria, categoria del rifiuto.
     * @return List<DayOfWeek> lista di giorni della settimana in cui il rifiuto
     * della categoria deve essere conferito.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<DayOfWeek> getDayOfWeekByCategoria(Categoria categoria){
        switch (categoria) {
            case RIFIUTI_ORGANICI:
                return Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
            case CARTA_CARTONE:
                return Arrays.asList(DayOfWeek.WEDNESDAY);
            case PLASTICA:
                return Arrays.asList(DayOfWeek.FRIDAY);
            case VETRO:
                return Arrays.asList(DayOfWeek.WEDNESDAY);
            case INDIFFERENZIATI:
                return Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
            default:
                return Arrays.asList(DayOfWeek.MONDAY);
        }
    }

    @Override
    public String toString() {
        switch (tipo) {
            case URBANO: return "Rifiuto (Normale){\n" +
                    "Nome = " + nome + "\n" +
                    "Istruzioni = " + istruzioni + "\n" +
                    "Descrizione = " + descrizione + "\n" +
                    "Categoria = " + categoria + "\n" +
                    "Giorno conferimento = " + giornoConferimento.toString() + "\n" +
                    "Orario conferimento = " + orarioConferimento + "\n" +
                    "Colore = " + colore + "\n" +
                    "}";
            case SPECIALE: return "Rifiuto (Normale){\n" +
                    "Nome = " + nome + "\n" +
                    "Istruzioni = " + istruzioni + "\n" +
                    "Descrizione = " + descrizione + "\n" +
                    "Materiale = " + materiale + "\n" +
                    "Dimensioni = " + dimensioni + "\n" +
                    "}";
            default: return "Rifiuto non valido";
        }
    }
}
