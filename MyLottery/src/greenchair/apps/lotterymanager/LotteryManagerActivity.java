package greenchair.apps.lotterymanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import greenchair.lotterymanager.R;

public class LotteryManagerActivity extends ListActivity
{

	// Add a ToDoItem Request Code
	private static final int ADD_TODO_ITEM_REQUEST = 0;

	private static final String FILE_NAME = "LotteryManagerActivityData.txt";
	private static final String TAG = "Lab-UserInterface";

	// IDs for menu items
	private static final int MENU_DELETE = Menu.FIRST;
	private static final int MENU_DUMP = Menu.FIRST + 1;

	BallSetListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Create a new TodoListAdapter for this ListActivity's ListView
		mAdapter = new BallSetListAdapter(getApplicationContext());

		// Put divider between ToDoItems and FooterView
		getListView().setFooterDividersEnabled(true);

		// DONE - Inflate footerView for footer_view.xml file
		LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		TextView footerView = (TextView) inflater.inflate(R.layout.footer_view, null);

		

		// DONE - Add footerView to ListView
		getListView().addFooterView(footerView);

		footerView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				log("Entered footerView.OnClickListener.onClick()");

				// DONE - Attach Listener to FooterView. Implement onClick().
				// Create an intent stating which Activity you would like to start
				Intent intent = new Intent(LotteryManagerActivity.this, AddBallSetActivity.class);

				// Launch the Activity using the intent
				startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);				
			}
		});

		// DONE - Attach the adapter to this ListActivity's ListView
		getListView().setAdapter(mAdapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		log("Entered onActivityResult()");

		// DONE - Check result code and request code.
		// If user submitted a new ToDoItem
		// Create a new ToDoItem from the data Intent
		// and then add it to the adapter
		if ((resultCode == RESULT_OK) && (requestCode == ADD_TODO_ITEM_REQUEST))
		{
			BallSet ballSet = new BallSet(data);
			mAdapter.add(ballSet);
		}

	}

	// Do not modify below here

	@Override
	public void onResume()
	{
		super.onResume();

		// Load saved ToDoItems, if necessary

		if (mAdapter.getCount() == 0)
			loadItems();
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		// Save ToDoItems

		saveItems();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);

		menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all");
		menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case MENU_DELETE:
			mAdapter.clear();
			return true;
		case MENU_DUMP:
			dump();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void dump()
	{

		for (int i = 0; i < mAdapter.getCount(); i++)
		{
			String data = ((BallSet) mAdapter.getItem(i)).toLog();
			log("Item " + i + ": " + data.replace(BallSet.ITEM_SEP, ","));
		}

	}

	// Load stored ToDoItems
	private void loadItems()
	{
		BufferedReader reader = null;
		try
		{
			FileInputStream fis = openFileInput(FILE_NAME);
			reader = new BufferedReader(new InputStreamReader(fis));

			String line;
			while (null != (line = reader.readLine()))
			{
				mAdapter.add(BallSet.fromString(line));
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != reader)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	// Save Items to file
	private void saveItems()
	{
		PrintWriter writer = null;
		try
		{
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					fos)));

			for (int idx = 0; idx < mAdapter.getCount(); idx++)
			{
				writer.println(mAdapter.getItem(idx));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != writer)
			{
				writer.close();
			}
		}
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