package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tv_selectedDate : TextView? = null
    private var tv_dateNumber : TextView? = null
    private var tv_hourNumber : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.main_activity_btn_datePicker)
        tv_selectedDate = findViewById(R.id.main_activity_textView_selectedDate)
        tv_dateNumber = findViewById(R.id.main_activity_textView_dateNumber)
        tv_hourNumber = findViewById(R.id.main_activity_textView_hourNumber)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_/*view*/, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Year was $selectedYear, month was ${selectedMonth+1}, day is ${selectedDayOfMonth}", Toast.LENGTH_SHORT).show()

                val selectedDate = "${selectedYear}/${selectedMonth+1}/${selectedDayOfMonth}"

                tv_selectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREAN)
                
                val theDate = sdf.parse(selectedDate)

                //theDate에 null이 아니면 실행하도록 let을 함 -> 오류 발생을 방지함(null safety)
                theDate?.let {
                    //선택한 날짜와 1970년 1월 1일 사이에 지난 시간 구하기
                    val selectedDateInMinutes = theDate.time / 60000
                    val selectedDateInHours = theDate.time / 1000
                    //현재 시각을 구함
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val currentDateInHours = currentDate.time / 1000
                        //지금까지 지난 시간을 분 단위로 구함
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tv_dateNumber?.text = differenceInMinutes.toString()

                        //지금까지 지난 시간을 시간 단위로 구함
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        tv_hourNumber?.text = differenceInHours.toString()
                    }
                }
            },
            year,
            month,
            day)
        //어제까지만 선택할 수 잇게 -> 다이얼로그(달력)에서 뒷 날짜는 회색으로 선택 못하게 됨
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000 // 1 hour = 3,600,000ms
        dpd.show()
    }


}