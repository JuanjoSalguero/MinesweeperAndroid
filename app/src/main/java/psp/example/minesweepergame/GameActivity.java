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
    final private int TIMER_LENGTH = 999000;

    // ---------------------------------- VARIABLES
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    Levels level;
    TextView smiley, timer, flagsCount;         // Smiley face, timer seconds
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
        smiley = findViewById(R.id.smiley);
        smiley.setOnClickListener(view -> {
            game = new MinesweeperGame(level.getSize(), level.getBombs());
            mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            timerStarted = false;
            countDownTimer.cancel();
            secondsElapsed = 0;
            timer.setText(R.string.default_count);
            flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));
        });

        // Setting timer
        timer = findViewById(R.id.main_timer);
        timerStarted = false;
        // Initialize the countdown timer
        countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            public void onFinish() {
                game.outOfTime();
                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_LONG).show();
                game.getMineGrid().revealAllBombs();
                mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            }
        };

        level = new Levels();

        gridRecyclerView = findViewById(R.id.game_grid);
        gridRecyclerView.setLayoutManager((new GridLayoutManager(this, level.getSize())));
        game = new MinesweeperGame(level.getSize(), level.getBombs());
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(), this, this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
        flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));

        closeGameActivity();
    }

    // ---------------------------------- ON CELL CLICK
    @SuppressLint("DefaultLocale")
    @Override
    public void onCellClick(Cell cell) {
        game.handleCellClick(cell);
        flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));

        if (!timerStarted){
            countDownTimer.start();
            timerStarted = true;
        }

        if (game.getIsGameOver()){
            Toast.makeText(getApplicationContext(), "Game is Over", Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
            countDownTimer.cancel();
        }

        if (game.isGameWon()){
            Toast.makeText(getApplicationContext(), "Game is Won", Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells((game.getMineGrid().getCells()));
    }

    // ---------------------------------- ON CELL LONG CLICK
    @SuppressLint("DefaultLocale")
    @Override
    public void onCellLongClick(Cell cell) {
        game.handleCellLongClick(cell);
        flagsCount.setText(String.format("%03d", game.getNumberOfBombs() - game.getFlagCount()));

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