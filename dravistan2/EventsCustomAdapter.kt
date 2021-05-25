package com.example.dravistan2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


import kotlinx.android.synthetic.main.my_custom_event_list_view.view.*


class EventsCustomAdapter(var ctx : Context, var list1 : List<EventsClass>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater:LayoutInflater=ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var row:View= inflater.inflate(R.layout.my_custom_event_list_view, null)

        row.visit_row.text = list1[position].eventName
        row.joinBtn.setOnClickListener(){
            var newActivity = Intent(ctx,EventDetailsActivity::class.java)
            newActivity.putExtra("event_name",list1[position].eventName)
            newActivity.putExtra("event_id",list1[position].eventID)
            newActivity.putExtra("startDate",list1[position].startdate)
            newActivity.putExtra("endDate",list1[position].enddate)
            newActivity.putExtra("eventDetails",list1[position].eventDetails)
            ctx.startActivity(newActivity)//go the detailed display page
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