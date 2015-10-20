package com.company.rrv.alarmstartup;

import android.media.MediaPlayer;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private boolean playingSound = false;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Log.v("Sound", "Initializing sounds...");

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.spooky_short);
        mp.setLooping(true);

        play = (Button) this.findViewById(R.id.btnPlaySound);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playingSound) {
                    Log.v("Sound", "Playing sound...");
                    playingSound = true;
                    play.setText("Stop");
                    mp.start();
                }
                else {
                    Log.v("Sound", "Stopping sound...");
                    playingSound = false;
                    play.setText("Play Me!");
                    if (mp.isPlaying()) {
                        mp.pause();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
