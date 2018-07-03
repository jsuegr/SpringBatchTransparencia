package com.example.transparencia.configBatchExcel;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.example.transparencia.helper.HelperLectura;
import com.example.transparencia.model.AtributosFormato;
import com.example.transparencia.model.Catalogo;
import com.example.transparencia.model.ReporteFormato;
import com.example.transparencia.model.Tabla;

@Component("procesoLectura")
@Scope("step")
public class ProcesoLectura implements ItemProcessor<AtributosFormato, ReporteFormato> {

	private static ReporteFormato reporteFormato;
	private static Catalogo catalogo;
	private static String nombreCatalogo = "";

	private static Tabla tabla;
	private static String nombreTabla = "";

	private static String nombreTablaCatalogo = "";
	private static Catalogo tablaCatalogo;

	@Autowired
	private HelperLectura helperLectura;


	public ProcesoLectura() {
		reporteFormato = new ReporteFormato();
		catalogo = null;
		tabla = null;
		tablaCatalogo = null;
	}

	@Override
	public ReporteFormato process(AtributosFormato atributosFormato) throws Exception {

		if (atributosFormato.getNombreHoja().equals("Reporte de Formatos")) {

			reporteFormato.setNombreHoja(atributosFormato.getNombreHoja());
			reporteFormato.setInformacionLista(atributosFormato.getListaFila());
		} else if (helperLectura.obtenerHidden(atributosFormato.getNombreHoja())) {

			if (nombreCatalogo.isEmpty()) { /* validacion para guardar el primer registro */

				inicioObjetoCatalogo(atributosFormato.getNombreHoja(), atributosFormato.getListaFila());

			} else if (nombreCatalogo.equals(
					atributosFormato.getNombreHoja())) { /*
															 * validacion para saber cuando se cambia de catalogo
															 */

				catalogo.setInformacionLista(atributosFormato.getListaFila());
				reporteFormato.setCatalogo(catalogo);

			} else {
				reporteFormato.setCatalogoLista(catalogo); /* guarda la lista de catalogo */
				inicioObjetoCatalogo(atributosFormato.getNombreHoja(), atributosFormato.getListaFila());
			}
		} else if (helperLectura.obtenerTabla(atributosFormato.getNombreHoja())) {

			if (nombreTabla.isEmpty()) { /* validacion para guardar el primer registro */

				iniciarTabla(atributosFormato.getNombreHoja(), atributosFormato.getListaFila());

			} else if (nombreTabla
					.equals(atributosFormato.getNombreHoja())) { /*
																	 * validacion para saber cuando se cambia de tabla
																	 */
				tabla.setInformacionTabla(atributosFormato.getListaFila());
				reporteFormato.setTabla(tabla);
			} else {
				reporteFormato.setTablaLista(tabla);
				iniciarTabla(atributosFormato.getNombreHoja(), atributosFormato.getListaFila());
			}

		} else if (helperLectura.obtenerTablaCatalogo(atributosFormato.getNombreHoja())) {

			if (nombreTablaCatalogo.isEmpty()) {

				inicioObjetoTablaCatalogo(atributosFormato.getNombreHoja(), atributosFormato.getListaFila());

			} else if (nombreTablaCatalogo
					.equals(atributosFormato.getNombreHoja())) { /*
																	 * validacion para saber cuando se cambia de tabla
																	 */
				tablaCatalogo.setInformacionLista(atributosFormato.getListaFila());
				tabla.setCatalogo(tablaCatalogo);

			} else {
				tabla.setListaCatalogo(tablaCatalogo);

				inicioObjetoTablaCatalogo(atributosFormato.getNombreHoja(), atributosFormato.getListaFila());
			}

		}

		return reporteFormato;
	}
	
	@BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        ReporteFormato reporteFormato = (ReporteFormato) jobContext.get("reporteFormato");
        System.out.println("");
    }

	@AfterStep
	public ReporteFormato afterStep(StepExecution stepExecution) {

		System.out.println("Called afterStep().");

		ReporteFormato reporteFormato = getReporteFormato();

		if (reporteFormato.getCatalogo() != null) {
			reporteFormato.setCatalogoLista(reporteFormato.getCatalogo());
			reporteFormato.setCatalogo(null);
		}

		if (reporteFormato.getTabla() != null) {
			reporteFormato.setTablaLista(reporteFormato.getTabla());
			reporteFormato.setTabla(null);
		}

		if (reporteFormato.getListTabla() != null) {
			List<Tabla> listaTabla = reporteFormato.getListTabla();
			for (Tabla tabla : listaTabla) {
				if (tabla.getCatalogo() != null) {
					tabla.setListaCatalogo(tabla.getCatalogo());
					tabla.setCatalogo(null);
				}
			}
		}

		setReporteFormato(reporteFormato);

		return reporteFormato;
	}

	public static void inicioObjetoCatalogo(String nombreHoja, List<String> listaFila) {

		catalogo = new Catalogo();
		catalogo.setNombreHoja(nombreHoja);
		catalogo.setInformacionLista(listaFila);
		nombreCatalogo = nombreHoja;
		reporteFormato.setCatalogo(null);
	}

	public static void iniciarTabla(String nombreHoja, List<String> informacionTabla) {
		tabla = new Tabla();
		tabla.setNombreTabla(nombreHoja);
		tabla.setInformacionTabla(informacionTabla);
		nombreTabla = nombreHoja;
		reporteFormato.setTabla(null);
	}

	public static void inicioObjetoTablaCatalogo(String nombreHoja, List<String> listaFila) {

		tablaCatalogo = new Catalogo();
		tablaCatalogo.setNombreHoja(nombreHoja);
		tablaCatalogo.setInformacionLista(listaFila);
		nombreTablaCatalogo = nombreHoja;
		tabla.setCatalogo(null);
	}

	public ReporteFormato getReporteFormato() {
		return reporteFormato;
	}

	public String getNombreCatalogo() {
		return nombreCatalogo;
	}

	public static void setReporteFormato(ReporteFormato reporteFormato) {
		ProcesoLectura.reporteFormato = reporteFormato;
	}

}
