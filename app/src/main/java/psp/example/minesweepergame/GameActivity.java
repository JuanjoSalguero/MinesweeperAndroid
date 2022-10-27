package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements OnCellClickListener{

    // ---------------------------------- CONSTANTS
    final private int SIZE = 16;
    final private int BOMBS = 30;
    final private int TIMER_LENGTH = 999000;

    // ---------------------------------- VARIABLES
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView smiley, timer;         // Smiley face, timer seconds
    CountDownTimer countDownTimer;  // Count down
    int secondsElapsed;             // Seconds left
    boolean timerStarted;           // Timer started or not

    // ---------------------------------- ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Restart the game, reset the scores and times
        smiley = findViewById(R.id.smiley);
        smiley.setOnClickListener(view -> {
            game = new MinesweeperGame(SIZE, BOMBS);
            mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            timerStarted = false;
            countDownTimer.cancel();
            secondsElapsed = 0;
            timer.setText(R.string.default_count);
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

        gridRecyclerView = findViewById(R.id.game_grid);
        gridRecyclerView.setLayoutManager((new GridLayoutManager(this, SIZE)));
        game = new MinesweeperGame(SIZE, BOMBS);
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(), this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
    }

    // ---------------------------------- ON CELL CLICK
    @Override
    public void onCellClick(Cell cell) {
        game.handleCellClick(cell);

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
}