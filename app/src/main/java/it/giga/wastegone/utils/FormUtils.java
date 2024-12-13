package it.giga.wastegone.utils;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.CampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe di utilità per la gestione dei form di login, registrazione e pagamento.
 */
public class FormUtils {

    /**
     * Controlla che l'email rispetti i requisiti di lunghezza e formato.
     *
     * @param email L'email da controllare.
     * @param password La password da controllare.
     * @throws LoginException Se uno dei controlli fallisce.
     */
    public void controllaLogin(String email, String password) throws LoginException {
        if (email.length() == 0)
            throw new LoginException("Il campo email è obbligatorio e non può essere vuoto");

        if (email.length() > 40)
            throw new LoginException("L'indirizzo email deve contenre al massimo 40 caratteri");

        if(email.length()<6)
            throw new LoginException("L'indirizzo email deve contenere almeno 6 caratteri");


        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches())
            throw new LoginException("L'indirizzo email deve essere in un formato valido");

        if (password.length() == 0)
            throw new LoginException("Il campo password è obbligatorio e non può essere vuoto");

        if (password.length() > 14)
            throw new LoginException("La password non deve superare i 14 caratteri");

        if (password.length() < 8)
            throw new LoginException("La password deve essere lunga almeno 8 caratteri");

        Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
        Matcher matcher1 = pattern1.matcher(password);

    if (!matcher1.matches()) {
      throw new LoginException("La password deve essere in un formato valido");
    }
  }

  /**
   * Controlla che la password rispetti i requisiti di lunghezza e formato.
   *
   * @param email L'email da controllare.
   * @param password La password da controllare.
   * @param confermaPassword La password ripetuta per conferma.
   * @throws RegistrazioneException Se uno dei controlli fallisce.
   */

  public void controllaRegistrazione(String email, String password, String confermaPassword)
          throws RegistrazioneException {
    if (email.length() == 0) {
      throw new RegistrazioneException("Il campo email è obbligatorio e non può essere vuoto");
    }
    if (email.length() > 40) {
      throw new RegistrazioneException("L'indirizzo email deve contenre al massimo 40 caratteri");
    }
    Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
    Matcher matcher = pattern.matcher(email);

    if (!matcher.matches()) {
      throw new RegistrazioneException("L'indirizzo email deve essere in un formato valido");
    }
    if (password.length() == 0) {
      throw new RegistrazioneException("Il campo password è obbligatorio e non può essere vuoto");
    }
    if (password.length() > 14) {
      throw new RegistrazioneException("La password non deve superare i 14 caratteri");
    }
    if (confermaPassword != null) {
      if (password.compareTo(confermaPassword) != 0) {
        throw new RegistrazioneException("etPassword_Le password non corrispondono");
      }
    }

    Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
    Matcher matcher1 = pattern1.matcher(password);

    if (!matcher1.matches()) {
      throw new RegistrazioneException("La password deve essere lunga almeno 8 caratteri, "
              + "con almeno una lettera maiuscola, un numero e un carattere speciale");
    }
  }

  /**
   * Controlla che i restanti valori inseriti nei campi del form di registrazione siano validi.
   *
   * @param nome il nome dell'utente.
   * @param cognome il cognome dell'utente.
   * @param indirizzo l'indirizzo dell'utente.
   * @throws CampiException se uno dei controlli fallisce.
   */
  public void controllaAltriCampi(String nome, String cognome, String indirizzo)
            throws CampiException {
    if (nome.length() == 0) {
      throw new CampiException("Inserire un nome.");
    }
    if (nome.length() > 20) {
      throw new CampiException("Il nome non deve superare i 20 caratteri.");
    }
    if (cognome.length() == 0) {
      throw new CampiException("Inserire un cognome.");
    }
    if (cognome.length() > 20) {
      throw new CampiException("Il cognome non deve superare i 20 caratteri.");
    }
    if (indirizzo.length() == 0) {
      throw new CampiException("Inserire un indirizzo.");
    }
    if (indirizzo.length() > 20) {
      throw new CampiException("L'indirizzo non deve superare 50 caratteri.");
    }
  }
  /**
   * Controlla che i dati della carta di credito siano validi.
   *
   * @param nome Il nome del titolare della carta.
   * @param cognome Il cognome del titolare della carta.
   * @param numeroCarta Il numero della carta di credito.
   * @param dataScadenza La data di scadenza della carta (MM/YY).
   * @param cvv Il codice CVV della carta.
   * @throws PagamentoException Se uno dei controlli fallisce.
   */

  public void controllaPagamento(String nome, String cognome, String numeroCarta,
                                   String dataScadenza, String cvv)
            throws PagamentoException {
    if (nome.length() < 2 || nome.length() > 20) {
      throw new PagamentoException("Il campo nome è obbligatorio e non può essere vuoto");
    }
    if (cognome.length() == 2 || cognome.length() > 20) {
      throw new PagamentoException("Il campo cognome è obbligatorio e non può essere vuoto");
    }
    if (!isValidCardNumber(numeroCarta)) {
      throw new PagamentoException("Numero di carta non valido");
    }
    if (!isValidExpiryDate(dataScadenza)) {
      throw new PagamentoException("Data di scadenza non valida");
    }
    if (!isValidCvv(cvv)) {
      throw new PagamentoException("CVV non valido");
    }
  }

  // Metodo per verificare la validità del numero della carta
  private boolean isValidCardNumber(String cardNumber) {
    return cardNumber.length() == 16 && cardNumber.matches("\\d+");
  }

  // Metodo per verificare la validità della data di scadenza
  private boolean isValidExpiryDate(String expiryDate) {
    // Controllo del formato MM/YY
    if (!Pattern.matches("(0[1-9]|1[0-2])/\\d{2}", expiryDate)) {
      return false;
    }

    // Parsing della data di scadenza
    String[] parts = expiryDate.split("/");
    int month = Integer.parseInt(parts[0]);
    int year = Integer.parseInt(parts[1]) + 2000; // Aggiunta del prefisso "20" all'anno

    // Ottieni il mese e l'anno corrente
    Calendar current = Calendar.getInstance();
    int currentYear = current.get(Calendar.YEAR);
    int currentMonth = current.get(Calendar.MONTH) + 1; // I mesi in Calendar partono da 0

    // Verifica se la data di scadenza è valida e successiva a quella corrente
    if (year > currentYear || (year == currentYear && month >= currentMonth)) {
      return true;
    }
    return false;
  }

  // Metodo per verificare la validità del Cvv
  private boolean isValidCvv(String cvv) {
    return cvv.length() == 3 && cvv.matches("\\d+");
  }
  /**
   * Eccezione lanciata quando i dati del pagamento non sono validi.
   */

  public class PagamentoException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message Il messaggio di errore.
     */
    public PagamentoException(String message) {
      super(message);
    }
  }
  /**
   * Controlla che l'orario sia valido.
   *
   * @param hour L'ora da controllare.
   * @param minute I minuti da controllare.
   * @throws OrarioException Se l'orario non è valido.
   */

  public void controllaOrario(int hour, int minute, boolean isChecked) throws OrarioException {
    if (isChecked == false) {
      throw new OrarioException("Seleziona l'orario per la notifica");
    }
    if (hour < 0 || hour > 23) {
      throw new OrarioException("L'ora deve essere compresa tra 0 e 23.");
    }
    if (minute < 0 || minute > 59) {
      throw new OrarioException("I minuti devono essere compresi tra 0 e 59.");
    }
    if (hour < 5 || hour > 18) {
      throw new OrarioException("L'orario deve essere compreso tra le 05:00 e le 18:00.");
    }
  }

  /**
   * Eccezione lanciata quando l'orario non è valido.
   */
  public class OrarioException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message Il messaggio di errore.
     */
    public OrarioException(String message) {
      super(message);
    }
  }
  /**
   * Controlla che i campi della prenotazione siano validi.
   *
   * @param userId      l'ID dell'utente che effettua la prenotazione.
   * @param description la descrizione dell'evento.
   * @param date        la data dell'evento.
   * @param hour        l'ora dell'evento.
   * @throws PrenotazioneException se uno dei controlli fallisce.
   */

  public void controllaPrenotazione(String userId, String description, String date, String hour)
          throws PrenotazioneException {
    if (userId == null || userId.isEmpty()) {
      throw new PrenotazioneException("Il campo userId è obbligatorio e non può essere vuoto");
    }
    if (description.length() <= 5 || description.length() >= 72) {
      throw new PrenotazioneException("La descrizione deve essere lunga almeno 5 caratteri e non "
              + "superare i 72 caratteri");
    }

    if (description == null || description.isEmpty()) {
      throw new PrenotazioneException("Il campo descrizione è obbligatorio e non può essere vuoto");
    }

    if (date == null || date.isEmpty()) {
      throw new PrenotazioneException("Il campo data è obbligatorio e non può essere vuoto");
    }

    if (hour == null || hour.isEmpty()) {
      throw new PrenotazioneException("Il campo ora è obbligatorio e non può essere vuoto");
    }

    // Controllo formato data (dd/MM/yyyy)
    if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
      throw new PrenotazioneException("La data deve essere nel formato dd/MM/yyyy");
    }
    // Controllo che la data sia maggiore della data corrente
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    try {
      Date prenotazioneDate = sdf.parse(date);
      Date currentDate = new Date();
      if (prenotazioneDate.before(currentDate)) {
        throw new PrenotazioneException("La data della prenotazione deve essere successiva alla "
                + "data corrente");
      }
    } catch (ParseException e) {
      throw new PrenotazioneException("Formato data non valido");
    }

    // Controllo formato ora (HH:mm)
    if (!hour.matches("\\d{2}:\\d{2}")) {
      throw new PrenotazioneException("L'ora deve essere nel formato HH:mm");
    }
    try {
      SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
      Date prenotazioneHour = sdfHour.parse(hour);
      Date startHour = sdfHour.parse("10:00");
      Date endHour = sdfHour.parse("18:00");
      if (prenotazioneHour.before(startHour) || prenotazioneHour.after(endHour)) {
        throw new PrenotazioneException("L'ora della prenotazione deve essere compresa tra le "
                + "10:00 e le 18:00");
      }
    } catch (ParseException e) {
      throw new PrenotazioneException("Formato ora non valido");
    }
  }

  /**
   * Eccezione lanciata quando i dati della prenotazione non sono validi.
   */
  public class PrenotazioneException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message Il messaggio di errore.
     */
    public PrenotazioneException(String message) {
      super(message);
    }
  }

}
