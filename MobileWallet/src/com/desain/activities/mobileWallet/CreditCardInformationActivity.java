package com.desain.activities.mobileWallet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreditCardInformationActivity extends Activity {
	static final int FRONT_PICTURE_REQUEST = 0;
	static final int REAR_PICTURE_REQUEST = 1;
	private boolean frontPictureIsTaken = false;
	private boolean backPictureIsTaken = false;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.info);
	}
	
	public void takeFrontImage(View v) {
		//create new Intent
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // start the image capture Intent
	    startActivityForResult(intent, FRONT_PICTURE_REQUEST);
	}
	
	public void takeBackImage(View v) {
		//create new Intent
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // start the image capture Intent
	    startActivityForResult(intent, REAR_PICTURE_REQUEST);
	}
	
	public void doneAndContinue(View v) {
		EditText et = (EditText) findViewById(R.id.credit_card_name);
		String s = et.getText().toString();
		if(s != "") {
			Intent intent = new Intent();
			intent.putExtra(Constants.CREDIT_CARD_NAME, s);
			setResult(RESULT_OK, intent);
			finish();
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.w(Constants.MOBILE_WALLET, "Inside On activity result");
		
		switch(requestCode) {
			case(FRONT_PICTURE_REQUEST): {
				Log.w(Constants.MOBILE_WALLET, "Inside credit card request");
				if(resultCode == Activity.RESULT_OK) {
					this.frontPictureIsTaken = true;
					if(this.frontPictureIsTaken && this.backPictureIsTaken) {
						Button b = (Button) findViewById(R.id.doneButton);
						b.setEnabled(true);
					}					

//				    Bundle extras = data.getExtras();
//				    Bitmap mImageBitmap = (Bitmap) extras.get("data");
				}
			}
			
			case(REAR_PICTURE_REQUEST): {
				Log.w(Constants.MOBILE_WALLET, "Inside credit card request");
				if(resultCode == Activity.RESULT_OK) {
					this.backPictureIsTaken = true;
					if(this.frontPictureIsTaken && this.backPictureIsTaken) {
						Button b = (Button) findViewById(R.id.doneButton);
						b.setEnabled(true);
					}
				}
			}
		}
	}
}
