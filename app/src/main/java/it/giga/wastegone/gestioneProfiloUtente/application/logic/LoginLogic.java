package it.giga.wastegone.gestioneProfiloUtente.application.logic;

import java.util.HashMap;
import java.util.Map;

public class LoginLogic {

    private Map<String, String> database = new HashMap<>();
        public boolean isEmailInDatabase(String email) {
            return database.containsKey(email);
        }



}

