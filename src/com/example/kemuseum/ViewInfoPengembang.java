package com.example.kemuseum;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewInfoPengembang extends Activity {	
	LinearLayout llHeaderTulisan, llPilMuseum, llCapaian, llCari, llAbout, llPencarian2, llBody, layoutAsli;
	TextView tvHeaderTulisan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inisiasi();
		setClickListener();
	}
	
	public void inisiasi()
	{
		llHeaderTulisan = (LinearLayout) findViewById(R.id.llHeaderTulisan);
    	llPilMuseum = (LinearLayout) findViewById(R.id.llPilMuseum);
    	llCapaian = (LinearLayout) findViewById(R.id.llCapaian);
    	llCari = (LinearLayout) findViewById(R.id.llCari);
    	llAbout = (LinearLayout) findViewById(R.id.llAbout);
    	tvHeaderTulisan = (TextView) findViewById(R.id.tvHeaderTulisan);
		
		layoutAsli = (LinearLayout)View.inflate(this, R.layout.activity_view_info_pengembang, null);
		llBody = (LinearLayout) findViewById(R.id.llBody);
		llBody.addView(layoutAsli);
	}
	
    public void setClickListener()
    {
    	
    	llCapaian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewInfoPengembang.this, ViewCapaian.class);
				startActivity(i);
			}
		});
    	
    	llPilMuseum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewInfoPengembang.this, ViewPilihMuseum.class);
				startActivity(i);
			}
		});
    	
    	llAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewInfoPengembang.this, ViewInfoPengembang.class);
				startActivity(i); 
			}
		});
    	
    	llCari.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewInfoPengembang.this, ViewPencarian.class);
				startActivity(i);
			}
		});
    }
}
