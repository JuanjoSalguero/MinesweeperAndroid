package psp.example.minesweepergame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import android.widget.Toast;

public class MyService extends Service {
    static MediaPlayer myPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        myPlayer = MediaPlayer.create(this,R.raw.soundtrack);
        //Setting loop play to true
        //This will make the ringtone continuously playing
        myPlayer.setLooping(false); // Set looping
    }
    @Override
    public void onStart(Intent intent, int startid) {
        myPlayer.start();
    }
    @Override
    public void onDestroy() {
        myPlayer.stop();
    }
}
