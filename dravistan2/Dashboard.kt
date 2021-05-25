package com.example.dravistan2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var dbObj = UserDetailsSQL(this)
        var listdata = dbObj.readData()
        var adpt = VisitViewAdapter(this,listdata)
        visitListView.adapter = adpt
        eventsBtn.setOnClickListener()
        {
            startActivity(Intent(this,EventsList::class.java))
        }

        usdeBtn.setOnClickListener()
        {
            startActivity(Intent(this,UserDetailEntry::class.java))
        }
    }

    companion object{
        val adminCred = "admin@gmail.com"
    }
}
