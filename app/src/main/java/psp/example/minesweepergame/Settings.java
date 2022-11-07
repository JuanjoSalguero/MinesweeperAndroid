package psp.example.minesweepergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //muteAndUnmute();    // To mute and unmute the sound (not implemented)
        aboutButton();      // To open about information
        rulesButton();      // To open rules information
        closeSettingsActivity();  // Closes Settings activity
    }

    // Method to close Setting activity
    private void closeSettingsActivity(){
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }

    // Method to mute or unmuted the sound
    // Method implemented but no working because there is no background music
    /*private void muteAndUnmute(){
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
    }*/

    // Method to acced the about information
    private void aboutButton(){
        Button aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(view ->
                alertDialog("Minesweeper Android game" +
                " designed and developed by Juanjo Salguero ®️."));
    }

    // Method to acced the rules information
    private void rulesButton(){
        Button rulesButton = findViewById(R.id.rules_button);
        rulesButton.setOnClickListener(view ->
                alertDialog("\uD83C\uDFAE Minesweeper Android Game \uD83D\uDD79\n" +
                        "\nMinesweeper for Android lets you\n" +
                        "play the classic logic game where\n" +
                        "you have to use your wit to clean\n" +
                        "mines from gameboard.\n" +
                        "\nGame is played in mine mode,\n" +
                        "where you click to open tiles. Use\n" +
                        "long-click to mark flags \uD83D\uDEA9 where you\n" +
                        "there is a bomb \uD83D\uDCA3.\n" +
                        "\n\uD83D\uDDE8Tip: Try to press open tile where\n" +
                        "number of flags around tile equals\n" +
                        "number of tile! It is by far the\n" +
                        "fastest way to play the game. And\n" +
                        "to hit the highest score.\n" +
                        "\n\uD83C\uDD99Levels:\n" +
                        "- Easy: 8 x 8 with 15 bombs.\n" +
                        "- Medium: 12 x 12 with 30 bombs.\n" +
                        "- Hard: 16 x 16 with 60 bombs.\n" +
                        "\n\uD83C\uDFC6Victory:\nAll tiles without bombs\n" +
                        "hav been unlocked.\n" +
                        "\nGood Luck \uD83D\uDE42"));
    }

    // Method to confirm exit the app
    private void alertDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setPositiveButton("Close", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}