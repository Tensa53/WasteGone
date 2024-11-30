package it.giga.wastegone.gestioneProfiloUtente.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import it.giga.wastegone.gestioneProfiloUtente.storage.dataAccess.FirebaseUserDAO;
import it.giga.wastegone.gestioneProfiloUtente.storage.entity.User;

public class RegisterLogic {
    private FirebaseAuth auth;
    private FirebaseUserDAO userDAO;

    /**
     * Costruttore di RegisterLogic.
     * Inizializza le istanze di FirebaseAuth e FirebaseUserDAO.
     */
    public RegisterLogic() {
        auth = FirebaseAuth.getInstance();
        userDAO = new FirebaseUserDAO();
    }

    /**
     * Crea un nuovo utente con l'email e la password fornite.
     *
     * @param email l'email dell'utente
     * @param password la password dell'utente
     * @param repeatPassword la password ripetuta per conferma
     * @return un Task che rappresenta l'operazione asincrona di creazione dell'utente
     * @throws IllegalArgumentException se le password non corrispondono
     */
    public Task<AuthResult> createUser(String email, String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Le password non corrispondono");
        }

        return auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String userId = auth.getCurrentUser().getUid();
                String nome = "", cognome = "", indirizzo = "";
                User user = new User(nome, cognome, email, indirizzo);
                userDAO.doSaveUser(user, userId);
            }
        });
    }
}