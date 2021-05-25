package com.example.dravistan2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var sharedPref = getSharedPreferences("userdetails",Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        lateinit var auth: FirebaseAuth
        // Initialize Firebase Auth
        auth = Firebase.auth
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.INTERNET), 123)
        //signup button
        SUb1.setOnClickListener({
            var email = SU2.text.toString()
            var password = SU4.text.toString()

            if (SU1.text.isNotEmpty() && SU2.text.isNotEmpty() && SU3.text.isNotEmpty() && SU4.text.isNotEmpty() && SU5.text.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)
                {
                        task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User Created", Toast.LENGTH_LONG).show()
                        editor.putString("PhoneNum",SU3.text.toString())
                        editor.apply()
                        editor.commit()
                        SU1.text.clear()
                        SU2.text.clear()
                        SU3.text.clear()
                        SU4.text.clear()
                        SU5.text.clear()
                        var signin = Intent(this, SignIn::class.java)
                        startActivity(signin)
                    } else {
                        Toast.makeText(this, "User not Created", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "please fill all the details", Toast.LENGTH_LONG).show()
            }
        })

        //signin button
        SUb.setOnClickListener({
            var jump = Intent(this,SignIn::class.java)
            startActivity(jump)

        })
    }
}

