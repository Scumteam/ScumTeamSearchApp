package com.scumteam.searchapp;

import java.io.InputStream;

import com.scumteam.searchapp.R;
import com.scumteam.searchapp.R.id;
import com.scumteam.searchapp.R.layout;
import com.scumteam.searchapp.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends ActionBarActivity {
	
	private Note note;
	private TextView nameText;
	private TextView contentText;
	private String postName;
	private String postContent;
	
	private WebView picView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
	    Intent intent = this.getIntent();
	    
	    nameText = (TextView) findViewById(R.id.noteName);
	    contentText = (TextView) findViewById(R.id.noteContent);
	    // show The Image

	 
	    if (intent.getExtras() != null) {
	        note = new Note(intent.getStringExtra("noteId"), intent.getStringExtra("noteName"), intent.getStringExtra("noteContent"), intent.getStringExtra("noteFileUrl"));
	 
		    new DownloadImageTask((ImageView) findViewById(R.id.picView)).execute(note.getFileUrl());
	        
	        nameText.setText(note.getName());
	        contentText.setText(note.getContent());
	    }
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
