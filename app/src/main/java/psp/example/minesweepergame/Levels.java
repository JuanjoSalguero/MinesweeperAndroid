package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

public class Levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        closeLevelsActivity();  // Closes levels activity
    }

    // Method to close Levels Activity
    private void closeLevelsActivity(){
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }
}