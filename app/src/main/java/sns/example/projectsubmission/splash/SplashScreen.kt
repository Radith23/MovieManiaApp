package sns.example.projectsubmission.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import sns.example.projectsubmission.MainActivity
import sns.example.projectsubmission.R

class SplashScreen : AppCompatActivity() {

    private val SplashTime: Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed( {
            startActivity(Intent (this, MainActivity::class.java))
        }
        , SplashTime)
    }
}