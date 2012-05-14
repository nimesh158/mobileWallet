package com.desain.activities.mobileWallet;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {
	
	protected boolean _active = true;
	protected int _splashTime = 2000; // time to display the splash screen in ms

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.splash);
		
		// thread for displaying the SplashScreen
	    Thread splashTread = new Thread() {
	        @Override
	        public void run() {
	            try {
	                int waited = 0;
	                while(_active && (waited < _splashTime)) {
	                    sleep(100);
	                    if(_active) {
	                        waited += 100;
	                    }
	                }
	            } catch(InterruptedException e) {
	                // do nothing
	            } finally {
	                finish();
	                try {
			    		SharedPreferences prefs = getSharedPreferences(Constants.MW_PREFERENCES, 0);
				    	if(!prefs.contains(Constants.MW_USERNAME))
				    		startActivity(new Intent(SplashScreenActivity.this, RegisterActivity.class));
				    	else
				    		startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
	                }
	                catch ( ActivityNotFoundException e) {
	                    e.printStackTrace();
	                }
	                stop();
	            }
	        }
	    };
	    splashTread.start();
	}
}
