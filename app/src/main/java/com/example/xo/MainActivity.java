package com.example.xo;

import android.app.*;
import android.os.*;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity
{
	Button [][]cells;
	TextView result;
	Board board;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		result = (TextView)findViewById(R.id.winner);
		// each cell represents button in the board
		cells = new Button[][]{
				{findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2)},
				{findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5)},
				{findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8)}
		};
		// create new board for playing
		board = new Board("X", cells, result);
    }
    // The listenter of the retry button
	public void retry(View view){
        board = new Board("X", cells, result);
	}
}

