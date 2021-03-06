package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Museum {
	private int id;
	private String nama;
	private String deskripsi;
	private Koordinat koordinatKiriAtas;
	private Koordinat koordinatKananBawah;
	private boolean statusTerkunci;
	private List<Ruangan> daftarRuangan;
	private String namaBerkasGambarMuseum;
	private String namaBerkasGambarDenah;
	
	public Museum(int id, String nama, String deskripsi,
			Koordinat koordinatKiriAtas, Koordinat koordinatKananBawah,
			String namaBerkasGambarMuseum, String namaBerkasGambarDenah,
			boolean statusTerkunci, List<Ruangan> daftarRuangan) {
		this.id = id;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.koordinatKiriAtas = koordinatKiriAtas;
		this.koordinatKananBawah = koordinatKananBawah;
		this.namaBerkasGambarMuseum = namaBerkasGambarMuseum;
		this.namaBerkasGambarDenah = namaBerkasGambarDenah;
		this.statusTerkunci = statusTerkunci;
		this.daftarRuangan = daftarRuangan;
	}

	public int getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public String getStringKoordinatKiriAtas() {
		return koordinatKiriAtas.toString();
	}

	public String getStringKoordinatKananBawah() {
		return koordinatKananBawah.toString();
	}

	public String getNamaBerkasGambarMuseum(){
		return namaBerkasGambarMuseum;
	}
	
	public String getNamaBerkasGambarDenah(){
		return namaBerkasGambarDenah;
	}
	
	public List<Ruangan> getDaftarRuangan() {
		return daftarRuangan;
	}

	public Ruangan getRuangan(int id) {
		Ruangan ret = null;
		for (Ruangan r : daftarRuangan) {
			if (r.getId() == id) {
				ret = r;
				break;
			}
		}
		return ret;
	}

	public boolean getStatusTerkunci() {
		return statusTerkunci;
	}

	public void setStatusTerkunci(boolean nilai) {
		statusTerkunci = nilai;
	}

	public boolean cekDiDalam(Koordinat koordinat) {
		return koordinat.diDalam(koordinatKiriAtas, koordinatKananBawah);
	}

	public boolean cekRuanganSudahBisaDibuka(Ruangan ruangan) {
		// periksa apakah ada ruangan yang terkunci tetapi prioritasnya lebih
		// tinggi dari ruangan yang menjadi parameter ^

		boolean ada = false;
		for (Ruangan r : daftarRuangan) {
			if (!r.getStatusTerkunci()
					&& (r.getPrioritas() > ruangan.getPrioritas())) {
				ada = true;
				break;
			}
		}

		return ada;
	}

	public List<Barang> getDaftarBarang(int idRuangan) {
		List<Barang> daftar = null;

		for (Ruangan r : daftarRuangan) {
			if (r.getId() == idRuangan) {
				daftar = r.getDaftarBarang();
				break;
			}
		}

		return daftar;
	}

	public List<Pertanyaan> getDaftarPertanyaan(int idRuangan) {
		List<Pertanyaan> daftar = null;

		for (Ruangan r : daftarRuangan) {
			if (r.getId() == idRuangan) {
				daftar = r.getDaftarPertanyaan();
				break;
			}
		}

		return daftar;
	}

//	public List<Pertanyaan> getDaftarJawaban(int idRuangan) {
//		List<Pertanyaan> daftar = null;
//
//		for (Ruangan r : daftarRuangan) {
//			if (r.getId() == idRuangan) {
//				daftar = r.getDaftarJawaban();
//				break;
//			}
//		}
//
//		return daftar;
//	}
	
	public int cekJawaban(int idRuangan, List<String> jawaban) {
		int hasil = 0;

		for (Ruangan r : daftarRuangan) {
			if (r.getId() == idRuangan) {
				hasil = r.cekJawaban(jawaban);
				break;
			}
		}

		return hasil;
	}

	public List<Barang> cariBarang(String kataKunci) {
		List<Barang> daftar = new ArrayList<Barang>();

		for (Ruangan r : daftarRuangan) {
			// hanya bisa bila ruangan sudah terbuka!
			if (!r.getStatusTerkunci()) {
				List<Barang> temp = r.cariBarang(kataKunci);
				daftar.addAll(temp);
			}
		}

		return daftar;
	}

	public Ruangan getRuanganDariWarna(int warna) {
		for (Ruangan r : daftarRuangan) {
			if (r.getWarna() == warna){
				return r;
			}
		}

		return null;
	}
}
