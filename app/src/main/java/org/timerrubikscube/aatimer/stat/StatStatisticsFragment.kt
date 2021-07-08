package org.timerrubikscube.aatimer.stat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.adapters.StatAdapter
import org.timerrubikscube.aatimer.nonactivityclass.Item


class StatStatisticsFragment : Fragment() {
    lateinit var mView: View
    lateinit var recyclerView: RecyclerView
    var adapter : StatAdapter? = null
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_stat_statistics, container, false)
        initVariables(mView)
        getItems()
        return mView
    }

    override fun onResume() {
        super.onResume()
        getItems()
    }





    private fun initVariables(mView: View) {
        recyclerView = mView.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

    private fun getItems() {
        val itemList = ArrayList<Item>()
        collectionRef
            .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
            .limit(50)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for(documentSnapshot in queryDocumentSnapshots){
                    val item = documentSnapshot.toObject(Item::class.java)
                    item.id = documentSnapshot.id
                    itemList.add(item)
                }
                adapter = StatAdapter(itemList)
                recyclerView.adapter = adapter
            }
    }



}