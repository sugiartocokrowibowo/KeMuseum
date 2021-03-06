package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Capaian {
	private String namaMuseum;
	private float progress;
	private List<String> capaianPerRuangan;
	
	public Capaian(Museum museum){
		this.namaMuseum = museum.getNama();
		this.capaianPerRuangan = generateCapaian(museum);
	}
	
	public String getNamaMuseum(){
		return namaMuseum;
	}
	
	public int getProgress(){
		// tanpa desimal
		return (int)(progress + 0.1f);
	}
	
	public List<String> getCapaianPerRuangan(){
		return capaianPerRuangan;
	}
	
	private List<String> generateCapaian(Museum museum){
		List<String> ret = new ArrayList<String>();
		
		List<Ruangan> daftarRuangan = museum.getDaftarRuangan();
		progress = 0;
		int totalRuanganBerisiBarang = 0;
		for (Ruangan r : daftarRuangan){
			// bukan ruangan yang berisi barang
			if (r.getDaftarBarang().size() == 0) continue;
			
			String status = "Status: " + (r.getStatusTerkunci() ? "terkunci" : "terbuka");
			String percobaan = "Banyak percobaan: " + r.getBanyakPercobaanBukaKunci() + " kali";
			
			ret.add(r.getNama() + "\n" + status + "\n" + percobaan);
			
			totalRuanganBerisiBarang++;
			if (!r.getStatusTerkunci()){
				progress++;
			}
		}
		// ambil presentasenya
		progress /= totalRuanganBerisiBarang;
		progress *= 100;
		
		Collections.sort(ret);
		
		return ret;
	}
}
