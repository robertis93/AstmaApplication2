package space.robert.astmaapplication2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.core.content.ContentProviderCompat.requireContext
import space.robert.astmaapplication2.fragments.AddFragment

class MyBroadcastReceiver :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var mp = MediaPlayer.create(context, R.raw.alarm_voice)
        mp.start()
        }
    }
