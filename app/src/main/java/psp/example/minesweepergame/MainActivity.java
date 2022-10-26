package psp.example.minesweepergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //MediaPlayer mySong; // Background sound

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Plays background music
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);

        openSettingsActivity(); // Opens Settings Activity
        openLevelsActivity();   // Opens Levels Activity
        exitApp();              // Exit the application
    }

    // Method to switch to Settings activity
    private void openSettingsActivity(){
        ImageButton settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, Settings.class)));
    }

    // Method to switch to Levels activity
    private void openLevelsActivity(){
        Button levelsButton = findViewById(R.id.levels_button);
        levelsButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, Levels.class)));
    }

    // Method to exit the application
    private void exitApp(){
        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(view -> exitButtonPressed());
    }

    // Method to confirm exit the app
    private void exitButtonPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            // Message to show in the dialog
        builder.setMessage("Are you sure you want to exit Minesweeper?")
                .setCancelable(false)
                // Yes option, close de app
                .setPositiveButton("Yes", (dialogInterface, i) -> finish())
                // No option, closes de dialog
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
