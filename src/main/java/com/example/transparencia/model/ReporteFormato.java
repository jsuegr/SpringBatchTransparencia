package com.example.transparencia.model;

import java.util.ArrayList;
import java.util.List;

public class ReporteFormato {
	
	private String nombreHoja;
	private List<List<String>> informacionMatriz;
	
	private List<Catalogo> listCatalogo;
	private Catalogo catalogo;
	
	
	private Tabla tabla;
	private List<Tabla> listTabla;
	

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
	
	
	public List<Catalogo> getListCatalogo() {
		return listCatalogo;
	}
	
	
	public void setCatalogoLista(Catalogo catalogo){	
		if(listCatalogo==null){
			listCatalogo = new ArrayList<Catalogo>();
			listCatalogo.add(catalogo);
		}
		else{
			listCatalogo.add(catalogo);
		}
	}
	
	public void setTablaLista(Tabla tabla){	
		if(listTabla==null){
			listTabla = new ArrayList<Tabla>();
			listTabla.add(tabla);
		}
		else{
			listTabla.add(tabla);
		}
	}
	
	public List<Tabla> getListTabla() {
		return listTabla;
	}
	public Catalogo getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
	public Tabla getTabla() {
		return tabla;
	}
	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

}
