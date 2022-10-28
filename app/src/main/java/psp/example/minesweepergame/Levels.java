package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Levels extends AppCompatActivity {

    // ---------------------------------- VARIABLES
    private int size;       // Grid size
    private int bombs;      // Number of bombs

    // ---------------------------------- CONSTRUCTOR
    public Levels(){
        this.size = 12;
        this.bombs = 30;
    }

    // ---------------------------------- GETTERS / SETTERS
    // size getter
    public int getSize() {
        return size;
    }

    // size setter
    public void setSize(int size) {
        this.size = size;
    }

    // bombs getter
    public int getBombs() {
        return bombs;
    }

    // bombs setter
    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    // ---------------------------------- METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        closeLevelsActivity();  // Closes levels activity
        selectLevel_1();
        selectLevel_2();
        selectLevel_3();
    }

    // Method to close Levels Activity
    private void closeLevelsActivity(){
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }

    // Method to select level1
    private void selectLevel_1(){
        Button levelsButton = findViewById(R.id.level_1);
        levelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSize(8);
                setBombs(15);
            }
        });

    }

    // Method to select level2
    private void selectLevel_2(){
        Button levelsButton = findViewById(R.id.level_1);
        levelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSize(12);
                setBombs(30);
            }
        });

    }

    // Method to select level3
    private void selectLevel_3(){
        Button levelsButton = findViewById(R.id.level_1);
        levelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSize(16);
                setBombs(60);
            }
        });

    }
}