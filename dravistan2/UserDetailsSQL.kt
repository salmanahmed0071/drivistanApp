package com.example.dravistan2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

import com.example.dravistan2.EventsClass

class UserDetailsSQL (var ctx : Context) : SQLiteOpenHelper(ctx,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        //create a table
        db?.execSQL("CREATE TABLE EVENT_TABLE (event_name TEXT, date VARCHAR(20),eventID TEXT, password VARCHAR(50))")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("Drop table if exists EVENT_TABLE")
        onCreate(db)
    }

    private var tableName: String = "EVENT_TABLE"
    fun getTableName():String{
        return tableName
    }
    fun insertData(data:UserDetails):Long{
        //method 1 to insert data in the table CARS_TABLE of data base
        var cv : ContentValues = ContentValues()
        cv.put("event_name",data.eventName)
        cv.put("date",data.date)
        cv.put("eventID",data.eventID)
        cv.put("password",data.password)


        var db = writableDatabase
        var res= db.insert("EVENT_TABLE",null,cv)

        return res //returns the total number of records in the database after insertion
    }

    fun readData(): ArrayList<UserDetails>{
        var booklist : ArrayList<UserDetails> = ArrayList<UserDetails>()

        var db = writableDatabase
        var cursor : Cursor? = null

        cursor = db.rawQuery("select * from EVENT_TABLE",null)//gets the data from table in the cursor
        if(cursor!!.moveToFirst())
        {
            var x: Int=0
            while(cursor.isAfterLast == false)//run till the end of table
            {
                var temp : UserDetails = UserDetails(cursor.getString(cursor.getColumnIndex("event_name")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getString(cursor.getColumnIndex("eventID")),
                    cursor.getString(cursor.getColumnIndex("password")))
                //store values to a temporary object

//                Toast.makeText(ctx, "Found: "+cursor.getString(cursor.getColumnIndex("price")), Toast.LENGTH_LONG).show()

                booklist.add(temp)
                // Toast.makeText(ctx,"Found :"+ vehicleList.get(x).getModel(), Toast.LENGTH_LONG).show()

                x=x+1

                cursor.moveToNext()
            }
        }

        return booklist
    }




    companion object{
        //we use these for a better approach of variables which is more secure
        var DATABASE_NAME:String = "Drivistan.db"
        var DATABASE_VERSION:Int = 1
    }
}