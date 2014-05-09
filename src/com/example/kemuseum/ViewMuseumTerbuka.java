package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kemuseum.controller.ControllerMuseum;
import com.example.kemuseum.model.Ruangan;
import com.example.kemuseum.utils.ArrayAdapterPilihRuangan;

public class ViewMuseumTerbuka extends Activity {
	private ArrayAdapterPilihRuangan arrayAdapter;
	private ListView listView;
	private ImageView gambarMuseum;
	private ImageView denahMuseum;
	private TextView deskripsiMuseum;
	private ControllerMuseum controller;
	private ImageView Magni;
	private Ruangan ruanganTerpilih;
	private Toast toast;
	private View toastLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_museum_terbuka);
	
		inisialisasi();
		setClickListener();
		isiData();
	}

	private void inisialisasi(){
		toast = new Toast(getApplicationContext());
		gambarMuseum = (ImageView) findViewById(R.id.gambar_museum);
		denahMuseum = (ImageView) findViewById(R.id.gambar_denah);
		deskripsiMuseum = (TextView) findViewById(R.id.deskripsi_museum);
		listView = (ListView) findViewById(R.id.list_view_ruangan);
		Magni = (ImageView) findViewById(R.id.magni);
		
		controller = new ControllerMuseum();
	
		LayoutInflater inflater = getLayoutInflater();
    	toastLayout = inflater.inflate(R.layout.toast_pilih_ruangan, (ViewGroup) findViewById(R.id.toast_layout_root));
	}
	
	public void setClickListener() {
		Magni.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewMuseumTerbuka.this, ViewPencarian.class);
				i.putExtra("Pencarian", "a");
				final int a = 1;
				startActivityForResult(i, a);

				// showDialog(0);
			}
		});
	}
	private void isiData(){
		Intent i = this.getIntent();
		final int idMuseum = i.getIntExtra("idMuseum", -1);
		
		// valid
		if (idMuseum != -1){
			// set gambar museum
			Drawable gambar= controller.getGambarMuseum(idMuseum);
			if (gambar != null){
				gambarMuseum.setImageDrawable(gambar);
			}else{
				gambarMuseum.setImageResource(R.drawable.museum_locked_foto_bahari);
			}
			// set deskripsi museum
			deskripsiMuseum.setText(controller.getMuseum(idMuseum).getDeskripsi());
			
			// urusan denah ruangan
			Drawable denah = controller.getGambarDenah(idMuseum);
			if (denah != null){
				denahMuseum.setImageDrawable(denah);
			}else{
				denahMuseum.setImageResource(R.drawable.denah_gabung);
			}
			
			
			final Bitmap bitmap = ((BitmapDrawable)denahMuseum.getDrawable()).getBitmap();

			// get touch constant for screen - image scaling
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			final float constant = (float)bitmap.getWidth() / size.x; 
			denahMuseum.setOnTouchListener(new OnTouchListener(){
		        @Override
		        public boolean onTouch(View v, MotionEvent event){
			        int x = (int)(event.getX() * constant);
			        int y = (int)(event.getY() * constant);
			        int pixel = bitmap.getPixel(x,y);
	
			        // ambil warna pixel
			        int redValue = Color.red(pixel);
			        int greenValue = Color.green(pixel);        
			        int blueValue = Color.blue(pixel);
			        
			        int warna = (redValue << 16) | (greenValue << 8) | blueValue;
			        ruanganTerpilih = controller.getRuanganDariWarna(idMuseum, warna);
			        
			        Log.d("asd", "gan " + x + " " + y + " " + warna);
			        
			        if (ruanganTerpilih != null){
			        	TextView text = (TextView) toastLayout.findViewById(R.id.deskripsi_ruangan);
			        	ImageView image = (ImageView) toastLayout.findViewById(R.id.tombol_masuk);
			        	
			        	text.setText(ruanganTerpilih.getNama());
			        	if (ruanganTerpilih.getDeskripsi().length() > 0){
			        		text.setText(text.getText() + ": " + ruanganTerpilih.getDeskripsi());
			        	}
			        	
			        	final Ruangan targetRuangan = ruanganTerpilih;
			        	image.setOnTouchListener(new OnTouchListener(){
					        @Override
					        public boolean onTouch(View v, MotionEvent event){
								// true -> terkunci
								if (targetRuangan.getStatusTerkunci()){
									Intent i = new Intent (ViewMuseumTerbuka.this, ViewPertanyaan.class);
									i.putExtra("Terkunci", "a");
									i.putExtra("idMuseum", targetRuangan.getIdMuseum());
									i.putExtra("idRuangan", targetRuangan.getId());
									final int a = 1;
									startActivityForResult(i, a);
								}else{
									Intent i = new Intent (ViewMuseumTerbuka.this, ViewRuangan.class);
									i.putExtra("Terbuka", "a");
									i.putExtra("idMuseum", targetRuangan.getIdMuseum());
									i.putExtra("idRuangan", targetRuangan.getId());
									final int a = 1;						
									startActivityForResult(i, a);
								}
					        	
								toast.cancel();
					        	return false;
					        }
			        	});
			        	
			        	toast.setGravity(Gravity.TOP, 0, 60);
			        	toast.setDuration(Toast.LENGTH_SHORT);
			        	toast.setView(toastLayout);
			        	toast.show();
			        }
			        
			        return false;
		        }
		   });
			/*
			List<Ruangan> daftarRuangan = controller.getDaftarRuangan(idMuseum);
			
			arrayAdapter = new ArrayAdapterPilihRuangan(this, daftarRuangan);
			listView.setAdapter(arrayAdapter);
			
			// bila ditap
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, final View view,
						int position, long id) {
					final Ruangan item = (Ruangan) parent.getItemAtPosition(position);
					
					// true -> terkunci
					if (item.getStatusTerkunci()){
						Intent i = new Intent (ViewMuseumTerbuka.this, ViewPertanyaan.class);
						i.putExtra("Terkunci", "a");
						i.putExtra("idMuseum", item.getIdMuseum());
						i.putExtra("idRuangan", item.getId());
						Log.d("asd", "gan " + item.getId() + " " + item.getIdMuseum());
						final int a = 1;
						startActivityForResult(i, a);
					}else{
						Intent i = new Intent (ViewMuseumTerbuka.this, ViewRuangan.class);
						i.putExtra("Terbuka", "a");
						i.putExtra("idMuseum", item.getIdMuseum());
						i.putExtra("idRuangan", item.getId());
						final int a = 1;						
						startActivityForResult(i, a);
					}
				}
			});
			*/
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_museum_terbuka, menu);
		return true;
	}
	

	@Override
	public void onResume() {
		super.onResume();
		
		// mungkin ada perubahan
//		arrayAdapter.notifyDataSetChanged();
	}
}
