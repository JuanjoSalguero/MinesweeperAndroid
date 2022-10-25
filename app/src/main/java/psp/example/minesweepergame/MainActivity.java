package psp.example.minesweepergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openSettingsActivity(); // Opens Settings Activity
        openLevelsActivity();   // Opens Levels Activity
        exitApp();             // Exit the application
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
        exitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                exitButtonPressed();
            }
        });
    }

    // Method to confirm exit the app
    private void exitButtonPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit Minesweeper?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}