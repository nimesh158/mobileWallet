package com.desain.activities.mobileWallet;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	private EditText username;
	private EditText password;
	private EditText confirm;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.register);
        
        // get instances of the 2 EditTexts
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm_password);
    }
    
    /**
     * 	The method called when the register button is clicked
     */
    public void registerAndContinue(View v) {
    	String _username = username.getText().toString();
    	String _password = password.getText().toString();
    	String _confirm = confirm.getText().toString();
    	
    	if(!_username.equals("")) {
    		if(_password.equals("")) {
    			/**
        		 * Show an alert saying that password field cannot be null
        		 */
        		AlertDialog alert = new AlertDialog.Builder(this).create();
        		alert.setTitle(getResources().getString(R.string.password_cannot_be_null));
        		alert.setButton(getResources().getString(R.string.okay), new DialogInterface.OnClickListener() {
        		      public void onClick(DialogInterface dialog, int which) {} 
        		});
        		alert.show();
    		} 
    		else if(_password.equals(_confirm)) {
		    		/**
		    		 * User has entered same password twice, so continue
		    		 */
		    		SharedPreferences prefs = getSharedPreferences(Constants.MW_PREFERENCES, 0);
			    	SharedPreferences.Editor editor = prefs.edit();
			    	
			    	// save username and password
			    	editor.putString(Constants.MW_USERNAME, username.getText().toString());
			    	editor.putString(Constants.MW_PASSWORD, password.getText().toString());
			    	editor.commit();			    	

		    		startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
		    		finish(); // Required to not stack the activities on top of each other
		    	} else {
		    		/**
		    		 * Show an alert saying that the two passwords do not match
		    		 */
		    		AlertDialog alert = new AlertDialog.Builder(this).create();
		    		alert.setTitle(getResources().getString(R.string.passwords_do_not_match));
		    		alert.setButton(getResources().getString(R.string.okay), new DialogInterface.OnClickListener() {
		    		      public void onClick(DialogInterface dialog, int which) {} 
		    		});
		    		alert.show();
		    	}
    	} else {
    		/**
    		 * Show an alert saying that username cannot be null
    		 */
    		AlertDialog alert = new AlertDialog.Builder(this).create();
    		alert.setTitle(getResources().getString(R.string.username_cannot_be_empty));
    		alert.setButton(getResources().getString(R.string.okay), new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {} 
    		});
    		alert.show();
    	}
    }
}