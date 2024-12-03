package it.giga.wastegone.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.CampiException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;
import it.giga.wastegone.gestioneProfiloUtente.application.exception.RegistrazioneException;

/**
 * Classe di utilità per la gestione dei form di login e registrazione.
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

        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches())
            throw new LoginException("L'indirizzo email deve essere in un formato valido");

        if (password.length() == 0)
            throw new LoginException("Il campo password è obbligatorio e non può essere vuoto");

        if (password.length() > 14)
            throw new LoginException("La password non deve superare i 14 caratteri");

        Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
        Matcher matcher1 = pattern1.matcher(password);

        if (!matcher1.matches())
            throw new LoginException("La password deve essere lunga almeno 8 caratteri, con almeno una lettera maiuscola, un numero e un carattere speciale");
    }

    /**
     * Controlla che la password rispetti i requisiti di lunghezza e formato.
     *
     * @param email L'email da controllare.
     * @param password La password da controllare.
     * @param confermaPassword La password ripetuta per conferma.
     * @throws RegistrazioneException Se uno dei controlli fallisce.
     */

public void controllaRegistrazione(String email, String password, String confermaPassword) throws RegistrazioneException {
    if (email.length() == 0)
        throw new RegistrazioneException("Il campo email è obbligatorio e non può essere vuoto");

    if (email.length() > 40)
        throw new RegistrazioneException("L'indirizzo email deve contenre al massimo 40 caratteri");

    Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
    Matcher matcher = pattern.matcher(email);

    if (!matcher.matches())
        throw new RegistrazioneException("L'indirizzo email deve essere in un formato valido");

    if (password.length() == 0)
        throw new RegistrazioneException("Il campo password è obbligatorio e non può essere vuoto");

    if (password.length() > 14)
        throw new RegistrazioneException("La password non deve superare i 14 caratteri");

    if (confermaPassword != null){
        if (password.compareTo(confermaPassword) != 0)
            throw new RegistrazioneException("etPassword_Le password non corrispondono");
    }

    Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
    Matcher matcher1 = pattern1.matcher(password);

    if (!matcher1.matches())
        throw new RegistrazioneException("La password deve essere lunga almeno 8 caratteri, con almeno una lettera maiuscola, un numero e un carattere speciale");
    }

    /**
     * Controlla che i restanti valori inseriti nei campi del form di registrazione siano validi
     *
     * @param nome, il nome dell'utente
     * @param cognome, il cognome dell'utente
     * @param indirizzo, l'indirizzo dell'utente
     * @throws CampiException
     */
    public void controllaAltriCampi(String nome,String cognome, String indirizzo) throws CampiException {
        if (nome.length() == 0)
            throw new CampiException("Inserire un nome.");

        if (nome.length() > 20)
            throw new CampiException("Il nome non deve superare i 20 caratteri.");

        if (cognome.length() == 0)
            throw new CampiException("Inserire un cognome.");

        if (cognome.length() > 20)
            throw new CampiException("Il cognome non deve superare i 20 caratteri.");

        if (indirizzo.length() == 0)
            throw new CampiException("Inserire un indirizzo.");

        if (indirizzo.length() > 20)
            throw new CampiException("L'indirizzo non deve superare 20 caratteri.");

    }
}

