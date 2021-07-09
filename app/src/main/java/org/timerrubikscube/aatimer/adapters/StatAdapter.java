package org.timerrubikscube.aatimer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.timerrubikscube.R;
import org.timerrubikscube.aatimer.ViewSolveActivity;
import org.timerrubikscube.aatimer.nonactivityclass.Item;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {
    ArrayList<Item> arrayList;
    Context context;

    public StatAdapter(ArrayList<Item> arrayList){
        this.arrayList = arrayList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_single_item_stat, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Item item = arrayList.get(position);


        holder.serialTV.setText(String.valueOf(position + 1));
        if(item.getOk()) {
            float temp = item.getTiming() ;
            holder.timeTV.setText(String.format("%.2f", temp));
        }
        else if(item.getDNF()){
            holder.timeTV.setText("DNF");
        }
        else if(item.getPlus2()){
            float temp = item.getTiming() + 2.00f;
            holder.timeTV.setText(String.format("%.2f", temp) + "+");
        }
        holder.dateTV.setText(item.getTimestamp());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewSolveActivity.class);
                intent.putExtra("solve item", item);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView serialTV, timeTV, dateTV;
        RelativeLayout mainLayout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            serialTV = itemView.findViewById(R.id.serial);
            timeTV = itemView.findViewById(R.id.time);
            dateTV = itemView.findViewById(R.id.date);
            mainLayout = itemView.findViewById(R.id.single_item_stat_mainLayout);




        }
    }
}
