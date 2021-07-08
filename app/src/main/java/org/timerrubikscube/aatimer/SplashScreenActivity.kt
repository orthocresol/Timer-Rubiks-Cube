package org.timerrubikscube.aatimer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.timerrubikscube.R

class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent: Intent = Intent(this@SplashScreenActivity, SignInSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}