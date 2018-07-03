package com.example.transparencia.configBatchExcel;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import com.example.transparencia.model.AtributosFormato;
import com.example.transparencia.model.ReporteFormato;
import com.example.transparencia.poi.PoiItemReaderTransparencia;
import com.example.transparencia.poi.RowMapperLista;

@Configuration
@EnableBatchProcessing
public class BatchConfigExcel {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job importJob() {
		return jobBuilderFactory.get("job1").start(step0()).next(step1()).build();
	}

	// STEPS 0 -----
	
	@Bean
    public Step step0() {
        return stepBuilderFactory.get("step0")
        		.tasklet(tasklet())
                .build();
    }
	
	@Bean
	public TaskletStep1 tasklet() {
		return new TaskletStep1();
	}

	// STEPS 1 -----------------------------------------

	@Bean
	public Step step1() {

		Step pasoLectura = stepBuilderFactory.get("step1").<AtributosFormato, ReporteFormato>chunk(1)
				.reader(readExcel()).processor(procesoLectura()).writer(null).build();

		return pasoLectura;
	}

	@Bean
	public ItemReader<AtributosFormato> readExcel() {
		PoiItemReaderTransparencia<AtributosFormato> readerExcel = new PoiItemReaderTransparencia<AtributosFormato>();
		readerExcel.setResource(new ClassPathResource("/excel/students.xlsx"));
		readerExcel.setRowMapper(new RowMapperLista());
		return readerExcel;
	}

	/* bean para lectura del excel */

	@Bean
	public ProcesoLectura procesoLectura() {
		return new ProcesoLectura();
	}

}
