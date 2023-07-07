package com.sobhanmp.domain.util

import java.text.SimpleDateFormat
import java.util.Date

object DateUtil {

    //May 21,2020
    fun getTodayDate(): String{
        val date = Date()
        val dateFormat = SimpleDateFormat("MMMM dd,YYYY")
        return dateFormat.format(date.time)
    }
}