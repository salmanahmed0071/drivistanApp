package com.example.dravistan2.ui.login

class Attendance {

    var uid:String=""
    var uemail:String=""
    var eventID=""
    var eventName=""
    var vehicle=""
    var regnum=""

    constructor(userid:String,useremail:String,eid:String,ename:String,car:String,reg:String)
    {

        uid=userid
        uemail=useremail
        eventID=eid
        eventName=ename
        vehicle=car
        regnum=reg
    }

}