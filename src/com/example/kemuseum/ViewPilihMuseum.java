package com.example.kemuseum;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewPilihMuseum extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pilih_museum);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pilih_museum, menu);
		return true;
	}

}
