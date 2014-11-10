package com.scumteam.searchapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ScumteamSearchApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Add your initialization code here
    Parse.initialize(this, "zwA8dR5TxWRZrVwZJrIpetHMgOWtgerQbFRQLfFp", "yo9nL5BNfbVRfDRvNeeIIAjPM2B4V0obj7t1idAl");


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
      
    // If you would like all objects to be private by default, remove this line.
    defaultACL.setPublicReadAccess(true);
    
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
