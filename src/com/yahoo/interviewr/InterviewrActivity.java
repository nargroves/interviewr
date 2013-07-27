package com.yahoo.interviewr;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class InterviewrActivity extends Activity {
	
	private ExpandableListView list;
	private InterviewAdapter adapter;
	private ArrayList <InterviewRowObject> interviewObjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interviewr);
		
		interviewObjects = new ArrayList<InterviewRowObject>();
		
		list = (ExpandableListView) findViewById(R.id.interview_list);
		adapter = new InterviewAdapter(InterviewrActivity.this);
		list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interviewr, menu);
		return true;
	}
	
	public class InterviewAdapter extends BaseExpandableListAdapter {

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

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return false;
		}
	}

}
