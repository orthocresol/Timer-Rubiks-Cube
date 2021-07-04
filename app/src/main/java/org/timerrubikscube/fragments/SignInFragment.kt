package org.timerrubikscube.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import org.timerrubikscube.DashboardActivity
import org.timerrubikscube.HomeScreen
import org.timerrubikscube.R

class SignInFragment : Fragment() {

    lateinit var etEmail: TextInputEditText
    lateinit var etEmailLayout: TextInputLayout
    lateinit var etPassword: TextInputEditText
    lateinit var etPasswordLayout: TextInputLayout
    lateinit var btnLogin: MaterialButton
    lateinit var imgLogo: ImageView
    lateinit var animation: LottieAnimationView
    lateinit var mainLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sign_in, container, false)

        initVariables(view)


        return view
    }

    private fun initVariables(view: View) {
        etEmail = view.findViewById(R.id.sign_in_email)
        etEmailLayout = view.findViewById(R.id.sign_in_email_layout)
        etPassword = view.findViewById(R.id.sign_in_password)
        etPasswordLayout = view.findViewById(R.id.sign_in_password_layout)
        btnLogin = view.findViewById(R.id.sign_in_login_btn)
        imgLogo = view.findViewById(R.id.sign_in_logo)
        animation = view.findViewById(R.id.sign_in_loading)
        mainLayout = view.findViewById(R.id.sign_in_mainLayout)
        btnLogin.setOnClickListener(View.OnClickListener {
            signIn()
            disappearElements()
        })
    }

    private fun disappearElements() {
        animation.visibility = View.VISIBLE
        animation.playAnimation()
    }

    private fun reappearElements() {
        animation.visibility = View.INVISIBLE
    }


    private fun signIn() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString()

        if(email.equals("") || password.equals("")){
            Log.d("SignIn", "signIn: fields cannot be empty")
            return
        }

        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener {task ->
                if(task.isSuccessful){
                    Log.d("SignIn", "signIn: signed in successfully")
                    val intent = Intent(activity, HomeScreen::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                else {
                    reappearElements()
                    snackbarAlert()
                }
            })
    }

    private fun snackbarAlert() {
        Snackbar.make(mainLayout, "Sign In failed", Snackbar.LENGTH_LONG)
            .setAction("Close", View.OnClickListener {

            })
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
            .show()
    }


}