package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        muteAndUnmute();
        closeSettingsActivity();  // Closes Settings activity
    }

    // Method to close Setting activity
    private void closeSettingsActivity(){
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }

    // Method to mute or unmuted the sound
    private void muteAndUnmute(){
        // Declare buttons
        ImageButton muteButton = findViewById(R.id.sound_off_button);
        ImageButton unmuteButton = findViewById(R.id.sound_on_button);

        // Declare an audio manager
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        muteButton.setOnClickListener(view -> {
            // Does the stream music true(mute)
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            // Message that appears when the mute button is pressed
            Toast muteToast = Toast.makeText(getApplicationContext(),
                            "Sound muted", Toast.LENGTH_SHORT);
            muteToast.show();

        });

        unmuteButton.setOnClickListener(view -> {
            // Does the stream music false(unmute)
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            // Message that appears when the button is pressed
            Toast unmuteToast = Toast.makeText(getApplicationContext(),
                    "Sound unmuted", Toast.LENGTH_SHORT);
            unmuteToast.show();
        });
    }
}