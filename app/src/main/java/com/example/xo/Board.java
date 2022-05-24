package com.example.xo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Board {
    String selected;
    TextView winner;
    Button [][]cells;

    // each button on the boaed has instance of Press which implement OnClickListner and this action is to set
    // the text x or o depending on my role represented by selected attribute in Board instance
    class Press implements View.OnClickListener {
        int i, j;
        public Press(int i, int j){
            this.i = i;
            this.j = j;
        }
        @Override
        public void onClick(View v) {
            cells[i][j].setText(selected);
            cells[i][j].setEnabled(false);
            if(isWinner(i, j)){
                end(false, "winner is " + selected);
            }
            // program should change text selected after each role
            selected = selected == "X" ? "O" : "X";
        }
    }
    // Create a clean boaed
    public Board(String selected, Button [][]cells, TextView winner) {
        this.selected = selected;
        this.cells = cells;
        this.winner = winner;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setEnabled(true);
                cells[i][j].setText("");
                cells[i][j].setOnClickListener(new Press(i, j));
            }
        }
        winner.setText("");
    }
/*  collect target of x or o to set a winner in each role by iterate the botton clicked now and buttons
    in the rows after iplus and colomn after jplus
*   notice that when iplus or jplus -ve it means that we check buttons before clicked button with target that complete
    the first target, this function is called recursively twice at first move up or right then down or left*/
    public boolean collect(int target, int I, int J, int iplus, int jplus, int c){
        int count = 0;
        for (int i = I, j = J;i != cells.length && i != -1 && j != cells[i].length && j != -1; i+=iplus, j+=jplus) {
            if(cells[i][j].getText().toString() == selected){
                if(++count == target){
                    return  true;
                }
            }
        }
        if(c == 0){
            return collect(target - count, I - iplus, J - jplus, -1 * iplus, -1 * jplus, 1);
        }
        return false;
    }
/*     The player wins when he collect 3 vertically, horizontally or digonally adjacent cells
*      notice that we move vertical by change i only, move horizontally by change j only and move digonally bu changing both*/
    public boolean isWinner(int i, int j){
        return collect(3, i, j, 1, 0, 0) ||
                collect(3, i, j, 0, 1, 0) ||
                collect(3, i, j, 1, 1, 0) ||
                collect(3, i, j, -1, 1, 0);
    }
    public void end(boolean status, String winner){
        this.winner.setText(winner);
        for (Button []row:cells) {
            for (Button cell:row) {
                cell.setEnabled(false);
            }
        }
    }
}