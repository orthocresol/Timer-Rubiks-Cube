package org.timerrubikscube.nonactivityclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;
import org.timerrubikscube.R;
import org.w3c.dom.Text;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FirestoreAdapter extends FirestoreRecyclerAdapter<Item, FirestoreAdapter.ViewHolder> {

    public FirestoreAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position, @NonNull @NotNull Item model) {
        holder.serialTV.setText(String.valueOf(String.valueOf(model.getSerialID())));
        holder.timingTV.setText(String.valueOf(model.getTiming()));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(model.getTimestamp());
        holder.dateTV.setText(strDate);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_history, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serialTV;
        TextView timingTV;
        TextView dateTV;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            serialTV = itemView.findViewById(R.id.card_view_serial_history);
            timingTV = itemView.findViewById(R.id.card_view_timing_history);
            dateTV = itemView.findViewById(R.id.card_view_date_history);

        }
    }
}
