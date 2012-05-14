package com.desain.activities.mobileWallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;

public class HomeActivity extends Activity {
	static final int CREDIT_CARD_REQUEST = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.home);
	}

	// Add a new credit card method
	public void addNewCreditCard(View v) {
		Intent i = new Intent(HomeActivity.this, CreditCardInformationActivity.class);
		startActivityForResult(i, CREDIT_CARD_REQUEST);
	}
	
	// Override the onActivityResult
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.w(Constants.MOBILE_WALLET, "Inside On activity result");
		
		switch(requestCode) {
			case(CREDIT_CARD_REQUEST): {
				Log.w(Constants.MOBILE_WALLET, "Inside credit card request");
				if(resultCode == Activity.RESULT_OK) {
					/* Find Tablelayout defined in main.xml */
			        TableLayout tl = (TableLayout)findViewById(R.id.ccTable);
			        tl.setStretchAllColumns(true);
		             /* Create a new row to be added. */
		             TableRow tr = new TableRow(this);
		             tr.setLayoutParams(new LayoutParams(
		                            LayoutParams.FILL_PARENT,
		                            LayoutParams.WRAP_CONTENT));
	                  /* Create a Button to be the row-content. */
	                  Button b = new Button(this);
	                  b.setText(data.getStringExtra(Constants.CREDIT_CARD_NAME));
	                  b.setLayoutParams(new LayoutParams(
	                            LayoutParams.FILL_PARENT,
	                            LayoutParams.WRAP_CONTENT));
	                  /* Add Button to row. */
	                  tr.addView(b);
			        /* Add row to TableLayout. */
			        tl.addView(tr,new TableLayout.LayoutParams(
			                  LayoutParams.FILL_PARENT,
			                  LayoutParams.WRAP_CONTENT));
				}
			}
		}
	}
}
