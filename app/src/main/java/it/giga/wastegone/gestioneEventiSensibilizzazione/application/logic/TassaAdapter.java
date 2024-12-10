package it.giga.wastegone.gestioneEventiSensibilizzazione.application.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.PagamentoTassaActivity;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseTassaDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;

public class TassaAdapter extends RecyclerView.Adapter<TassaAdapter.TassaViewHolder> {

    private List<Tassa> tasseList;
    private Context context;
    private FirebaseTassaDAO dao;

    public TassaAdapter(Context context, List<Tassa> tasseList, FirebaseTassaDAO dao) {
        this.context = context;
        this.tasseList = tasseList;
        this.dao = dao;
    }

    @NonNull
    @Override
    public TassaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tassa, parent, false);
        return new TassaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TassaViewHolder holder, int position) {
        Tassa tassa = tasseList.get(position);
        holder.tvImporto.setText("Importo: €" + tassa.getImporto());
        holder.tvScadenza.setText("Scadenza: " + tassa.getDataScadenza());
        holder.tvStato.setText(tassa.isPagato() ? "Pagata" : "Non pagata");
        holder.tvStato.setTextColor(context.getResources().getColor(tassa.isPagato()
                ? android.R.color.holo_green_dark
                : android.R.color.holo_red_dark));

        if (!tassa.isPagato()) {
            holder.btnPaga.setVisibility(View.VISIBLE);
            holder.btnPaga.setOnClickListener(v -> {
                Intent intent = new Intent(context, PagamentoTassaActivity.class);
                context.startActivity(intent);
            });
        } else {
            holder.btnPaga.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return tasseList.size();
    }

    public void loadTasseFromDatabase(String userID) {
        Log.d("TassaAdapter", "Caricamento dati dal database...");
        dao.doRetrieveAllTasseByUserId(userID)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        tasseList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                Tassa tassa = document.toObject(Tassa.class);
                                tasseList.add(tassa);
                            } catch (Exception e) {
                                Log.e("TassaAdapter", "Errore nel parsing del documento: ", e);
                            }
                        }
                        notifyDataSetChanged();
                        Log.d("TassaAdapter", "Dati caricati con successo.");
                    } else {
                        Log.e("TassaAdapter", "Errore nel recupero dei documenti: ", task.getException());
                    }
                });
    }

    public class TassaViewHolder extends RecyclerView.ViewHolder {
        TextView tvImporto, tvScadenza, tvStato;
        Button btnPaga;

        public TassaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvImporto = itemView.findViewById(R.id.tvImporto);
            tvScadenza = itemView.findViewById(R.id.tvScadenza);
            tvStato = itemView.findViewById(R.id.tvStato);
            btnPaga = itemView.findViewById(R.id.btnPaga);
        }
    }
}