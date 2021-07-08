package org.timerrubikscube.aatimer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.timerrubikscube.R;
import org.timerrubikscube.aatimer.nonactivityclass.Item;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {
    ArrayList<Item> arrayList;

    public StatAdapter(ArrayList<Item> arrayList){
        this.arrayList = arrayList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_single_item_stat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Item item = arrayList.get(position);


        holder.serialTV.setText(String.valueOf(position + 1));
        holder.timeTV.setText(String.valueOf(item.getTiming()));
        holder.dateTV.setText(item.getTimestamp());
        holder.scrambleTV.setText(item.getScramble());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView serialTV, timeTV, dateTV, scrambleTV;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            serialTV = itemView.findViewById(R.id.serial);
            timeTV = itemView.findViewById(R.id.time);
            dateTV = itemView.findViewById(R.id.date);
            scrambleTV = itemView.findViewById(R.id.scramble);


        }
    }
}
