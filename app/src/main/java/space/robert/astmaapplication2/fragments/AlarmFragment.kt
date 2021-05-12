package space.robert.astmaapplication2.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import space.robert.astmaapplication2.MyBroadcastReceiver
import space.robert.astmaapplication2.R
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*


class AlarmFragment : Fragment()

{

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btn_first_time.setOnClickListener {
            val calndarFirst = Calendar.getInstance()

            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hour, minute ->
                    calndarFirst.set(Calendar.HOUR_OF_DAY, hour)
                    calndarFirst.set(Calendar.MINUTE, minute)
                   var  sdf = SimpleDateFormat("HH:mm").format(calndarFirst.time)
                    textview_first_time.text = sdf

                    // Set the alarm to start at 8:30 a.m.
                    //        var sec = editTextAlarmTimeFirst.text.toString().toInt()
                    //A PendingIntent указывает действие, которое необходимо предпринять в будущем.
                    //С помощью AlarmManager вы можете запланировать выполнение кода в будущем.
                    alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmIntent = Intent(context, MyBroadcastReceiver::class.java).let { intent ->
                        PendingIntent.getBroadcast(context, 11, intent, FLAG_UPDATE_CURRENT)
                    }

                    var timeHourFirst = sdf.substringBefore(':')
                    var timeMinuteFirst = sdf.substringAfter(':')
                    val calendarSecond: Calendar = Calendar.getInstance().apply {
                        timeInMillis = System.currentTimeMillis()
                        set(Calendar.HOUR_OF_DAY, timeHourFirst.toInt())
                        set(Calendar.MINUTE, timeMinuteFirst.toInt())

                    }

                    alarmMgr?.set(
                        AlarmManager.RTC_WAKEUP,
                        calendarSecond.timeInMillis,
                        alarmIntent
                    )

//

                }
//создание timepickerdialog
                    // timeSetListener, метод которого срабатывает при нажатии кнопки ОК на диалоге

            TimePickerDialog(
                context,
                timeSetListener,
                calndarFirst.get(Calendar.HOUR_OF_DAY),
                calndarFirst.get(Calendar.MINUTE),
                true
            ).show()


        }

        // val tdtf =  textview_first_time.text.toString()


        // alarmMgr.nextAlarmClock.showIntent


        view.btn_second_time.setOnClickListener {

            val calendarSecond = Calendar.getInstance()
            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hour, minute ->
                    calendarSecond.set(Calendar.HOUR_OF_DAY, hour)
                    calendarSecond.set(Calendar.MINUTE, minute)
                    var sdfS =  SimpleDateFormat("HH:mm").format(calendarSecond.time)
                    textview_second_time.text =sdfS

                    alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmIntent = Intent(context, MyBroadcastReceiver::class.java).let { intent ->
                        PendingIntent.getBroadcast(context, 22, intent, 0)
                    }

                    var  timeHourSecond = sdfS.substringBefore(':')
                    var timeMinutSecond = sdfS.substringAfter(':')
                    val calendar: Calendar = Calendar.getInstance().apply {
                        timeInMillis = System.currentTimeMillis()
                        set(Calendar.HOUR_OF_DAY, timeHourSecond.toInt())
                        set(Calendar.MINUTE, timeMinutSecond.toInt())

//            set(Calendar.HOUR_OF_DAY, timeHourSecond.toInt())
//            set(Calendar.MINUTE, timeMinutSecond.toInt() )
                    }

                    alarmMgr?.set(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        alarmIntent
                    )
                }

            val timePickerDialog = TimePickerDialog(
                context,
                timeSetListener,
                calendarSecond.get(Calendar.HOUR_OF_DAY),
                calendarSecond.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()


        }


        view.btn_third_time.setOnClickListener {

            val calendarThird = Calendar.getInstance()
            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hour, minute ->
                    calendarThird.set(Calendar.HOUR_OF_DAY, hour)
                    calendarThird.set(Calendar.MINUTE, minute)


                    var sdfTh = SimpleDateFormat("HH:mm").format(calendarThird.time)
                    textview_third_time.text =sdfTh

                    alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmIntent = Intent(context, MyBroadcastReceiver::class.java).let { intent ->
                        PendingIntent.getBroadcast(context, 33, intent, 0)
                    }

                    var timeHourThird = (sdfTh).substringBefore(':')
                    var timeMinutThird = (sdfTh).substringAfter(':')
                    val calendarThird: Calendar = Calendar.getInstance().apply {
                        timeInMillis = System.currentTimeMillis()
                        set(Calendar.HOUR_OF_DAY, timeHourThird.toInt())
                        set(Calendar.MINUTE, timeMinutThird.toInt())
                    }

                    alarmMgr?.set(
                        AlarmManager.RTC_WAKEUP,
                        calendarThird.timeInMillis,
                        alarmIntent
                    )
                }

            TimePickerDialog(
                context,
                timeSetListener,
                calendarThird.get(Calendar.HOUR_OF_DAY),
                calendarThird.get(Calendar.MINUTE),
                true
            ).show()


        }

        // Set the alarm to start at 8:30 a.m.
        //        var sec = editTextAlarmTimeFirst.text.toString().toInt()


        view.btn_saved_time.setOnClickListener {
            alarmMgr?.cancel(alarmIntent)
        }
    }

//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        TODO("Not yet implemented")
//    }


}
