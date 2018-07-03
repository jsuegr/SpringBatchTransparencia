package com.example.transparencia.model;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
	
	private String nombreHoja;
	private List<List<String>> informacionMatriz;
	
	public String getNombreHoja() {
		return nombreHoja;
	}
	public void setNombreHoja(String nombreHoja) {
		this.nombreHoja = nombreHoja;
	}
	
	public void setInformacionLista(List<String> listaInformacion){
		if(informacionMatriz==null){
			informacionMatriz = new ArrayList<List<String>>();
			informacionMatriz.add(listaInformacion);
		}
		else{
			informacionMatriz.add(listaInformacion);
		}
	}
	
	public List<List<String>> getInformacionMatriz() {
		return informacionMatriz;
	}
	

}
