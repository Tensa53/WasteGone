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

import it.giga.wastegone.R;

public class NavbarFragment extends Fragment {

    public NavbarFragment() {
        super(R.layout.navbar); // Usa il layout della navbar
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configura il menù a tendina
        ImageButton dropdownMenu = view.findViewById(R.id.menu_dropdown);
        dropdownMenu.setOnClickListener(anchor -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), anchor);
            popupMenu.getMenuInflater().inflate(R.menu.dropdown_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                // Usa una struttura condizionale per gestire la selezione delle voci
                if (item.getItemId() == R.id.menu_option1) {
                    Toast.makeText(requireContext(), "Opzione 1 selezionata", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_option2) {
                    Intent intent = new Intent(requireContext(), NotificationManagementActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_option3) {
                    Toast.makeText(requireContext(), "Opzione 3 selezionata", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            });
            popupMenu.show();
        });
    }
}