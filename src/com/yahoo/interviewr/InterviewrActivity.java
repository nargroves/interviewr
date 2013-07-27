package com.yahoo.interviewr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class InterviewrActivity extends Activity {
	
	private ListView list;
	private InterviewAdapter adapter;
	private ArrayList <InterviewRowObject> interviewObjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interviewr);
		
		
		interviewObjects = populateFromTextFile("nargroves");
		
		
		list = (ListView) findViewById(R.id.interview_list);
		adapter = new InterviewAdapter(InterviewrActivity.this);
		list.setAdapter(adapter);
	}
	
	private ArrayList <InterviewRowObject> populateFromTextFile(String interviewee) {
		interviewObjects = new ArrayList<InterviewRowObject>();

		try {
			InputStream is = getAssets().open(interviewee);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while(reader.ready()) {
				String lineOriginal = reader.readLine();
				String [] line = lineOriginal.split("\\~",-1);
				InterviewRowObject thisRow = new InterviewRowObject();
				thisRow.interviewTime = line[1];
				thisRow.username = line[2];
				thisRow.jobTitle = line[3];
				//Drawable d = Drawable.createFromPath("@drawable/"+line[4]);
				//thisRow.picture = ((BitmapDrawable)d).getBitmap();
				thisRow.jobDescription = line[5];
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return interviewObjects;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interviewr, menu);
		return true;
	}
	
	public class InterviewAdapter extends BaseAdapter {

	    private Activity activity;
	    private LayoutInflater inflater = null;

	    public InterviewAdapter(Activity a) {
	        activity = a;
	        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    public class ViewHolder{
	        public TextView mUsername;
	        public ImageView mPicture;
	        public TextView mInterviewTime;
	        public ImageView image;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        View vi = convertView;
	        ViewHolder holder;
	        if (convertView == null) {
	            vi = inflater.inflate(R.layout.interview_item, null);
	            holder = new ViewHolder();
	            holder.mUsername = (TextView) vi.findViewById(R.id.username);
	            holder.mPicture = (ImageView) vi.findViewById(R.id.picture);
	            holder.mInterviewTime = (TextView) vi.findViewById(R.id.interview_time);
	            vi.setTag(holder);
	        } else
	            holder = (ViewHolder) vi.getTag();

	        holder.mUsername.setText(interviewObjects.get(position).username);
	        holder.mPicture.setImageBitmap(interviewObjects.get(position).picture);
	        holder.mInterviewTime.setText(interviewObjects.get(position).interviewTime);
	        return vi;
	    }

		public int getCount() {
			return 0;
		}
	}
}
