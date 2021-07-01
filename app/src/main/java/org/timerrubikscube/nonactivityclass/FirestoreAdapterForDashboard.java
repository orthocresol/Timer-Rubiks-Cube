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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FirestoreAdapterForDashboard extends FirestoreRecyclerAdapter<Item, FirestoreAdapterForDashboard.ViewHolder> {

    public FirestoreAdapterForDashboard(@NonNull @NotNull FirestoreRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position, @NonNull @NotNull Item model) {
        holder.timingTV.setText(String.valueOf(model.getTiming()));
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_history_for_dashboard, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView timingTV;



        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            timingTV = itemView.findViewById(R.id.card_view_timing_history);

        }
    }
}
