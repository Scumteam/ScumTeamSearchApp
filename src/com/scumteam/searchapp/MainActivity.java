package com.scumteam.searchapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import com.scumteam.searchapp.R;
import com.scumteam.searchapp.R.layout;

import com.scumteam.searchapp.Note;

public class MainActivity extends ListActivity {
	/** Called when the activity is first created. */
	
	private List<Note> notes;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

//		ParseAnalytics.trackAppOpened(getIntent());
		
		
	    notes = new ArrayList<Note>();
	    ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, R.layout.list_item_layout, notes);
	    setListAdapter(adapter);
	 
	    refreshDataList();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	 
	    Note note = notes.get(position);
	    Intent intent = new Intent(this, ViewActivity.class);
	    intent.putExtra("noteId", note.getId());
	    intent.putExtra("noteName", note.getName());
	    intent.putExtra("noteContent", note.getContent());
	    intent.putExtra("noteFileUrl", note.getFileUrl());
	    
	    startActivity(intent);
	 
	}
	
	private void refreshDataList() {
		 
	    ParseQuery<ParseObject> query = ParseQuery.getQuery("Data");
	 
	    query.findInBackground(new FindCallback<ParseObject>() {
	 
	        @Override
	        public void done(List<ParseObject> dataList, ParseException e) {
	            if (e == null) {
	                // If there are results, update the list of posts
	                // and notify the adapter
	                notes.clear();
	                for (ParseObject data : dataList) {
	                    Note note = new Note(data.getObjectId(), data.getString("name"), data.getString("content"), data.getParseFile("file").getUrl());
	                    notes.add(note);
	                }
	                ((ArrayAdapter<Note>) getListAdapter()).notifyDataSetChanged();
	            } else {
	                Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
	            }
	        }
	    });
	}
}
