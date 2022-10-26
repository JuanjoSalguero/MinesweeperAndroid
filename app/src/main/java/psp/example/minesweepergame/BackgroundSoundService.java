package psp.example.minesweepergame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

// Class created to implement music throughout the application
public class BackgroundSoundService extends Service {

    MediaPlayer player;     // MediaPlayer initialization

    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.music); // Create the music from our mp3 file
        player.setLooping(true); // Set looping
        player.setVolume(100,100);  // Set volume

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return Service.START_STICKY; //(1)
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }
}
