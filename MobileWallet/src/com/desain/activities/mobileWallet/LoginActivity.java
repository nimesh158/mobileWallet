package com.desain.activities.mobileWallet;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText username;
	private EditText password;
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);		
		setContentView(R.layout.login);
		
		username = (EditText) findViewById(R.id.username_login);
		password = (EditText) findViewById(R.id.password_login);
	}
	
	public void loginAndContinue(View v) {
		SharedPreferences prefs = getSharedPreferences(Constants.MW_PREFERENCES, 0);
    	
    	String _username = prefs.getString(Constants.MW_USERNAME, "");
    	String _password = prefs.getString(Constants.MW_PASSWORD, "");
    	String usr = username.getText().toString();
    	String pass = password.getText().toString();
    	
    	if(!_username.equals(usr)) {
    		AlertDialog alert = new AlertDialog.Builder(this).create();
    		alert.setTitle(getResources().getString(R.string.usernames_do_not_match));
    		alert.setButton(getResources().getString(R.string.okay), new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {} 
    		});
    		alert.show();
    	} else if(!_password.equals(pass)) {
    		AlertDialog alert = new AlertDialog.Builder(this).create();
    		alert.setTitle(getResources().getString(R.string.passwords_do_not_match));
    		alert.setButton(getResources().getString(R.string.okay), new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {} 
    		});
    		alert.show();
    	} else {
    		AlertDialog alert = new AlertDialog.Builder(this).create();
    		alert.setTitle(getResources().getString(R.string.login_successful));
    		alert.setButton(getResources().getString(R.string.okay), new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {} 
    		});
    		alert.show();
    		
    		startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    		finish(); // Required to not stack the activities on top of each other
    	}
	}
}
