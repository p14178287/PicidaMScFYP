package com.example.shingiraimarikasi.PicidaMScFYP.controller.dataModel

import java.util.*

class SymptomDataActivity {
//    inner class Ailment {
        var title: String? = null
            internal set
        var notes: String? = null
            internal set
        var date: String? = null
            internal set
        var pushId: String? = null

        constructor() {}

        constructor(title: String, notes: String) {
            this.title = title
            this.notes = notes

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR).toString() + ""
            val month = (cal.get(Calendar.MONTH) + 1).toString() + ""
            val day = cal.get(Calendar.DAY_OF_MONTH).toString() + ""

            this.date = "$month / $day / $year"
        }
//    }
}
