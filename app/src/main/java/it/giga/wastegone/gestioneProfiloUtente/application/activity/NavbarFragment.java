package it.giga.wastegone.gestioneProfiloUtente.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import it.giga.wastegone.MainActivity;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.PrenotazioneActivity;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.SezioneEventiActivity;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.TasseActivity;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.application.activity.CalendarioActivity;

/**
 * Fragment per la gestione della navbar.
 */
public class NavbarFragment extends Fragment {

  /**
   * Costruttore del fragment che utilizza il layout della navbar.
   */
  public NavbarFragment() {
    super(R.layout.navbar); // Usa il layout della navbar
  }

  /**
   * Metodo chiamato quando la view del fragment è stata creata.
   *
   * @param view la view del fragment
   * @param savedInstanceState lo stato precedentemente salvato del fragment
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // Configura il menù a tendina
    ImageButton dropdownMenu = view.findViewById(R.id.menu_dropdown);
    dropdownMenu.setOnClickListener(anchor -> {
      PopupMenu popupMenu = new PopupMenu(requireContext(), anchor);
      popupMenu.getMenuInflater().inflate(R.menu.dropdown_menu, popupMenu.getMenu());


      // Ottieni l'utente corrente
      FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
      MenuItem loginItem = popupMenu.getMenu().findItem(R.id.menu_option7);
      MenuItem logoutItem = popupMenu.getMenu().findItem(R.id.menu_option9);
      MenuItem tasseItem = popupMenu.getMenu().findItem(R.id.menu_option4);
      MenuItem prenotazioneItem = popupMenu.getMenu().findItem(R.id.menu_option3);
      MenuItem registerItem = popupMenu.getMenu().findItem(R.id.menu_option8);
      MenuItem notificationItem = popupMenu.getMenu().findItem(R.id.menu_option2);
      MenuItem calendarioItem = popupMenu.getMenu().findItem(R.id.menu_option6);

      if (currentUser != null) {
        // User loggato
        calendarioItem.setVisible(true);
        notificationItem.setVisible(true);
        registerItem.setVisible(false);
        prenotazioneItem.setVisible(true);
        tasseItem.setVisible(true);
        loginItem.setVisible(false);
        logoutItem.setVisible(true);
      } else {
        // User non loggato
        calendarioItem.setVisible(false);
        notificationItem.setVisible(false);
        registerItem.setVisible(true);
        prenotazioneItem.setVisible(false);
        tasseItem.setVisible(false);
        loginItem.setVisible(true);
        logoutItem.setVisible(false);
      }

      // Usa una struttura condizionale per gestire la selezione delle voci
      popupMenu.setOnMenuItemClickListener(item -> {
        if (item.getItemId() == R.id.menu_option1) {
          Intent intent = new Intent(requireContext(), MainActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option2) {
          Intent intent = new Intent(requireContext(), NotificationManagementActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option3) {
          Intent intent = new Intent(requireContext(), PrenotazioneActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option4) {
          Intent intent = new Intent(requireContext(), TasseActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option5) {
          Intent intent = new Intent(requireContext(), SezioneEventiActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option6) {
          Intent intent = new Intent(requireContext(), CalendarioActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option7) {
          Intent intent = new Intent(requireContext(), LoginActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option8) {
          Intent intent = new Intent(requireContext(), RegisterActivity.class);
          startActivity(intent);
          return true;
        } else if (item.getItemId() == R.id.menu_option9) {
          FirebaseAuth.getInstance().signOut();
          Intent intent = new Intent(requireContext(), MainActivity.class);
          startActivity(intent);
          requireActivity().finish();
          Toast.makeText(requireContext(), "Logout effettuato", Toast.LENGTH_SHORT).show();
          return true;
        } else {
          return false;
        }
      });
      popupMenu.show();
    });
  }
}