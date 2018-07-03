package com.example.transparencia.model;

import java.util.ArrayList;
import java.util.List;

public class Tabla {
	
	private String nombreTabla;
	private List<List<String>> informacionTabla;
	private List<Catalogo> listaCatalogo;
	private Catalogo catalogo;
	

	public String getNombreTabla() {
		return nombreTabla;
	}
	public List<List<String>> getInformacionTabla() {
		return informacionTabla;
	}
	public List<Catalogo> getListaCatalogo() {
		return listaCatalogo;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	
	public void setInformacionTabla(List<String> listaInformacion){
		if(informacionTabla==null){
			informacionTabla = new ArrayList<List<String>>();
			informacionTabla.add(listaInformacion);
		}
		else{
			informacionTabla.add(listaInformacion);
		}
	}

	public void setListaCatalogo(Catalogo catalogo) {
		
		if(listaCatalogo==null){
			listaCatalogo = new ArrayList<Catalogo>();
			listaCatalogo.add(catalogo);
		}
		else{
			listaCatalogo.add(catalogo);
		}
		
	}
	public Catalogo getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
}
