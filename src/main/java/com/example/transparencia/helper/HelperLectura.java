package com.example.transparencia.helper;
import org.springframework.stereotype.Component;

@Component("helperLectura")
public class HelperLectura {

	public boolean obtenerHidden(String nombreHoja) {

		if ("Hidden".equals(nombreHoja.substring(0, 6))) {

			if (nombreHoja.contains("Hidden") && nombreHoja.contains("Tabla")) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}
	}

	public boolean obtenerTabla(String nombreHoja) {
		if ("Tabla".equals(nombreHoja.substring(0, 5))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean obtenerTablaCatalogo(String nombreHoja) {
		if (nombreHoja.contains("Hidden") && nombreHoja.contains("Tabla")) {
			return true;
		} else {
			return false;
		}

	}
}
