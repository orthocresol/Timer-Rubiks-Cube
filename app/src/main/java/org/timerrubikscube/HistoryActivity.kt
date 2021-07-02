package org.timerrubikscube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.nonactivityclass.FirestoreAdapter
import org.timerrubikscube.nonactivityclass.Item

class HistoryActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    var collectionReference = db.collection("Time $userID")
    var adapter: FirestoreAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val query = collectionReference.orderBy("timeFromBeginning", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Item>()
            .setQuery(query, Item::class.java)
            .build()
        adapter = FirestoreAdapter(options)
        val recyclerView = findViewById<RecyclerView>(R.id.history_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
}