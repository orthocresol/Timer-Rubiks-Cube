package org.timerrubikscube

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent: Intent = Intent(this@SplashScreenActivity, SignInSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}