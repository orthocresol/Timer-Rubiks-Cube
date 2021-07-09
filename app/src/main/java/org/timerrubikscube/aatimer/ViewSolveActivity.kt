package org.timerrubikscube.aatimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.nonactivityclass.Item

class ViewSolveActivity : AppCompatActivity() {
    var item : Item? = null

    lateinit var backBtn : ImageButton
    lateinit var solveTime : TextView
    lateinit var scramble : TextView
    lateinit var swOk : SwitchMaterial
    lateinit var swplus2 : SwitchMaterial
    lateinit var swDNF : SwitchMaterial
    lateinit var deleteBtn : MaterialButton
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_solve)
        item = intent.getParcelableExtra("solve item")
        initVariables()

    }

    private fun initVariables() {
        backBtn = findViewById(R.id.view_solve_back)
        backBtn.setOnClickListener {
            finish()
        }
        solveTime = findViewById(R.id.view_solve_time)
        scramble = findViewById(R.id.view_solve_scramble)
        swOk = findViewById(R.id.view_solve_ok_switch)
        swplus2 = findViewById(R.id.view_solve_plus2_switch)
        swDNF = findViewById(R.id.view_solve_dnf_switch)
        deleteBtn = findViewById(R.id.view_solve_delete)

        scramble.text = item?.scramble
        swOk.isChecked = item!!.ok
        swDNF.isChecked = item!!.dnf
        swplus2.isChecked = item!!.plus2

        if(item!!.ok){
            swOk.isEnabled = false
            solveTime.text = item?.timing.toString()
        }
        else if(item!!.dnf){
            swDNF.isEnabled = false
            solveTime.text = "DNF"
        }
        else if(item!!.plus2){
            swplus2.isEnabled = false
            val plus2 : Float? = 2.00f + item!!.timing
            solveTime.text = String.format("%.2f", plus2)
        }


        swOk.setOnClickListener{
            swDNF.isChecked = false
            swplus2.isChecked = false
            swDNF.isEnabled = true
            swplus2.isEnabled = true
            swOk.isEnabled = false
            solveTime.text = item!!.timing.toString()
            item?.ok = true
            item?.dnf = false
            item?.plus2 = false
            collectionRef.document(item!!.id).set(item!!)
        }

        swDNF.setOnClickListener{
            swOk.isChecked = false
            swplus2.isChecked = false
            swDNF.isEnabled = false
            swplus2.isEnabled = true
            swOk.isEnabled = true
            solveTime.text = "DNF"
            item?.ok = false
            item?.dnf = true
            item?.plus2 = false
            collectionRef.document(item!!.id).set(item!!)
        }

        swplus2.setOnClickListener {
            swDNF.isChecked = false
            swOk.isChecked = false
            swDNF.isEnabled = true
            swOk.isEnabled = true
            swplus2.isEnabled = false
            val plus2 : Float? = 2.00f + item!!.timing
            solveTime.text = String.format("%.2f", plus2)
            item?.ok = false
            item?.dnf = false
            item?.plus2 = true
            collectionRef.document(item!!.id).set(item!!)
        }

        deleteBtn.setOnClickListener {
            //alert dialog
            collectionRef.document(item!!.id).delete()
            finish()
        }
    }
}