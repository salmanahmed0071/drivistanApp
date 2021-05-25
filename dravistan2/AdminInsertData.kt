package com.example.dravistan2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_admin_insert_data.*

class AdminInsertData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_insert_data)

        var total: Int =0
        var check:Boolean =true
//        var tmp :String =""
        var auth: FirebaseAuth?=null
        // Initialize Firebase Auth
        auth = Firebase.auth
        val user = auth.currentUser
        user.let {
            if(user!!.email != adminCred)//if the user is not admin go to dashboard
            {
                startActivity(Intent(this,Dashboard::class.java))
            }
        }
        createbtn.setOnClickListener(){
            check =true
            val database = Firebase.database
            val myRef = database.getReference("RecordID")


            if(input_eventname.text.isEmpty())
            {
                input_eventname.error = "Please input event name"
                check=false
                //return@setOnClickListener
            }
            if(input_details.text!!.isEmpty()){
                input_details.error = "Please input details consisting of venue and host"
                check =false
            }
            if(input_startDate.text.isEmpty()){
                input_details.error = "Please input starting date"
                check =false
            }

            val database2  = Firebase.database.reference
            var event_name = input_eventname.text.toString()
            var det = input_details.text.toString()
            var sd = input_startDate.text.toString()
            var ed = input_endDate.text.toString()


            if(check)
            {
                var key = database2.push().key
                var obj : EventsClass = EventsClass(key!!,event_name,sd,ed,det)
                database2.child("Events").child(key!!).setValue(obj)//updates the number of records
                //database2.child("RecordID").setValue((total+1).toString())

                Toast.makeText(this,
                    "Event created successfully ${obj.eventName}",
                    Toast.LENGTH_LONG).show()
            }



        }

    }

    companion object{
        val adminCred = "admin@gmail.com"
    }
}
