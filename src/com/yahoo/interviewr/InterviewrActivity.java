package com.yahoo.interviewr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
				String [] line = lineOriginal.split("\\~", -1);
				String interviewTime = line[1];
				String username = line[2];
				String jobTitle = line[3];
				String jobDescription = line[5];
				InterviewRowObject thisRow = new InterviewRowObject(this, 
						username, interviewTime, jobTitle, jobDescription);
				interviewObjects.add(thisRow);
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
	        public TextView nameTv;
	        public TextView interviewTimeTv;
	        public TextView aboutMeTv;
	        public ImageView picture;
	        public RelativeLayout expandedView;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	    	InterviewRowObject thisRow = interviewObjects.get(position);
	        View vi = convertView;
	        ViewHolder holder;
	        if (convertView == null) {
	            vi = inflater.inflate(R.layout.interview_item, null);
	            holder = new ViewHolder();
	            holder.nameTv = (TextView) vi.findViewById(R.id.name);
	            holder.interviewTimeTv = (TextView) vi.findViewById(R.id.interview_time);
	            holder.expandedView = (RelativeLayout) vi.findViewById(R.id.bottom_part);
	            holder.picture = (ImageView) vi.findViewById(R.id.picture);
	            holder.aboutMeTv = (TextView) vi.findViewById(R.id.about_me);
	            vi.setTag(holder);
	        } else
	            holder = (ViewHolder) vi.getTag();

	        holder.nameTv.setText(thisRow.mName);
	        holder.interviewTimeTv.setText(thisRow.mInterviewTime);
	        if (thisRow.mExpanded) {
	        	holder.expandedView.setVisibility(View.VISIBLE);
	        }
	        else {
	        	holder.expandedView.setVisibility(View.GONE);
	        }
			
			String mtgName = (String) holder.nameTv.getText();
			if (!(mtgName == "Break" || mtgName == "Lunch")) {
				convertView.setOnClickListener(onCellClicked);
			}
	        
	        return vi;
	    }

		public int getCount() {
			return 0;
		}
		
		private OnClickListener onCellClicked = new OnClickListener() {

			@Override
			public void onClick(View v) {
					ViewHolder holder = (ViewHolder) v.getTag();
					RelativeLayout expandedView = holder.expandedView;
					expand(expandedView);
        	}
		};
		
		private void expand(View expandedView) {
			expandedView.setVisibility(View.VISIBLE);
			expandedView.getLayoutParams().height = 0;
			DropDownAnim dda = new DropDownAnim(expandedView, true);
			expandedView.startAnimation(dda);
		}
	}
}
