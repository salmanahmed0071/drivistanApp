package com.example.dravistan2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_detail_entry.*

class UserDetailEntry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail_entry)
        supportActionBar!!.title="Go Back To Dashboard"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //back button set on top bar
        var sharedPref = getSharedPreferences("userdetails",Context.MODE_PRIVATE)
        var editor = sharedPref.edit()

        doneBtn.setOnClickListener()
        {
            editor.putString("PhoneNum",input_phone.text.toString())
            editor.putString("userVehicle",input_vehicle.text.toString())
            editor.putString("regnum",input_reg.text.toString())
            editor.apply()
            editor.commit()
            Toast.makeText(baseContext,"User phone number updated!",Toast.LENGTH_SHORT)
                .show()
        }
    }
}
