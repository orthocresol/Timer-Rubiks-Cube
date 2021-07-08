package org.timerrubikscube.aatimer.stat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.nonactivityclass.Item


class StatGraphFragment : Fragment() {
    lateinit var mView : View
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_stat_graph, container, false)
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
                for(documentSnapshot in queryDocumentSnapshots){
                    val item = documentSnapshot.toObject(Item::class.java)
                    item.id = documentSnapshot.id
                    itemList.add(item)
                }
                showLineChart(itemList)
            }
    }

    private fun showLineChart(itemList : ArrayList<Item>) {
        val lineChart = mView.findViewById<LineChart>(R.id.graph_line_chart)
        val dataset = ArrayList<Entry>()
        var inc = 0f
        for(item in itemList){
            dataset.add(Entry(inc, item.timing))
            inc++
        }
        val lineDataSet = LineDataSet(dataset, "Solves")
        val iLineDataSet = ArrayList<ILineDataSet>()
        iLineDataSet.add(lineDataSet)
        val lineData = LineData(iLineDataSet)
        lineChart.data = lineData
        lineChart.invalidate()


    }

}