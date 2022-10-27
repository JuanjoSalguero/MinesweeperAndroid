package psp.example.minesweepergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements OnCellClickListener{

    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView smiley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Restart the game
        smiley = findViewById(R.id.smiley);
        smiley.setOnClickListener(view -> {
            game = new MinesweeperGame(8, 10);
            mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
        });

        gridRecyclerView = findViewById(R.id.game_grid);
        gridRecyclerView.setLayoutManager((new GridLayoutManager(this, 8)));
        game = new MinesweeperGame(8, 10);
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(), this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
    }

    @Override
    public void onCellClick(Cell cell) {

    }
}