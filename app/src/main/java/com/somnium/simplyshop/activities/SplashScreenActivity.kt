package com.somnium.simplyshop.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.somnium.simplyshop.R

const val REQUEST_CODE_SOFT_ON_BOARDING = 99

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var signIn : TextView
    private lateinit var beginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initUI()
    }

    private fun initUI() {
        signIn = findViewById(R.id.splash_screen_sign_in)
        beginBtn = findViewById(R.id.splash_screen_start)

        beginBtn.setOnClickListener { closeSplashScreen() }
        signIn.setOnClickListener { onSignIn() }
    }

    private fun closeSplashScreen() {
        if (SoftOnBoardingActivity.isViewed(this)) {
            openRegistrationActivity()
        } else {
            startActivityForResult(Intent(this, SoftOnBoardingActivity::class.java), REQUEST_CODE_SOFT_ON_BOARDING)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SOFT_ON_BOARDING){
            openRegistrationActivity()
        }
    }

    private fun openRegistrationActivity() {
        startActivity(Intent(this, RegistrationActivity::class.java))
        finish()
    }

    private fun onSignIn() {
        val intent = Intent(this, LoginPageActivity::class.java)
        startActivity(intent)
    }
}
