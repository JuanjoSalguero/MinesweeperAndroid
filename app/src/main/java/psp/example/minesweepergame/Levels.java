package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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



        goToMainActivity();  // Closes levels activity
    }

    // Method to close Levels Activity
    private void goToMainActivity(){
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view ->
                sizeAndBombs());
    }

    // Method to update size and bombs
    private void sizeAndBombs(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("SizeSelected", getSize());
        intent.putExtra("BombsSelected", getBombs());
        startActivity(intent);
    }

    // Method to select level1
    public void selectLevel_1(View view){
        setSize(8);
        setBombs(15);
        sizeAndBombs();
    }

    // Method to select level2
    public void selectLevel_2(View view){
        setSize(12);
        setBombs(30);
        sizeAndBombs();
    }

    // Method to select level3
    public void selectLevel_3(View view){
        setSize(16);
        setBombs(60);
        sizeAndBombs();
    }
}