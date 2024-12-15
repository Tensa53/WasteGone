package it.giga.wastegone.gestioneSmaltimentoRifiuti.application.logic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;
import java.util.List;

/*
    * Adapter per la visualizzazione dei rifiuti nel calendario
 */

/**
 * Adapter per la visualizzazione dei rifiuti nel calendario.
 */
public class CalendarioAdapter
        extends RecyclerView.Adapter<CalendarioAdapter.CalendarioViewHolder> {

  private final Context context;
  private final List<Rifiuto> rifiutiList;

  /**
   * Costruttore per CalendarioAdapter.
   *
   * @param context Contesto dell'applicazione
   * @param rifiutiList Lista dei rifiuti
   */
  public CalendarioAdapter(Context context, List<Rifiuto> rifiutiList) {
    this.context = context;
    this.rifiutiList = rifiutiList;
  }

  /**
   * Crea un nuovo ViewHolder per un elemento rifiuto.
   *
   * @param parent Il ViewGroup genitore
   * @param viewType Il tipo di vista del nuovo View
   * @return Un nuovo CalendarioViewHolder
   */
  @NonNull
  @Override
  public CalendarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_calendario_card,
                parent, false);
    return new CalendarioViewHolder(view);
  }

  /**
   * Associa un rifiuto a un ViewHolder.
   *
   * @param holder Il ViewHolder a cui associare il rifiuto
   * @param position La posizione del rifiuto nella lista
   */
  @Override
    public void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position) {
    Rifiuto rifiuto = rifiutiList.get(position);

    holder.tvCategoria.setText("Categoria: "
                + (rifiuto.getCategoria() != null ? rifiuto.getCategoria().name()
            : "Non specificata"));
    holder.tvGiornoConferimento.setText(rifiuto.getGiornoConferimento() != null
                ? rifiuto.getGiornoConferimento().toString() : "Giorno non specificato");
    holder.tvOrarioConferimento.setText("Orario: " + (rifiuto.getOrarioConferimento()
                != null ? rifiuto.getOrarioConferimento() : "Non specificato"));
    holder.tvIstruzioni.setText("Istruzioni: " + (rifiuto.getIstruzioni()
                != null ? rifiuto.getIstruzioni() : "Nessuna istruzione"));
    holder.ivCategoriaImmagine.setImageResource(getImageResource(rifiuto.getCategoria()));
    holder.cardView.setCardBackgroundColor(rifiuto.getCodiceColore()
            != null ? Color.parseColor(rifiuto.getCodiceColore()) : Color.parseColor("#FFFFFF"));
  }

  /**
     * Restituisce il numero totale di rifiuti nella lista.
     *
     * @return Il numero di rifiuti
     */
  @Override
    public int getItemCount() {
    return rifiutiList.size();
  }

  /**
     * Restituisce la risorsa immagine per una categoria di rifiuto.
     *
     * @param categoria La categoria del rifiuto
     * @return L'ID della risorsa immagine
     */
  private int getImageResource(Rifiuto.Categoria categoria) {
    if (categoria == null) {
      return R.drawable.logodark;
    } // Immagine predefinita
    switch (categoria) {
      case PLASTICA:
        return R.drawable.ic_plastica;
      case CARTA:
        return R.drawable.ic_carta;
      case INDIFFERENZIATA:
        return R.drawable.ic_indifferenziata;
      case UMIDO:
        return R.drawable.ic_umido;
      case ALLUMINIO:
        return R.drawable.ic_alluminio;
      case VETRO:
        return R.drawable.ic_vetro;
      default:
        return R.drawable.logodark;
    }
  }

  /**
     * ViewHolder per un elemento rifiuto.
     */
  public static class CalendarioViewHolder extends RecyclerView.ViewHolder {
    TextView tvGiornoConferimento;
    TextView tvCategoria;
    TextView tvOrarioConferimento;
    TextView tvIstruzioni;
    ImageView ivCategoriaImmagine;
    CardView cardView;

    /**
         * Costruttore per CalendarioViewHolder.
         *
         * @param itemView La vista dell'elemento rifiuto
         */
    public CalendarioViewHolder(@NonNull View itemView) {
      super(itemView);
      tvGiornoConferimento = itemView.findViewById(R.id.tvGiornoConferimento);
      tvCategoria = itemView.findViewById(R.id.tvCategoria);
      tvOrarioConferimento = itemView.findViewById(R.id.tvOrarioConferimento);
      tvIstruzioni = itemView.findViewById(R.id.tvIstruzioni);
      ivCategoriaImmagine = itemView.findViewById(R.id.ivCategoriaImmagine);
      cardView = itemView.findViewById(R.id.item_calendario);
    }
  }
}