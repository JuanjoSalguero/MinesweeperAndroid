package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton settingsButton;
    private Button levelsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Settings button
        settingsButton = (ImageButton) findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view -> openSettingsActivity());

        // Levels button
        levelsButton = (Button) findViewById(R.id.levels_button);
        levelsButton.setOnClickListener(view -> openLevelsActivity());

    }
    // Method to open settings activity
    public void openSettingsActivity(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    // Method to open settings activity
    public void openLevelsActivity(){
        Intent intent = new Intent(this, Levels.class);
        startActivity(intent);
    }

}