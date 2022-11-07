package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements OnCellClickListener, OnCellLongClickListener {

    // ---------------------------------- CONSTANTS
    final private int TIMER_LENGTH = 999000;    // Max time to complete the game

    // ---------------------------------- VARIABLES
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView restartIcon, timer, flagsCount; // Restart icon, timer seconds, flags count
    CountDownTimer countDownTimer;  // Count down
    int secondsElapsed;             // Seconds left
    boolean timerStarted;           // Timer started or not

    // ---------------------------------- ON CREATE
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        flagsCount = findViewById(R.id.flags_left);

        // Restart the game, reset the scores and times
        restartIcon = findViewById(R.id.restartIcon);
        restartIcon.setOnClickListener(view -> { // On click, reset al game stats
            game = new MinesweeperGame(Levels.getSize(), Levels.getBombs());
            mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            timerStarted = false;
            countDownTimer.cancel();
            secondsElapsed = 0;
            timer.setText(R.string.default_count);
            if (!flagsCount.getText().equals("000")){   // If flags > 0, subtract
                // Number of bombs - flags count
                flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));
            }
        });

        // Setting timer
        timer = findViewById(R.id.main_timer);
        timerStarted = false;
        // Initialize the countdown timer                   // Second by second
        countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1; // Seconds elapse +1
                timer.setText(String.format("%03d", secondsElapsed));
            }

            // Method to finish the game
            public void onFinish() {
                game.outOfTime(); // Time expired, game over.
                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_LONG).show();
                game.getMineGrid().revealAllBombs();    // Reveal game bombs
                mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            }
        };

        gridRecyclerView = findViewById(R.id.game_grid);
        // Setting the grid layout with the size given from Levels size
        gridRecyclerView.setLayoutManager((new GridLayoutManager(this, Levels.getSize())));
        // Getting size and bombs
        game = new MinesweeperGame(Levels.getSize(), Levels.getBombs());
        // On click and On long click functions
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(), this, this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
        // Number of bombs - flags count
        flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));

        closeGameActivity();    // Close game activity
    }

    // ---------------------------------- ON CELL CLICK
    @SuppressLint("DefaultLocale")
    @Override
    public void onCellClick(Cell cell) {
        game.handleCellClick(cell);
        if (!flagsCount.getText().equals("000")){  // If flags > 0, subtract
            // Number of bombs - flags count
            flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));
        }

        if (!timerStarted){ // If time started = false
            countDownTimer.start(); // Start countdown
            timerStarted = true;
        }

        if (game.getIsGameOver()){  // If game over = true
            // Game over message
            Toast.makeText(getApplicationContext(), "Game is Over", Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();        // Reveal all bombs
            countDownTimer.cancel();    // Cancel de countdown timer
        }

        if (game.isGameWon()){  // If game won = true
            // Game won message
            Toast.makeText(getApplicationContext(), "Game is Won", Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();    // Reveal all bombs
            countDownTimer.cancel();    // Cancel de countdown timer
        }

        mineGridRecyclerAdapter.setCells((game.getMineGrid().getCells()));
    }

    // ---------------------------------- ON CELL LONG CLICK
    // This method has the same functionality as onCellClick but onCellLongClick
    @SuppressLint("DefaultLocale")
    @Override
    public void onCellLongClick(Cell cell) {
        game.handleCellLongClick(cell);
        if (!flagsCount.getText().equals("000")){
            flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));
        }

        if (!timerStarted){
            countDownTimer.start();
            timerStarted = true;
        }

        if (game.getIsGameOver()){
            Toast.makeText(getApplicationContext(), "Game is Over", Toast.LENGTH_LONG).show();
            game.getMineGrid().revealAllBombs();
            countDownTimer.cancel();
        }

        if (game.isGameWon()){
            Toast.makeText(getApplicationContext(), "Game is Won", Toast.LENGTH_LONG).show();
            game.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells((game.getMineGrid().getCells()));
    }

    // Method to close Game Activity
    private void closeGameActivity(){
        ImageButton leaveButton = findViewById(R.id.home_menu_button);
        leaveButton.setOnClickListener(view -> finish());
    }
}