package com.example.dravistan2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dravistan2.ui.login.Attendance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.my_custom_event_list_view.*

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        var sharedPref = getSharedPreferences("userdetails", Context.MODE_PRIVATE)
        val dbref  = Firebase.database.reference

            var temp : Bundle? = intent.extras
            testView.text = "Details: "+ intent.getStringExtra("event_name") + "\n" +
                    intent.getStringExtra("eventDetails") +"\nStart Date:"+intent.getStringExtra("startDate")+"\nEnd Date: "
                    intent.getStringExtra("endDate")+"\n"

        bkBtn.setOnClickListener(){
            startActivity(Intent(this,EventsList::class.java))
        }

        requestBtn.setOnClickListener(){
                var prompt = AlertDialog.Builder(this)
                var phnum = sharedPref.getString("PhoneNum","0")
                if(phnum=="0")
                {
                    Toast.makeText(this,"No phone number stored",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                prompt.setTitle("Confirm")
                prompt.setMessage("Are you sure you want to apply for this event? You will receive a text message" +
                        "from the admin with the code generated for admission\n")
                prompt.setNegativeButton("No"){
                    dialog, which ->
                }
                prompt.setPositiveButton("OK"){
                    dialog, which ->

                    var smsApi = SmsManager.getDefault()
                     val STRING_CHARACTERS = ('0'..'z').toList().toTypedArray()
                    val password = (1..32).map { STRING_CHARACTERS.random() }.joinToString("")
                    smsApi.sendTextMessage(phnum,"Drivistan","You have applied for event"+intent.getStringExtra("event_name")+
                        " Your admission code is $password",null,null)

                   //write data to user SQL
                    var dbObj = UserDetailsSQL(this)
                    val temp  = UserDetails(intent.getStringExtra("event_name"),intent.getStringExtra("startDate"),
                        intent.getStringExtra("event_id"),password)
                    dbObj.insertData(temp)
                    var key = dbref.push().key

                    var auth: FirebaseAuth?=null
                    // Initialize Firebase Auth
                    auth = Firebase.auth
                    val user = auth.currentUser
                    var em:String=""
                    user.let {
                        if (user != null) {
                            em = user.email.toString()
                        }
                    }
                    var uObj = Attendance(password,em,intent.getStringExtra("event_id"),intent.getStringExtra("event_name")
                    ,sharedPref.getString("userVehicle","0").toString(),sharedPref.getString("regnum","Unregistered").toString())
                    dbref.child("UsersAttend").child(key!!).setValue(uObj)//updates the number of records
                    Toast.makeText(baseContext,"You have been invited to the event",Toast.LENGTH_LONG).show()
                }
            prompt.create()
            prompt.show()
        }

    }
}
