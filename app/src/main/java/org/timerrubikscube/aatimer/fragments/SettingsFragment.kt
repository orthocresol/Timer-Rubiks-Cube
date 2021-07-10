package org.timerrubikscube.aatimer.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.SignInSignUpActivity
import org.timerrubikscube.aatimer.adapters.StatAdapter
import org.timerrubikscube.aatimer.nonactivityclass.Item
import org.timerrubikscube.aatimer.nonactivityclass.SessionManager

class SettingsFragment : Fragment() {
    lateinit var mView : View
    lateinit var deleteSolvesBtn : MaterialButton
    lateinit var logoutBtn : MaterialButton
    lateinit var nameTV : TextView
    lateinit var emailTV : TextView
    lateinit var sessionManager : SessionManager
    lateinit var sw_inspection : SwitchMaterial
    lateinit var sw_autoLogout : SwitchMaterial
    lateinit var sw_showScrambleBtn : SwitchMaterial

    val mAuth = FirebaseAuth.getInstance()
    val user = mAuth.currentUser
    val userID = user?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_settings, container, false)
        initVariables()


        return mView
    }

    private fun initVariables() {
        sessionManager = SessionManager(context)
        sw_inspection = mView.findViewById(R.id.settings_sw_enable_inspection)
        sw_autoLogout = mView.findViewById(R.id.settings_sw_enable_auto_logout)
        sw_showScrambleBtn = mView.findViewById(R.id.settings_sw_show_scramble_btn)

        sw_inspection.isChecked = sessionManager.enableInspection;
        sw_autoLogout.isChecked = sessionManager.enableAutoLogout;
        sw_showScrambleBtn.isChecked = sessionManager.scrambleButton;


        deleteSolvesBtn = mView.findViewById(R.id.settings_delete_all_solves_btn)
        logoutBtn = mView.findViewById(R.id.settings_logout)
        nameTV = mView.findViewById(R.id.settings_username)
        emailTV = mView.findViewById(R.id.settings_user_email)

        nameTV.text = user?.displayName
        emailTV.text = user?.email

        clickListeners()

    }

    private fun clickListeners() {
        sw_inspection.setOnClickListener {
            sessionManager.enableInspection = sw_inspection.isChecked;
        }
        sw_autoLogout.setOnClickListener {
            sessionManager.enableAutoLogout = sw_autoLogout.isChecked;
        }
        sw_showScrambleBtn.setOnClickListener {
            sessionManager.scrambleButton = sw_showScrambleBtn.isChecked;
        }


        deleteSolvesBtn.setOnClickListener {
            //show alert dialog
            collectionRef
                .get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    for (documentSnapshot in queryDocumentSnapshots) {
                        collectionRef.document(documentSnapshot.id).delete()
                    }
                }
        }

        logoutBtn.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(activity, SignInSignUpActivity::class.java))
            activity?.finish()
        }
    }


}