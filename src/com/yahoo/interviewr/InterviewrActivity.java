package com.yahoo.interviewr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
		list.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
		adapter = new InterviewAdapter(InterviewrActivity.this, interviewObjects);
		list.setAdapter(adapter);
		list.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
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
				String jobDescription = line[4];
				InterviewRowObject thisRow = new InterviewRowObject(this, 
						username, interviewTime, jobTitle, jobDescription);
				interviewObjects.add(thisRow);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return interviewObjects;
	}
	
	
}
