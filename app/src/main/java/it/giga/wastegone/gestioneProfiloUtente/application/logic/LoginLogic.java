package it.giga.wastegone.gestioneProfiloUtente.application.logic;

import java.util.HashMap;
import java.util.Map;

public class LoginLogic {


    private Map<String, String> database = new HashMap<>();

    /**
     * Verifica se l'email fornita è presente nel database.
     *
     * @param email l'email da verificare
     * @return true se l'email è nel database, false altrimenti
     */
        public boolean isEmailInDatabase(String email) {
            return database.containsKey(email);
        }



}

