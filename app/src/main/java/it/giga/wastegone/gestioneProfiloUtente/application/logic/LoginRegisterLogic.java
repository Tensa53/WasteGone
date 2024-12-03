package it.giga.wastegone.gestioneProfiloUtente.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import it.giga.wastegone.gestioneProfiloUtente.storage.dataAccess.FirebaseUserDAO;
import it.giga.wastegone.gestioneProfiloUtente.storage.entity.User;

public class LoginRegisterLogic {
    private FirebaseAuth auth;
    private FirebaseUserDAO userDAO;

    /**
     * Costruttore di RegisterLogic.
     * Inizializza le istanze di FirebaseAuth e FirebaseUserDAO.
     */
    public LoginRegisterLogic() {
        auth = FirebaseAuth.getInstance();
        userDAO = new FirebaseUserDAO();
    }

    /**
     * Crea un utente nuovo con l'email e la password fornite.
     *
     * @param email l'email dell'utente
     * @param password la password dell'utente
     * @return un Task che rappresenta l'operazione asincrona di creazione dell'utente
     */
    public Task<AuthResult> createUser(String email, String password) {
        return auth.createUserWithEmailAndPassword(email, password);
    }

    /**
     * Salva l'utente nel database.
     *
     * @param user l'utente da salvare
     * @param userId l'id dell'utente
     * @return un Task che rappresenta l'operazione asincrona di salvataggio dell'utente
     */
    public Task<Void> saveUser(User user, String userId) {
        return userDAO.doSaveUser(user, userId);
    }

    public Task<AuthResult> loginUser(String email, String password) {
        return auth.signInWithEmailAndPassword(email, password);
    }
}

