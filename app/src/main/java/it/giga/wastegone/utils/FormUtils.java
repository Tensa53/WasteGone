package it.giga.wastegone.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.giga.wastegone.gestioneProfiloUtente.application.exception.LoginException;

/**
 * Classe di utilità per la gestione dei form di login e registrazione.
 */
public class FormUtils {

    /**
     * Controlla che l'email e la password rispettino i requisiti di lunghezza e formato.
     *
     * @param email L'email da controllare.
     * @param password La password da controllare.
     * @param ripetiPassword La password ripetuta per conferma.
     * @throws LoginException Se uno dei controlli fallisce.
     */
    public void controllaEmailEPassword(String email, String password, String ripetiPassword) throws LoginException {
        if (email.length() == 0)
            throw new LoginException("Il campo email non può essere vuoto");

        if (email.length() > 40)
            throw new LoginException("L'indirizzo email non deve superare i 40 caratteri");

        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches())
            throw new LoginException("L'indirizzo email deve essere in un formato valido");

        if (password.length() == 0)
            throw new LoginException("Il campo password non può essere vuoto");

        if (password.length() > 14)
            throw new LoginException("La password non deve superare i 14 caratteri");

        if (ripetiPassword != null) {
            if (!password.equals(ripetiPassword))
                throw new LoginException("Le password non corrispondono");
        }

        Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
        Matcher matcher1 = pattern1.matcher(password);

        if (!matcher1.matches())
            throw new LoginException("La password deve essere lunga almeno 8 caratteri, con almeno una lettera maiuscola, un numero e un carattere speciale");
    }
}