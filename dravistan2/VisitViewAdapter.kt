package com.example.dravistan2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.visit_list_view.view.*


class VisitViewAdapter(var ctx : Context, var list1 : List<UserDetails>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater: LayoutInflater =ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var row: View = inflater.inflate(R.layout.visit_list_view, null)

        row.visit_row.text = list1[position].eventName
        row.visit_row.setOnClickListener()
        {
            var msg = AlertDialog.Builder(ctx)
            msg.setTitle("Information")
            msg.setMessage("Date: "+list1[position].date+"\nUser Passcode: "+list1[position].password)
            msg.setPositiveButton("OK"){
                dialog, which ->  
            }
            msg.create()
            msg.show()
        }

        
        return row
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list1.size
    }
}