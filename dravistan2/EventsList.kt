package com.example.dravistan2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_events_list.*


class EventsList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        var list = ArrayList<EventsClass>()
        var myRef = Firebase.database.reference
        var ct :Int =0
        myRef.child("Events").addValueEventListener(object :  ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(i in snapshot.children)
                    {
                        var obj = i.getValue(EventsClass::class.java)!!
//                        Toast.makeText(baseContext,"$ct Data \n Name: "+obj.eventName,Toast.LENGTH_SHORT)
//                            .show()
                        list.add(obj)
                        ct+=1
                    }
                }
            }

        })



        var eadpt = EventsCustomAdapter(this,list)
        eventList.adapter = eadpt

    }
}
