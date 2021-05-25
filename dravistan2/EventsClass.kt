package com.example.dravistan2

class EventsClass {

     var eventName : String = ""
     var startdate =""
     var enddate = ""
     var eventID =""
     var eventDetails = ""


    constructor( eventId : String, eventname :String, start_Date:String,  end_Date:String,  edetails: String) {
          eventName = eventname
          startdate =start_Date
          enddate = end_Date
          eventID =eventId
          eventDetails = edetails
    }
    constructor(){

    }

}