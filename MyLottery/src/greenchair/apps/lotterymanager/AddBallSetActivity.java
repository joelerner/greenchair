package greenchair.apps.lotterymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import greenchair.lotterymanager.R;

public class AddBallSetActivity extends Activity
{

	private static final String TAG = "Lab-UserInterface";

	private EditText mBall1Text;
	private EditText mBall2Text;
	private EditText mBall3Text;
	private EditText mBall4Text;
	private EditText mBall5Text;
	private EditText mMegaBallText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_ballset);

		mBall1Text = (EditText) findViewById(R.id.editBall1);
		mBall2Text = (EditText) findViewById(R.id.editBall2);
		mBall3Text = (EditText) findViewById(R.id.editBall3);
		mBall4Text = (EditText) findViewById(R.id.editBall4);
		mBall5Text = (EditText) findViewById(R.id.editBall5);
		mMegaBallText = (EditText) findViewById(R.id.editMegaBall);



		// OnClickListener for the Cancel Button,

		final Button cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				log("Entered cancelButton.OnClickListener.onClick()");

				// DONE - Implement onClick().
				finish();

			}
		});

		// OnClickListener for the Reset Button

		final Button resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				log("Entered resetButton.OnClickListener.onClick()");

				// DONE - Reset data fields to default values
				mBall1Text.setText(null);
				mBall2Text.setText(null);
				mBall3Text.setText(null);
				mBall4Text.setText(null);
				mBall5Text.setText(null);
				mMegaBallText.setText(null);
			}
		});

		// OnClickListener for the Submit Button
		// Implement onClick().

		final Button submitButton = (Button) findViewById(R.id.submitButton);
		submitButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				log("Entered submitButton.OnClickListener.onClick()");

				// Gather data

				// Package data into an Intent
				Intent data = new Intent();
				BallSet.packageIntent(data, 
						editTextToInt(mBall1Text),
						editTextToInt(mBall2Text),
						editTextToInt(mBall3Text),
						editTextToInt(mBall4Text),
						editTextToInt(mBall5Text),
						editTextToInt(mMegaBallText)
						);

				// DONE - return data Intent and finish

				setResult(RESULT_OK, data);
				finish();
			}
		});
	}

	// Do not modify below here

	// Use this method to set the default date and time
	static private int editTextToInt(EditText t)
	{
		int i = 0;
		try
		{
			i = Integer.parseInt(t.getText().toString());
		}
		catch (NumberFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			i = 0;
		}
		return (i);
	}

	private void log(String msg)
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.i(TAG, msg);
	}

}
