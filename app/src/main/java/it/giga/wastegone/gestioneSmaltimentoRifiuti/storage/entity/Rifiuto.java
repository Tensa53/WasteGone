package it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity;

import java.time.DayOfWeek;

/**
 * Questa classe rappresenta un tipo di rifiuto con informazioni relative alla sua categoria,
 * il giorno e l'orario di conferimento, le istruzioni per il conferimento
 * e il colore associato al rifiuto.
 */
public class Rifiuto {
  /**
   * Le possibili categorie di rifiuti.
   */
  public enum Categoria {
    /**
     * Categoria per il rifiuto plastica.
     */
    PLASTICA,

    /**
     * Categoria per il rifiuto carta.
     */
    CARTA,

    /**
     * Categoria per il rifiuto indifferenziato.
     */
    INDIFFERENZIATA,

    /**
     * Categoria per il rifiuto umido.
     */
    UMIDO,

    /**
     * Categoria per il rifiuto alluminio.
     */
    ALLUMINIO,

    /**
     * Categoria per il rifiuto vetro.
     */
    VETRO,

    /**
     * Categoria per il rifiuto tessili.
     */
    TESSILI
  }

  /**
   * La categoria del rifiuto.
   */
  private Categoria categoria;

  /**
   * Il giorno della settimana in cui il rifiuto deve essere conferito.
   */
  private DayOfWeek giornoConferimento;

  /**
   * L'orario di conferimento del rifiuto.
   */
  private String orarioConferimento;

  /**
   * Le istruzioni specifiche per il conferimento del rifiuto.
   */
  private String istruzioni;

  /**
   * Il codice del colore associato al rifiuto.
   */
  private String codiceColore;

  /**
   * Il colore associato al rifiuto.
   */
  private String colore;


  /**
   * Costruttore vuoto.
   */
  public Rifiuto() {

  }


  /**
   * Costruttore con tutti i parametri necessari per creare un'istanza di Rifiuto.
   *
   * @param categoria , categoria del rifiuto.
   * @param istruzioni , istruzioni per il conferimento del rifiuto.
   * @param giornoConferimento . giorno della settimana per il conferimento del rifiuto.
   * @param orarioConferimento , orario di conferimento del rifiuto.
   * @param colore , colore associato al rifiuto.
   * @param codiceColore , codice del colore associato al rifiuto.
   */
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

  /**
   * Restituisce le istruzioni per il conferimento del rifiuto.
   *
   * @return String , le istruzioni per il conferimento del rifiuto.
   */
  public String getIstruzioni() {
    return istruzioni;
  }

  /**
   * Imposta le istruzioni per il conferimento del rifiuto.
   *
   * @param istruzioni , le istruzioni da impostare.
   */
  public void setIstruzioni(String istruzioni) {
    this.istruzioni = istruzioni;
  }

  /**
   * Restituisce la categoria del rifiuto.
   *
   * @return Categoria , la categoria del rifiuto.
   */
  public Categoria getCategoria() {
    return categoria;
  }

  /**
   * Imposta la categoria del rifiuto.
   *
   * @param categoria , la categoria da impostare.
   */
  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  /**
   * Restituisce il giorno della settimana in cui il rifiuto deve essere conferito.
   *
   * @return DayOfWeek , il giorno del conferimento.
   */
  public DayOfWeek getGiornoConferimento() {
    return giornoConferimento;
  }

  /**
   * Imposta il giorno della settimana in cui il rifiuto deve essere conferito.
   *
   * @param giornoConferimento , il giorno da impostare per il conferimento.
   */
  public void setGiornoConferimento(DayOfWeek giornoConferimento) {
    this.giornoConferimento = giornoConferimento;
  }

  /**
   * Restituisce l'orario di conferimento del rifiuto.
   *
   * @return String , l'orario di conferimento.
   */
  public String getOrarioConferimento() {
    return orarioConferimento;
  }

  /**
   * Imposta l'orario di conferimento del rifiuto.
   *
   * @param orarioConferimento , l'orario da impostare.
   */
  public void setOrarioConferimento(String orarioConferimento) {
    this.orarioConferimento = orarioConferimento;
  }

  /**
   * Restituisce il colore associato al rifiuto.
   *
   * @return String , il colore del rifiuto.
   */
  public String getColore() {
    return colore;
  }

  /**
   * Imposta il colore associato al rifiuto.
   *
   * @param colore , il colore da impostare.
   */
  public void setColore(String colore) {
    this.colore = colore;
  }

  /**
   * Restituisce il codice del colore associato al rifiuto.
   *
   * @return String , il codice del colore.
   */
  public String getCodiceColore() {
    return codiceColore;
  }

  /**
   * Imposta il codice del colore associato al rifiuto.
   *
   * @param codiceColore , il codice colore da impostare.
   */
  public void setCodiceColore(String codiceColore) {
    this.codiceColore = codiceColore;
  }

  /**
   * Restituisce la stringa che mostra tutti i dati dell'istanza della classe Rifiuto.
   *
   * @return String , la stringa con tutti i dati.
   */
  @Override
  public String toString() {
    return "Rifiuto{"
            + "Categoria=" + categoria
            + ", Istruzioni='" + istruzioni + '\''
            + ", Giorno conferimento=" + (giornoConferimento != null
            ? giornoConferimento.toString() : "null")
            + ", Orario conferimento='" + orarioConferimento + '\''
            + ", Colore='" + colore + '\''
            + ", CodiceColore='" + codiceColore + '\''
            + '}';
  }
}
