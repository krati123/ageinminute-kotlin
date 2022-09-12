package com.example.dobcalculation

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView?=null
    private var tvAgeInMinutes: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDate:Button=findViewById(R.id.btnDatePicker)

        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)

        btnDate.setOnClickListener{
            clickDatePicker()

        }


    }
     private fun clickDatePicker(){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{_,Selectedyear,Selectedmonth,SelecteddayOfMonth->
            Toast.makeText(this,"Year was $Selectedyear, month was ${Selectedmonth+1}, day of month" +
                    "$SelecteddayOfMonth",Toast.LENGTH_LONG).show()

            val SelectedDate ="$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"
            tvSelectedDate?.setText(SelectedDate)

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(SelectedDate)

            //for minutes
            theDate?.let {
                val selectedDateInMinute = theDate.time/60000  //getTime()=time

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes=currentDate.time/60000
                    val differenceInMinutes=currentDateInMinutes- selectedDateInMinute
                    tvAgeInMinutes?.text=differenceInMinutes.toString()

                }

            }

        },
        year,
        month,
        day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
      dpd.show()
    }
}