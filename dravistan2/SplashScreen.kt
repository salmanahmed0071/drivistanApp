package com.example.dravistan2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var signup = Intent(this,SignUp::class.java)
        Handler().postDelayed(object : Runnable{
            override fun run() {
                startActivity(signup)
            }
        },3000)


    }
}
