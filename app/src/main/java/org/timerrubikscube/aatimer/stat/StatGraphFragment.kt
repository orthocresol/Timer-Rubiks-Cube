package org.timerrubikscube.aatimer.stat

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.nonactivityclass.Item


class StatGraphFragment : Fragment() {
    lateinit var mView: View
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_stat_graph_bar, container, false)
        getData()
        return mView
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        val itemList = ArrayList<Item>()
        collectionRef
            .orderBy("timeFromBeginning", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for (documentSnapshot in queryDocumentSnapshots) {
                    val item = documentSnapshot.toObject(Item::class.java)
                    item.id = documentSnapshot.id
                    itemList.add(item)
                }
                showLineChart(itemList)
                showBarChart(itemList)
                showPieChart(itemList)
            }
    }

    private fun showPieChart(itemList: java.util.ArrayList<Item>) {
        val mapping = ArrayList<Int>()
        for (i in 0..299) {
            mapping.add(0);
        }

        val pieChart = mView.findViewById<PieChart>(R.id.graph_pie_chart)
        val solves = ArrayList<PieEntry>()
        for (item in itemList) {
            if (item.dnf) {

            } else if (item.plus2) {
                val cast2int: Int = (item.timing + 2.00f).toInt()
                if (cast2int < 299)
                    mapping.set(cast2int, mapping.get(cast2int) + 1)
            } else if (item.ok) {
                val cast2int: Int = item.timing.toInt()
                if (cast2int < 299)
                    mapping.set(cast2int, mapping.get(cast2int) + 1)
            }
        }

        for (i in 0..299) {
            if (mapping.get(i) != 0) {
                solves.add(PieEntry(mapping.get(i).toFloat(), i.toString()))
            }
        }
        val pieDataSet = PieDataSet(solves, "time distribution")
        pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 16f

        val pieData = PieData(pieDataSet)


        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.centerText = "Solves"
        pieChart.animate()
    }

    private fun showBarChart(itemList: java.util.ArrayList<Item>) {
        val mapping = ArrayList<Int>()
        for (i in 0..299) {
            mapping.add(0);
        }

        val barChart = mView.findViewById<BarChart>(R.id.graph_bar_chart)
        val solves = ArrayList<BarEntry>()
        for (item in itemList) {
            if (item.dnf) {

            } else if (item.plus2) {
                val cast2int: Int = (item.timing + 2.00f).toInt()
                if (cast2int < 299)
                    mapping.set(cast2int, mapping.get(cast2int) + 1)
            } else if (item.ok) {
                val cast2int: Int = item.timing.toInt()
                if (cast2int < 299)
                    mapping.set(cast2int, mapping.get(cast2int) + 1)
            }
        }

        for (i in 0..299) {
            if (mapping.get(i) != 0) {
                solves.add(BarEntry(i.toFloat(), mapping.get(i).toFloat()))

            }
        }
        val barDataSet = BarDataSet(solves, "time distribution")
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)

        barChart.setFitBars(true)
        barChart.data = barData
        barChart.description.text = "Solves"
        barChart.animateY(2000)

    }

    private fun showLineChart(itemList: ArrayList<Item>) {
        val lineChart = mView.findViewById<LineChart>(R.id.graph_line_chart)
        val dataset = ArrayList<Entry>()
        var inc = 0f
        for (item in itemList) {
            if (item.dnf) {
                // no addition
            } else if (item.plus2) {
                dataset.add(Entry(inc, item.timing + 2.00f))
                inc++
            } else if (item.ok) {
                dataset.add(Entry(inc, item.timing))
                inc++
            }

        }
        val lineDataSet = LineDataSet(dataset, "Solves")
        val iLineDataSet = ArrayList<ILineDataSet>()
        iLineDataSet.add(lineDataSet)
        val lineData = LineData(iLineDataSet)
        lineChart.data = lineData
        lineChart.invalidate()


    }

}