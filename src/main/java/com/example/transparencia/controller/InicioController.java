package com.example.transparencia.controller;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.transparencia.model.ReporteFormato;

@RestController
public class InicioController {
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("importJob")
	private Job job;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ReporteFormato inicio() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameter = new JobParametersBuilder().addString("reporteFormato", "1").toJobParameters();
		jobLauncher.run(job, jobParameter);
		
		return new ReporteFormato();
	}

}
	