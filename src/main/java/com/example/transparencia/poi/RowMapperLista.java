package com.example.transparencia.poi;
import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;
import com.example.transparencia.model.AtributosFormato;

public class RowMapperLista  implements RowMapper<AtributosFormato>{

	@Override
	public AtributosFormato mapRow(RowSet rs) throws Exception {

		String nombreHoja = rs.getMetaData().getSheetName();
		
		List<String> listaFila = new ArrayList<String>();
		String[] valorFila =  rs.getCurrentRow();
		
		for (String valorCelda : valorFila) {
			listaFila.add(valorCelda);
		}
		
		AtributosFormato atributosFormato = new AtributosFormato();
		atributosFormato.setNombreHoja(nombreHoja);
		atributosFormato.setListaFila(listaFila);
		
		return atributosFormato;
	}

}
