package greenchair.apps.lotterymanager;

import java.util.Vector;

import android.content.Intent;

// Do not modify 

public class BallSet
{

	public static final String ITEM_SEP = ",";

	public final static String BALL1 = "ball1";
	public final static String BALL2 = "ball2";
	public final static String BALL3 = "ball3";
	public final static String BALL4 = "ball4";
	public final static String BALL5 = "ball5";
	public final static String MEGABALL = "megaball";

	private int mBall1, mBall2, mBall3, mBall4, mBall5, mMegaBall = 0;

	BallSet(int ball1, int ball2, int ball3, int ball4, int ball5, int megaball)
	{
		this.mBall1 = ball1;
		this.mBall2 = ball2;
		this.mBall3 = ball3;
		this.mBall4 = ball4;
		this.mBall5 = ball5;
		this.mMegaBall = megaball;
	}

	// Create a new ToDoItem from data packaged in an Intent

	BallSet(Intent intent)
	{

		mBall1 = intent.getIntExtra(BALL1, 0);
		mBall2 = intent.getIntExtra(BALL2, 0);
		mBall3 = intent.getIntExtra(BALL3, 0);
		mBall4 = intent.getIntExtra(BALL4, 0);
		mBall5 = intent.getIntExtra(BALL5, 0);
		mMegaBall = intent.getIntExtra(MEGABALL, 0);
	}


	// Take a set of String data values and
	// package them for transport in an Intent

	public static void packageIntent(Intent intent, int ball1, int ball2,
			int ball3, int ball4, int ball5, int megaBall)
	{
		intent.putExtra(BallSet.BALL1, ball1);
		intent.putExtra(BallSet.BALL2, ball2);
		intent.putExtra(BallSet.BALL3, ball3);
		intent.putExtra(BallSet.BALL4, ball4);
		intent.putExtra(BallSet.BALL5, ball5);
		intent.putExtra(BallSet.MEGABALL, megaBall);
	}
	
	public int getBall(int n)
	{
		switch (n)
		{
			case 1:		return mBall1;
			case 2:		return mBall2;
			case 3:		return mBall3;
			case 4:		return mBall4;
			case 5:		return mBall5;
			default:	return (-1); 
		}
	}
	
	public int getMegaBall()
	{
		return (mMegaBall);
	}

	public String toString()
	{
		return "" + mBall1 + ITEM_SEP + mBall2 + ITEM_SEP + mBall3 + ITEM_SEP
				+ mBall4 + ITEM_SEP + mBall5 + ITEM_SEP + mMegaBall;
	}
	
	static public BallSet fromString(String s)
	{
		String[] tokens = s.split(",");
	    Vector<Integer> numbers=new Vector<Integer>(6);
		for (String t : tokens)
		{
		  numbers.add(Integer.valueOf(t));
		}
		return (new BallSet(
				numbers.get(0).intValue(),
				numbers.get(1).intValue(),
				numbers.get(2).intValue(),
				numbers.get(3).intValue(),
				numbers.get(4).intValue(),
				numbers.get(5).intValue()));
	}

	public String toLog()
	{
		return toString();
	}

}
