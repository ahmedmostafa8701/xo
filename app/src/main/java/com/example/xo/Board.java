package com.example.xo;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Board {
    String selected;
    String winner;
    public Board(String selected) {
        this.selected = selected;
    }
    public void play(Button cells[][], int i, int j, TextView result){
        cells[i][j].setText(selected);
        cells[i][j].setEnabled(false);
        winner = selected;
        if(isWinner(cells, i, j)){
            finish(cells);
            result.setText("Winner is " + selected);
        }
        selected = selected == "X" ? "O" : "X";
    }
    public boolean collect(Button cells[][], int target, int I, int J, int iplus, int jplus, int c){
        int count = 0;
        int i = I, j = J;
        while(i != cells.length && i != -1 && j != cells[i].length && j != -1){
            if(cells[i][j].getText().toString() == selected){
                if(++count == target){
                    return  true;
                }
            }
            j += jplus;
            i += iplus;
        }
        if(c == 0){
            return collect(cells, target - count, I - iplus, J - jplus, -1 * iplus, -1 * jplus, 1);
        }
        return false;
    }
    public boolean isWinner(Button cells[][], int i, int j){
        return collect(cells, 3, i, j, 1, 0, 0) ||
                collect(cells, 3, i, j, 0, 1, 0) ||
                collect(cells, 3, i, j, 1, 1, 0) ||
                collect(cells, 3, i, j, -1, 1, 0);
    }
    public void finish(Button cells[][]){
        for (Button row[]:cells) {
            for (Button cell:row) {
                cell.setEnabled(false);
            }
        }
    }
    public void retry(Button cells[][]){
        for (Button rows[]:cells) {
            for (Button cell:rows) {
                cell.setText("");
                cell.setEnabled(true);
            }
        }
        selected = "X";
        winner = null;
    }
    public String getWinner(){
        return winner;
    }
}