package org.timerrubikscube.aatimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.nonactivityclass.Item
import java.util.*
import kotlin.collections.ArrayList

class RecordFragment : Fragment() {

    lateinit var mView: View
    lateinit var singleBestTV : TextView
    lateinit var mo3TV : TextView
    lateinit var ao5TV : TextView
    lateinit var ao12TV : TextView
    lateinit var mo50TV : TextView
    lateinit var mo100TV : TextView

    lateinit var csingleBestTV : TextView
    lateinit var cmo3TV : TextView
    lateinit var cao5TV : TextView
    lateinit var cao12TV : TextView
    lateinit var cmo50TV : TextView
    lateinit var cmo100TV : TextView

    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_record, container, false)
        initVariables(mView)
        getItems()

        return mView;
    }

    override fun onResume() {
        super.onResume()
        getItems()
    }
    private fun initVariables(mView : View) {
        singleBestTV = mView.findViewById(R.id.value_single_time)
        mo3TV = mView.findViewById(R.id.value_mean_of_3)
        ao5TV = mView.findViewById(R.id.value_avg_of_5)
        ao12TV = mView.findViewById(R.id.value_avg_of_12)
        mo50TV = mView.findViewById(R.id.value_mean_of_50)
        mo100TV = mView.findViewById(R.id.value_mean_of_100)
        csingleBestTV = mView.findViewById(R.id.value_single_time_c)
        cmo3TV = mView.findViewById(R.id.value_mean_of_3_c)
        cao5TV = mView.findViewById(R.id.value_avg_of_5_c)
        cao12TV = mView.findViewById(R.id.value_avg_of_12_c)
        cmo50TV = mView.findViewById(R.id.value_mean_of_50_c)
        cmo100TV = mView.findViewById(R.id.value_mean_of_100_c)
    }

    private fun getItems() {
        val itemList = ArrayList<Item>()
        collectionRef
            .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for (documentSnapshot in queryDocumentSnapshots) {
                    val item = documentSnapshot.toObject(Item::class.java)
                    item.id = documentSnapshot.id
                    itemList.add(item)
                }
                findSingleBest(itemList)
                findmo3(itemList)
                findao5(itemList)
                findao12(itemList)
                findmo50(itemList)
                findmo100(itemList)

                findCurrentSingleBest(itemList)
                findCurrentmo3(itemList)
                findCurrentao5(itemList)
                findCurrentao12(itemList)
                findCurrentmo50(itemList)
                findCurrentmo100(itemList)

            }
    }




    private fun findmo100(itemList: ArrayList<Item>) {
        if(itemList.size < 100){
            mo100TV.text = "DNF"
            return
        }
        val size = itemList.size
        var lowest = 0.00f
        for(i in 0..99){
            lowest += itemList[i].timing
        }
        var aggregate = lowest
        for(i in 0..(size - 101)){
            aggregate = aggregate - itemList[i].timing + itemList[i + 100].timing
            if(aggregate < lowest){
                lowest = aggregate
            }
        }
        lowest /= 100
        mo100TV.text = String.format("%.2f", lowest)
    }

    private fun findCurrentmo100(itemList: ArrayList<Item>) {
        if(itemList.size < 100){
            cmo100TV.text = "DNF"
            return
        }
        var lowest = 0.00f
        for(i in 0..99){
            lowest += itemList[i].timing
        }
        lowest /= 100
        cmo100TV.text = String.format("%.2f", lowest)

    }

    private fun findmo50(itemList: ArrayList<Item>) {
        if(itemList.size < 50){
            mo50TV.text = "DNF"
            return
        }
        val size = itemList.size
        var lowest = 0.00f
        for(i in 0..49){
            lowest += itemList[i].timing
        }
        var aggregate = lowest
        for(i in 0..(size - 51)){
            aggregate = aggregate - itemList[i].timing + itemList[i + 50].timing
            if(aggregate < lowest){
                lowest = aggregate
            }
        }
        lowest /= 50
        mo50TV.text = String.format("%.2f", lowest)
    }

    private fun findCurrentmo50(itemList: ArrayList<Item>) {
        if(itemList.size < 50){
            cmo50TV.text = "DNF"
            return
        }

        var lowest = 0.00f
        for(i in 0..49){
            lowest += itemList[i].timing
        }
        lowest /= 50
        cmo50TV.text = String.format("%.2f", lowest)
    }

    private fun findao12(itemList: ArrayList<Item>) {
        if(itemList.size < 12){
            ao12TV.text = "DNF"
            return
        }
        var lowest = 1000.00f
        val size = itemList.size
        for(i in 0..(size - 12)){
            val toSort = ArrayList<Float>()
            for(j in i..(i + 11)){
                toSort.add(itemList[j].timing)
            }
            Collections.sort(toSort)
            var aggregate = 0.00f
            for(j in 1..10){
                aggregate += toSort[j]
            }
            if(aggregate < lowest){
                lowest = aggregate
            }
        }
        lowest /= 10
        ao12TV.text = String.format("%.2f", lowest)
    }

    private fun findCurrentao12(itemList: ArrayList<Item>) {
        if(itemList.size < 12){
            cao12TV.text = "DNF"
            return
        }
        val toSort = ArrayList<Float>()
        for(i in 0..11){
            toSort.add(itemList[i].timing)
        }
        Collections.sort(toSort)
        var aggregate = 0.00f
        for(j in 1..10){
            aggregate += toSort[j]
        }
        aggregate /= 10
        cao12TV.text = String.format("%.2f", aggregate)
    }

    private fun findao5(itemList: ArrayList<Item>) {
        if(itemList.size < 5){
            ao5TV.text = "DNF"
            return
        }
        var lowest = 1000.00f
        val size = itemList.size
        for(i in 0..(size - 5)){
            val toSort = ArrayList<Float>()
            for(j in i..(i + 4)){
                toSort.add(itemList[j].timing)
            }
            Collections.sort(toSort)
            var aggregate = 0.00f
            for(j in 1..3){
                aggregate += toSort[j]
            }
            if(aggregate < lowest){
                lowest = aggregate
            }
        }
        lowest /= 3
        ao5TV.text = String.format("%.2f", lowest)

    }

    private fun findCurrentao5(itemList: ArrayList<Item>) {
        if(itemList.size < 5){
            cao5TV.text = "DNF"
            return
        }
        val toSort = ArrayList<Float>()
        for(i in 0..4){
            toSort.add(itemList[i].timing)
        }
        Collections.sort(toSort)
        var aggregate = 0.00f
        for(j in 1..3){
            aggregate += toSort[j]
        }
        aggregate /= 3
        cao5TV.text = String.format("%.2f", aggregate)
    }



    private fun findmo3(itemList: ArrayList<Item>) {
        if(itemList.size < 3){
            mo3TV.text = "DNF"
            return
        }
        val size = itemList.size
        var lowest = itemList[0].timing + itemList[1].timing + itemList[2].timing
        var aggregate = lowest
        for(i in 0..(size - 4)){
            aggregate = aggregate - itemList[i].timing + itemList[i + 3].timing
            if(aggregate < lowest){
                lowest = aggregate
            }
        }
        lowest /= 3
        mo3TV.text = String.format("%.2f", lowest)

    }

    private fun findCurrentmo3(itemList: ArrayList<Item>) {
        if(itemList.size < 3){
            cmo3TV.text = "DNF"
            return
        }
        val temp = (itemList[0].timing + itemList[1].timing + itemList[2].timing) / 3
        cmo3TV.text = String.format("%.2f", temp)
    }

    private fun findSingleBest(itemList: ArrayList<Item>) {
        if(itemList.size < 1){
            singleBestTV.text = "DNF"
            return
        }

        var lowest = 1000.00f
        for(item in itemList){
            if(item.timing < lowest){
                lowest = item.timing
            }
        }
        singleBestTV.text = String.format("%.2f", lowest)
    }

    private fun findCurrentSingleBest(itemList: ArrayList<Item>) {
        if(itemList.size < 1){
            csingleBestTV.text = "DNF"
            return
        }
        csingleBestTV.text = String.format("%.2f", itemList[0].timing)
    }

}