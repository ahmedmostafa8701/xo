package com.example.xo;

import android.app.*;
import android.os.*;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity
{
	Button cells[][];
	TextView result;
	Board board;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		result = (TextView)findViewById(R.id.winner);
		cells = new Button[][]{
				{findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2)},
				{findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5)},
				{findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8)}
		};
		board = new Board("X");
    }
    public void play(View v){
        for (int i = 0; i < cells.length;i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(cells[i][j] == v){
                    board.play(cells, i, j, result);
                }
            }
        }
    }
	public void retry(View view){
    	board.retry(cells);
		result.setText("");
	}
}

