package psp.example.minesweepergame;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MineGridRecyclerAdapter extends RecyclerView.Adapter<MineGridRecyclerAdapter.MineTileViewHolder> {

    // ---------------------------------- VARIABLES
    private List<Cell> cells;                       // List of cells
    private OnCellClickListener listener;           // On cell click listener
    private OnCellLongClickListener longListener;

    // ---------------------------------- CONSTRUCTOR
    public MineGridRecyclerAdapter(List<Cell> cells, OnCellClickListener listener, OnCellLongClickListener longListener) {
        this.cells = cells;
        this.listener = listener;
        this.longListener = longListener;
    }

    @NonNull
    @Override
    public MineTileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new MineTileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MineTileViewHolder holder, int position) {
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
        notifyDataSetChanged();
    }

    class MineTileViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;

        public MineTileViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.item_cell_value);
        }

        public void bind(final Cell cell) {
            itemView.setBackgroundColor(Color.WHITE);

            itemView.setOnClickListener(view -> listener.onCellClick(cell));
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longListener.onCellLongClick(cell);
                    return true;
                }
            });

            if(cell.getIsRevealed()){
                // Display the value inside the textView (1, 2, 3, bombs, blanks...) for that tile int he grid
                if (cell.getValue() == Cell.BOMB) {
                    valueTextView.setText(R.string.bomb);
                    valueTextView.setTextColor(Color.rgb(97, 97, 97));
                    itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                } else if (cell.getValue() == Cell.BLANK) {
                    valueTextView.setText("");
                    itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                } else {
                    // Depending on the numbers
                    valueTextView.setText(String.valueOf(cell.getValue()));
                    if (cell.getValue() == 1) {
                        valueTextView.setTextColor(Color.rgb(0, 1, 253));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 2) {
                        valueTextView.setTextColor(Color.rgb(1, 126, 0));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 3) {
                        valueTextView.setTextColor(Color.rgb(254, 0, 0));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 4) {
                        valueTextView.setTextColor(Color.rgb(1, 1, 127));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 5) {
                        valueTextView.setTextColor(Color.rgb(129, 2, 1));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 6) {
                        valueTextView.setTextColor(Color.rgb(0, 128, 128));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 7) {
                        valueTextView.setTextColor(Color.rgb(0, 0, 0));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    } else if (cell.getValue() == 8) {
                        valueTextView.setTextColor(Color.rgb(128, 128, 128));
                        itemView.setBackgroundColor(Color.rgb(192, 192, 192));
                    }
                }
            }else if (cell.getIsFlagged()){
                valueTextView.setText(R.string.flag_box);
                valueTextView.setTextColor(Color.rgb(97, 97, 97));
            }
        }
    }
}