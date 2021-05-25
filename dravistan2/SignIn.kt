package com.example.dravistan2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var ctx=this;
        var flag:Boolean=false
        LIb1.setOnClickListener({
            var auth: FirebaseAuth?=null
            // Initialize Firebase Auth
            auth = Firebase.auth
            var email=LIet1.text.toString()
            var password=LIet2.text.toString()
            if(LIet1.text.isNotEmpty()&&LIet2.text.isNotEmpty())
            {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this)
                { task ->
                    if (task.isSuccessful)
                    {
                        Toast.makeText(this, "User Signed in", Toast.LENGTH_LONG).show()
                        LIet1.text.clear()
                        LIet2.text.clear()
//                        var main = Intent(ctx,MainActivity::class.java)
//                        startActivity(main)
                       //flag =true
                        val user = auth.currentUser
                        user.let {
                            if(user!!.email == adminCred)
                            {
                                startActivity(Intent(this,AdminInsertData::class.java))
                            }
                            else{
                                startActivity(Intent(this,Dashboard::class.java))
                            }
                        }

                    }
                    else
                    {
                        Toast.makeText(this, "User not Signed in", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Enter All details", Toast.LENGTH_LONG).show()
            }

//            if(flag==true)
//                startActivity(Intent(this,SplashScreen::class.java))
        })




    }
    companion object{
        val adminCred = "admin@gmail.com"
    }
}
