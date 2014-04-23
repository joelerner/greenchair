package greenchair.apps.lotterymanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import greenchair.lotterymanager.R;

public class BallSetListAdapter extends BaseAdapter
{

	// List of ToDoItems
	private final List<BallSet> mItems = new ArrayList<BallSet>();

	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public BallSetListAdapter(Context context)
	{

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(BallSet item)
	{

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear()
	{

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount()
	{

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos)
	{

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos)
	{

		return pos;

	}

	// Create a View to display the ToDoItem
	// at specified position in mItems

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		// DONE - Get the current ToDoItem
		final BallSet ballSet = mItems.get(position);

		// DONE - Inflate the View for this ToDoItem
		// from todo_item.xml.
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 

		RelativeLayout itemLayout = (RelativeLayout) inflater.inflate(R.layout.ballset_item, null);

		// DONE - Fill in specific  data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file


		final TextView ball1View = (TextView) itemLayout.findViewById(R.id.textBall1);
		ball1View.setText(String.valueOf(ballSet.getBall(1)));
		final TextView ball2View = (TextView) itemLayout.findViewById(R.id.textBall2);
		ball2View.setText(String.valueOf(ballSet.getBall(2)));
		final TextView ball3View = (TextView) itemLayout.findViewById(R.id.textBall3);
		ball3View.setText(String.valueOf(ballSet.getBall(3)));
		final TextView ball4View = (TextView) itemLayout.findViewById(R.id.textBall4);
		ball4View.setText(String.valueOf(ballSet.getBall(4)));
		final TextView ball5View = (TextView) itemLayout.findViewById(R.id.textBall5);
		ball5View.setText(String.valueOf(ballSet.getBall(5)));
		final TextView megaBallView = (TextView) itemLayout.findViewById(R.id.textMegaBall);
		megaBallView.setText(String.valueOf(ballSet.getMegaBall()));

		// Return the View you just created
		return itemLayout;

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
