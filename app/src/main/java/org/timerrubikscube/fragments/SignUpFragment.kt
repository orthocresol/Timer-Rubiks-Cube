package org.timerrubikscube.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import org.timerrubikscube.DashboardActivity
import org.timerrubikscube.R
import org.timerrubikscube.nonactivityclass.Session


class SignUpFragment : Fragment() {

    lateinit var materialButton: MaterialButton
    lateinit var etEmail: TextInputEditText
    lateinit var etPassword: TextInputEditText
    lateinit var etConfirmPassword: TextInputEditText
    lateinit var etName: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        initVariables(view)
        return view
    }

    private fun initVariables(view: View) {
        materialButton = view.findViewById(R.id.sign_up_register_btn)
        etName = view.findViewById(R.id.sign_up_name)
        etEmail = view.findViewById(R.id.sign_up_email)
        etPassword = view.findViewById(R.id.sign_up_password)
        etConfirmPassword = view.findViewById(R.id.sign_up_confirm_password)

        materialButton.setOnClickListener(View.OnClickListener {
            registerAccount()
        })
    }

    private fun registerAccount() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        if (passedCriteria(password, confirmPassword, name, email)) return
        val auth = FirebaseAuth.getInstance()


        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener {task ->
            if(task.isSuccessful){
                Log.d("SignUp", "registerAccount: account created successfully")
                val user = auth.currentUser
                val updateName = UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build()

                user!!.updateProfile(updateName)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("SignUp", "Name is updated")
                        }
                    }

                val userID = FirebaseAuth.getInstance().currentUser?.uid
                val ref = FirebaseFirestore.getInstance().collection("session").document(userID!!)
                val session = Session("session_name", 0)
                ref.set(session)
                    .addOnSuccessListener(OnSuccessListener {
                        val intent = Intent(activity, DashboardActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    })
            }


            })
    }

    private fun passedCriteria(
        password: String,
        confirmPassword: String,
        name: String,
        email: String
    ): Boolean {
        if (password.equals(confirmPassword) == false) {
            Log.d("SignUp", "registerAccount: password did not match")
            return true
        }
        if (name.equals("")) {
            Log.d("SignUp", "registerAccount: name cannot be empty")
            return true
        }
        if (password.equals("")) {
            Log.d("SignUp", "registerAccount: password cannot be empty")
            return true
        }
        if (email.equals("")) {
            Log.d("SignUp", "registerAccount: email cannot be empty")
            return true
        }
        return false
    }

}