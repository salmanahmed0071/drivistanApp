package com.example.dravistan2


import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FirebaseDBHelper_Events(var ctx : Context){

    var total: Int =0
    var list_events : ArrayList<EventsClass> = ArrayList()

//   fun getNumOfRecords(){
//       var firedatabase= Firebase.database
//       var myRef: DatabaseReference = firedatabase.getReference("RecordID/")
//       val changeListner = object :ValueEventListener{
//           override fun onDataChange(snapshot: DataSnapshot) {
//               total=snapshot.getValue(String::class.java).toString().toInt()
//               Toast.makeText(ctx,"Total records : $total",Toast.LENGTH_LONG)
//                   .show()
//
//           }
//
//           override fun onCancelled(error: DatabaseError) {
//               Toast.makeText(ctx,"$error",Toast.LENGTH_LONG)
//                   .show()
//           }
//       }
//       myRef.addValueEventListener(changeListner)
//
//   }

    fun readEvents(){
        var firedatabase=FirebaseDatabase.getInstance()
        var count=0


            var myRef: DatabaseReference = firedatabase.getReference("Events")

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(ctx,"$error",Toast.LENGTH_LONG)
                        .show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    list_events.clear()
                    for (i in snapshot.children)
                    {
                        var data:EventsClass = i.getValue(EventsClass::class.java)!!
                        Toast.makeText(
                            ctx,
                            "Object " + (count + 1) + "\nEvent: " + data.eventName + "\nStartDate: " + data.startdate,
                            Toast.LENGTH_SHORT
                        ).show()
                        list_events.add(data)
                        count+=1
                    }
                }
            })


    }

    fun getEventsList():ArrayList<EventsClass>{
        return list_events
    }
}