package com.example.xo;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.widget.AutoCompleteTextView.*;
import android.content.*;
public class MainActivity extends Activity implements OnClickListener
{
	Button btn[] = new Button[9];
	Button retry ;
	TextView txt;
	String row[] = {"","",""};
	String colomn[] = {"","",""};
	int role=1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		retry = (Button)findViewById(R.id.retry);
		retry.setOnClickListener(MainActivity.this);
		txt = (TextView)findViewById(R.id.winner);
		for(int i=R.id.btn0 ,j=0;i<=R.id.btn8;i++ ,j++)
		{
				btn[j]=(Button)findViewById(i);
				btn[j].setOnClickListener(MainActivity.this);
		}
    }

	@Override
	public void onClick(View p1)
	{
		if (p1==retry)
		{
			Intent in = new Intent(MainActivity.this,MainActivity.class);
			startActivity(in);
	        finish();
		}
		for(int i = 0;i < 9;i++)
		{
			if(p1==btn[i])
			{
				if (role%2 != 0)
				{
					btn[i].setText("x");
					row[i/3]+="x";
					colomn[i%3]+="x";
				    if (checkWin(row ,colomn ,"xxx" ,i ,btn))
					{
						finishGame(btn,txt,"x");
					}
				}
				else
				{
					btn[i].setText("o");
					row[i/3]+="o";
					colomn[i%3]+="o";	
					if (checkWin(row ,colomn ,"ooo" ,i ,btn))
					{
						finishGame(btn,txt,"o");
					}
				}
				btn[i].setEnabled(false);
				role++;
			}
		}
	    
	}
	public static String getTxt (Button btn)
	{
		return btn.getText().toString();
	}
	public static boolean positions(int i ,int j,int k ,String xo,Button btn[])
	{
		if ((getTxt(btn[i])+getTxt(btn[j])+getTxt(btn[k])).equals(xo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean checkWin (String row[] ,String colomn[] ,String xo ,int key ,Button btn[])
	{
		if (row[key/3].equals(xo) || colomn[key%3].equals(xo))
		{
			return true;
		}
		else if (positions(0,4,8,xo,btn) || positions(2,4,6,xo,btn))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static void finishGame(Button [] btn,TextView txt,String xo)
	{
		for(int i = 0 ;i < 9 ;i++)
		{
			btn[i].setEnabled(false);
		}
		txt.setText("winner is "+xo);
	}
}

