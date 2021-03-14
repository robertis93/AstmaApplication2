package space.robert.astmaapplication2.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import space.robert.astmaapplication2.MyBroadcastReceiver
import space.robert.astmaapplication2.R
import space.robert.astmaapplication2.convertDayOfMounth
import space.robert.astmaapplication2.model.Measure
import space.robert.astmaapplication2.viewmodel.MeasureViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class AddFragment : Fragment() {
    val dateTime = LocalDateTime.now()

    //val dateTime = LocalDateTime.now()

    var dayWeek = Calendar.getInstance()
    private lateinit var mMeasureViewModel: MeasureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMeasureViewModel = ViewModelProvider(this).get(MeasureViewModel::class.java)
        view.add_button.setOnClickListener {
            insertDataToDatabase()




//            var sec = editTextTime8.text.toString().toInt()
//            var i = Intent(context?.applicationContext, MyBroadcastReceiver::class.java)
//            var pi = PendingIntent.getBroadcast(context?.applicationContext, 111, i, 0)
//            var am : AlarmManager? = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//            if (am != null) {
//                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (sec*1000), pi)
//            }
//            Toast.makeText(requireContext().applicationContext, "Alarm set for $sec Seconds", Toast.LENGTH_SHORT).show()
        }


        val textViewDate = getView()?.findViewById<TextView>(R.id.text_date)
        if (textViewDate != null) {
            textViewDate.setText(dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))
                .toString()
        }
        val textViewTime = getView()?.findViewById<TextView>(R.id.text_time)
        if (textViewTime != null) {
            textViewTime.setText(dateTime.format(DateTimeFormatter.ofPattern("H:m")))
                .toString()
        }

        val weekOfDay = getView()?.findViewById<TextView>(R.id.text_day_week)
        if (weekOfDay != null) {

            weekOfDay.setText((convertDayOfMounth(dayWeek.get(Calendar.DAY_OF_WEEK)).toString()))


        }
    }


    private fun insertDataToDatabase() {
        val dateOfMeasure =
            dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)).toString()
        val morningM = measureMorning.text
        val dayM = measureDay.text
        val eveningM = measureEvening.text
        val firstTime = editTextTime8.text
        val secondTime = editTextTime9.text
        val thirdTime = editTextTime10.text


        if (inputCheck(morningM, dayM, eveningM)) {
            val measure = Measure(
                0,
                dateOfMeasure.toString(),
                Integer.parseInt(morningM.toString()),
                Integer.parseInt(dayM.toString()),
                Integer.parseInt(eveningM.toString()),
                firstTime.toString(),
                secondTime.toString(),
                thirdTime.toString()
            )
            mMeasureViewModel.addMeasure(measure)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill  out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(morningM: Editable, dayM: Editable, evening: Editable): Boolean {
        return !(morningM.isEmpty() && dayM.isEmpty() && evening.isEmpty())
    }


}