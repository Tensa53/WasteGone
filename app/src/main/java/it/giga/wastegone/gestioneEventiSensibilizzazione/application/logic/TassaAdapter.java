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
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneEventiSensibilizzazione.application.activity.PagamentoTassaActivity;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.dataAccess.FirebaseTassaDAO;
import it.giga.wastegone.gestioneEventiSensibilizzazione.storage.entity.Tassa;
import java.util.List;



/**
 * Adapter per visualizzare una lista di tasse in un RecyclerView.
 */
public class TassaAdapter extends RecyclerView.Adapter<TassaAdapter.TassaViewHolder> {
  private List<Tassa> tasseList;
  private Context context;
  private FirebaseTassaDAO dao;
  /**
  * Costruttore per TassaAdapter.
  *
  * @param context Il contesto in cui viene utilizzato l'adapter.
  * @param tasseList La lista delle tasse da visualizzare.
  * @param dao L'oggetto di accesso ai dati per le operazioni Firebase.
  */

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
    holder.tvStato.setText(tassa.getIsPagato() ? "Pagata" : "Non pagata");
    holder.tvStato.setTextColor(context.getResources().getColor(tassa.getIsPagato()
            ? android.R.color.holo_green_dark
            : android.R.color.holo_red_dark));

    if (!tassa.getIsPagato()) {
      holder.btnPaga.setVisibility(View.VISIBLE);
      holder.btnPaga.setOnClickListener(v -> {
        Intent intent = new Intent(context, PagamentoTassaActivity.class);
        intent.putExtra("tassaId", tassa.getDocumentId());
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

  /**
     * Carica la lista delle tasse dal database per un utente specifico.
     *
     * @param userId L'ID dell'utente le cui tasse devono essere caricate.
     */
  public void loadTasseFromDatabase(String userId) {
    Log.d("TassaAdapter", "Caricamento dati dal database...");
    dao.doRetrieveAllTasseByUserId(userId)
                .addOnCompleteListener(task -> {
                  if (task.isSuccessful()) {
                    tasseList.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                      try {
                        Tassa tassa = document.toObject(Tassa.class);
                        tassa.setDocumentId(document.getId());
                        tasseList.add(tassa);
                      } catch (Exception e) {
                        Log.e("TassaAdapter", "Errore nel parsing del documento: ", e);
                      }
                    }
                    notifyDataSetChanged();
                    Log.d("TassaAdapter", "Dati caricati con successo.");
                  } else {
                    Log.e("TassaAdapter", "Errore nel recupero dei documenti: ",
                            task.getException());
                  }
                });
  }

  /**
     * ViewHolder per visualizzare singoli elementi di tassa.
     */
  public class TassaViewHolder extends RecyclerView.ViewHolder {
    TextView tvImporto;
    TextView tvScadenza;
    TextView tvStato;
    Button btnPaga;

    /**
         * Costruttore per TassaViewHolder.
         *
         * @param itemView La vista del singolo elemento di tassa.
         */
    public TassaViewHolder(@NonNull View itemView) {
      super(itemView);
      tvImporto = itemView.findViewById(R.id.tvImporto);
      tvScadenza = itemView.findViewById(R.id.tvScadenza);
      tvStato = itemView.findViewById(R.id.tvStato);
      btnPaga = itemView.findViewById(R.id.btnPaga);
    }
  }
}