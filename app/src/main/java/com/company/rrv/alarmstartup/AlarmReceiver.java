package com.company.rrv.alarmstartup;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Nick on 10/20/2015.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        HomePage inst = HomePage.instance();
        inst.setAlarmText("Alarm! Wake up! Wake up!");

        if (!inst.isAlarmOn) {
            try {
                AssetFileDescriptor afd = context.getResources().openRawResourceFd(R.raw.spooky_short);
                inst.mp.reset();
                inst.mp.setAudioStreamType(AudioManager.STREAM_ALARM);
                inst.mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                inst.mp.setLooping(true);
                inst.mp.prepare();
                Log.d("Alarm Receiver", "Playing sound...");
                if (inst.mp.isPlaying()) {
                    inst.mp.stop();
                    inst.mp.start();
                } else {
                    inst.mp.start();
                }
                afd.close();
                inst.isAlarmOn = true;
            } catch (Exception e) {
                Log.d("Alarm Receiver", "Error occurred while playing sound.");
            } finally {

            }

            //this will send a notification message
            ComponentName comp = new ComponentName(context.getPackageName(),
                    HomePage.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);
        }
    }


}
