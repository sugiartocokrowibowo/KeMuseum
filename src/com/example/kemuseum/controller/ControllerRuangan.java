package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerRuangan {
	private MuseumManager museumManager;
	
	public ControllerRuangan(){
		museumManager = MuseumManager.getMuseumManager();
	}
	
	public List<Barang> getDaftarBarang(int idMuseum, int idRuangan){
		return museumManager.getDaftarBarang(idMuseum, idRuangan);
	}
}
