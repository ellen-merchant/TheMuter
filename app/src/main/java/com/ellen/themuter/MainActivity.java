package com.ellen.themuter;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isHome = activeNetwork.getExtraInfo().equals("\"PlusnetWireless405351\"");
        boolean isWork = activeNetwork.getExtraInfo().equals("\"DevPlus\"");

        if (isWork) {
            audioManager.setStreamMute(AudioManager.STREAM_RING, true);
        } else if (isHome) {
            audioManager.setStreamMute(AudioManager.STREAM_RING, false);
            audioManager.setStreamVolume(AudioManager.STREAM_RING, 8, 1);
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_RING, false);
        }
    }
}
