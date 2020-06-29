package com.casper.agebytheminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{view->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"The chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth"
                , Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            textViewSelectedDate.setText(selectedDate)

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = simpleDateFormat.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time/60000

            val currentDate =simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

            val currentDateToMinutes = currentDate!!.time/60000

            val differenceInMinutes=currentDateToMinutes-selectedDateInMinutes

            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
        }
            ,year
            ,month
            ,day).show()

    }
}