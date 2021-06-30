package org.timerrubikscube.nonactivityclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.timerrubikscube.R

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var timings = arrayOf(10.05, 8.88, 11.30, 9.12, 12.92, 11.22, 12.20, 11.50, 10.12, 10.22)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.timingTV.text = timings[position].toString()
    }

    override fun getItemCount(): Int {
        return timings.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var timingTV: TextView
        var dnfTV: TextView
        var okTV: TextView
        var plus2TV: TextView
        var deleteTV: TextView

        init {
            timingTV = itemView.findViewById(R.id.card_view_time)
            dnfTV = itemView.findViewById(R.id.card_view_dnf)
            okTV = itemView.findViewById(R.id.card_view_ok)
            plus2TV = itemView.findViewById(R.id.card_view_plus2)
            deleteTV = itemView.findViewById(R.id.card_view_delete)


            deleteTV.visibility = View.INVISIBLE
            dnfTV.visibility = View.INVISIBLE
            okTV.visibility = View.INVISIBLE
            plus2TV.visibility = View.INVISIBLE
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "you clicked on ${timings[position]}", Toast.LENGTH_SHORT).show()
            }
        }


    }

}