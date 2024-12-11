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
import java.util.List;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.CalendarioViewHolder> {

    private final Context context;
    private final List<Rifiuto> rifiutiList;

    public CalendarioAdapter(Context context, List<Rifiuto> rifiutiList) {
        this.context = context;
        this.rifiutiList = rifiutiList;
    }

    @NonNull
    @Override
    public CalendarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendario_card, parent, false);
        return new CalendarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position) {
        Rifiuto rifiuto = rifiutiList.get(position);

        holder.tvCategoria.setText("Categoria: " + (rifiuto.getCategoria() != null ? rifiuto.getCategoria().name() : "Non specificata"));
        holder.tvGiornoConferimento.setText(rifiuto.getGiornoConferimento() != null ? rifiuto.getGiornoConferimento().toString() : "Giorno non specificato");
        holder.tvOrarioConferimento.setText("Orario: " + (rifiuto.getOrarioConferimento() != null ? rifiuto.getOrarioConferimento() : "Non specificato"));
        holder.tvIstruzioni.setText("Istruzioni: " + (rifiuto.getIstruzioni() != null ? rifiuto.getIstruzioni() : "Nessuna istruzione"));
        holder.ivCategoriaImmagine.setImageResource(getImageResource(rifiuto.getCategoria()));
        holder.cardView.setCardBackgroundColor(rifiuto.getCodiceColore() != null ? Color.parseColor(rifiuto.getCodiceColore()) : Color.parseColor("#FFFFFF"));
    }

    @Override
    public int getItemCount() {
        return rifiutiList.size();
    }

    private int getImageResource(Rifiuto.Categoria categoria) {
        if (categoria == null) return R.drawable.logodark; // Immagine predefinita
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
            case TESSILI:
                return R.drawable.ic_tessili;
            default:
                return R.drawable.logodark;
        }
    }

    public static class CalendarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvGiornoConferimento, tvCategoria, tvOrarioConferimento, tvIstruzioni;
        ImageView ivCategoriaImmagine;
        CardView cardView;

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
